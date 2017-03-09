package swarmBots;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import common.MapTile;
import common.Rover;
import enums.Terrain;

/**
 * The seed that this program is built on is a chat program example found here:
 * http://cs.lmu.edu/~ray/notes/javanetexamples/ Many thanks to the authors for
 * publishing their code examples
 */

public class ROVER_03 extends Rover {

	public ROVER_03() {
		// constructor
		System.out.println("ROVER_03 rover object constructed");
		rovername = "ROVER_03";
	}

	public ROVER_03(String serverAddress) {
		// constructor
		System.out.println("ROVER_03 rover object constructed");
		rovername = "ROVER_03";
		SERVER_ADDRESS = serverAddress;
	}

	static enum Direction {
		NORTH, SOUTH, EAST, WEST;
	}

	static class Navigation {
		int northSteps = 1, currNorthSteps = 1;
		int eastSteps = 1, currEastSteps = 1;
		int southSteps = 2, currSouthSteps = 2;
		int westSteps = 2, currWestSteps = 2;
	}

	/**
	 * Connects to the server then enters the processing loop.
	 */
	private void run() throws IOException, InterruptedException {

		// Make connection to SwarmServer and initialize streams
		Socket socket = null;
		try {
			socket = new Socket(SERVER_ADDRESS, PORT_ADDRESS);

			receiveFrom_RCP = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			sendTo_RCP = new PrintWriter(socket.getOutputStream(), true);

			// Need to allow time for the connection to the server to be
			// established
			sleepTime = 100;

			// Process all messages from server, wait until server requests
			// Rover ID
			// name - Return Rover Name to complete connection
			while (true) {
				String line = receiveFrom_RCP.readLine();
				if (line.startsWith("SUBMITNAME")) {
					// This sets the name of this instance of a swarmBot for
					// identifying the thread to the server
					sendTo_RCP.println(rovername);
					break;
				}
			}

			/**
			 * ### Setting up variables to be used in the Rover control loop ###
			 */
			Direction currentDir = Direction.NORTH;

			/**
			 * ### Retrieve static values from RCP ###
			 */
			// **** get equipment listing ****
			equipment = getEquipment();
			System.out.println(rovername + " equipment list results " + equipment + "\n");

			// **** Request START_LOC Location from SwarmServer **** this might
			// be dropped as it should be (0, 0)
			startLocation = getStartLocation();
			System.out.println(rovername + " START_LOC " + startLocation);

			// **** Request TARGET_LOC Location from SwarmServer ****
			targetLocation = getTargetLocation();
			System.out.println(rovername + " TARGET_LOC " + targetLocation);

			Navigation navigation = new Navigation();

			/**
			 * #### Rover controller process loop ####
			 */
			while (true) { // <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

				// **** Request Rover Location from RCP ****
				currentLoc = getCurrentLocation();

				System.out.println(rovername + " currentLoc at start: " + currentLoc);

				// after getting location set previous equal current to be able
				// to check for stuckness and blocked later
				previousLoc = currentLoc;

				// ***** do a SCAN *****
				// gets the scanMap from the server based on the Rover current
				// location
				scanMap = doScan();
				// prints the scanMap to the Console output for debug purposes
				scanMap.debugPrintMap();

				MapTile[][] scanMapTiles = scanMap.getScanMap();
				int centerIndex = (scanMap.getEdgeSize() - 1) / 2;

				// ***** get TIMER time remaining *****
				timeRemaining = getTimeRemaining();

				// ***** MOVING *****
				if (currentDir == Direction.NORTH) {
					if (navigation.currNorthSteps > 0) {
						if (scanMapTiles[centerIndex][centerIndex - 1].getHasRover()
								|| scanMapTiles[centerIndex][centerIndex - 1].getTerrain() == Terrain.ROCK) {
							takeDiversion(currentDir, navigation);
							if (navigation.currNorthSteps > 0) {
								currentDir = Direction.NORTH;
							} else {
								currentDir = Direction.EAST;
							}
						} else {
							System.out.println("======> Loc before move north: " + getCurrentLocation());
							moveNorth();
							System.out.println("------> Loc after move north: " + getCurrentLocation());
							navigation.currNorthSteps--;
						}
					}
					if (navigation.currNorthSteps <= 0) {
						navigation.northSteps += 2;
						navigation.currNorthSteps = navigation.northSteps;
						currentDir = Direction.EAST;
					}
				} else if (currentDir == Direction.EAST) {
					if (navigation.currEastSteps > 0) {
						if (scanMapTiles[centerIndex + 1][centerIndex].getHasRover()
								|| scanMapTiles[centerIndex + 1][centerIndex].getTerrain() == Terrain.ROCK) {
							takeDiversion(currentDir, navigation);
							if (navigation.currEastSteps > 0) {
								currentDir = Direction.EAST;
							} else {
								currentDir = Direction.SOUTH;
							}
						} else {
							System.out.println("======> Loc before move east: " + getCurrentLocation());
							moveEast();
							System.out.println("------> Loc after move east: " + getCurrentLocation());
							navigation.currEastSteps--;
						}
					}
					if (navigation.currEastSteps <= 0) {
						navigation.eastSteps += 2;
						navigation.currEastSteps = navigation.eastSteps;
						currentDir = Direction.SOUTH;
					}
				} else if (currentDir == Direction.SOUTH) {
					if (navigation.currSouthSteps > 0) {
						if (scanMapTiles[centerIndex][centerIndex + 1].getHasRover()
								|| scanMapTiles[centerIndex][centerIndex + 1].getTerrain() == Terrain.ROCK) {
							takeDiversion(currentDir, navigation);
							if (navigation.currSouthSteps > 0) {
								currentDir = Direction.SOUTH;
							} else {
								currentDir = Direction.WEST;
							}
						} else {
							System.out.println("======> Loc before move south: " + getCurrentLocation());
							moveSouth();
							System.out.println("------> Loc after move south: " + getCurrentLocation());
							navigation.currSouthSteps--;
						}
					}
					if (navigation.currSouthSteps <= 0) {
						navigation.southSteps += 2;
						navigation.currSouthSteps = navigation.southSteps;
						currentDir = Direction.WEST;
					}
				} else if (currentDir == Direction.WEST) {
					if (navigation.currWestSteps > 0) {
						if (scanMapTiles[centerIndex - 1][centerIndex].getHasRover()
								|| scanMapTiles[centerIndex - 1][centerIndex].getTerrain() == Terrain.ROCK) {
							takeDiversion(currentDir, navigation);
							if (navigation.currWestSteps > 0) {
								currentDir = Direction.WEST;
							} else {
								currentDir = Direction.NORTH;
							}
						} else {
							System.out.println("======> Loc before move west: " + getCurrentLocation());
							moveWest();
							System.out.println("------> Loc after move west: " + getCurrentLocation());
							navigation.currWestSteps--;
						}
					}
					if (navigation.currWestSteps <= 0) {
						navigation.westSteps += 2;
						navigation.currWestSteps = navigation.westSteps;
						currentDir = Direction.NORTH;
					}
				}

				// another call for current location
				currentLoc = getCurrentLocation();

				// this is the Rovers HeartBeat, it regulates how fast the Rover
				// cycles through the control loop
				Thread.sleep(sleepTime);

				System.out.println("ROVER_03 ------------ bottom process control --------------");
			} // END of Rover control While(true) loop

			// This catch block closes the open socket connection to the server
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					System.out.println("ROVER_03 problem closing socket");
				}
			}
		}

	} // END of Rover run thread

	private void debugNavigation(Navigation navigation) throws IOException {
		System.out.println("============ DEBUG ===============");
		System.out.println("currLoc: " + getCurrentLocation());
		System.out.println("northSteps = " + navigation.northSteps + ", currNorthSteps = " + navigation.currNorthSteps);
		System.out.println("eastSteps = " + navigation.eastSteps + ", currEastSteps = " + navigation.currEastSteps);
		System.out.println("southSteps = " + navigation.southSteps + ", currSouthSteps = " + navigation.currSouthSteps);
		System.out.println("westSteps = " + navigation.westSteps + ", currWestSteps = " + navigation.currWestSteps);
		System.out.println("==================================");
	}

	private void takeDiversion(Direction originalDirection, Navigation navigation)
			throws IOException, InterruptedException {
		System.out.println("%%%%%%%%%% BEFORE %%%%%%%%%%%%%");
		debugNavigation(navigation);
		int originalX = getCurrentLocation().xpos;
		int originalY = getCurrentLocation().ypos;
		if (originalDirection == Direction.NORTH) {
			int originalEastSteps = navigation.currEastSteps;
			int originalWestSteps = navigation.currWestSteps;
			do {
				moveAroundTheObstacle(originalDirection, navigation);
				if (originalEastSteps != navigation.currEastSteps || originalWestSteps != navigation.currWestSteps) {
					if (getCurrentLocation().xpos == originalX) {
						break;
					}
				}
				Thread.sleep(sleepTime);
			} while (navigation.currNorthSteps > 0);
		} else if (originalDirection == Direction.EAST) {
			int originalNorthSteps = navigation.currNorthSteps;
			int originalSouthSteps = navigation.currSouthSteps;
			do {
				moveAroundTheObstacle(originalDirection, navigation);
				if (originalNorthSteps != navigation.currNorthSteps
						|| originalSouthSteps != navigation.currSouthSteps) {
					if (getCurrentLocation().ypos == originalY) {
						break;
					}
				}
				Thread.sleep(sleepTime);
			} while (navigation.currEastSteps > 0);
		} else if (originalDirection == Direction.SOUTH) {
			int originalEastSteps = navigation.currEastSteps;
			int originalWestSteps = navigation.currWestSteps;
			do {
				moveAroundTheObstacle(originalDirection, navigation);
				if (originalEastSteps != navigation.currEastSteps || originalWestSteps != navigation.currWestSteps) {
					if (getCurrentLocation().xpos == originalX) {
						break;
					}
				}
				Thread.sleep(sleepTime);
			} while (navigation.currSouthSteps > 0);
		} else if (originalDirection == Direction.WEST) {
			int originalNorthSteps = navigation.currNorthSteps;
			int originalSouthSteps = navigation.currSouthSteps;
			do {
				moveAroundTheObstacle(originalDirection, navigation);
				if (originalNorthSteps != navigation.currNorthSteps
						|| originalSouthSteps != navigation.currSouthSteps) {
					if (getCurrentLocation().ypos == originalY) {
						break;
					}
				}
				Thread.sleep(sleepTime);
			} while (navigation.currWestSteps > 0);
		}
		System.out.println("%%%%%%%%%% AFTER %%%%%%%%%%%%%");
		debugNavigation(navigation);
	}

	private void moveAroundTheObstacle(Direction originalDirection, Navigation navigation) throws IOException {
		MapTile[][] mapTiles = doScan().getScanMap();
		Direction directionToMove = getDirectionToMove(mapTiles);
		System.out.println("$$$$$$$ directionToMove = " + directionToMove);
		Direction direction = directionToMove == null ? originalDirection : directionToMove;
		System.out.println("$$$$$$$ direction = " + direction);
		switch (direction) {
		case NORTH:
			moveNorth();
			// if (originalDirection == Direction.NORTH) {
			navigation.currNorthSteps--;
			// }
			break;
		case EAST:
			moveEast();
			// if (originalDirection == Direction.EAST) {
			navigation.currEastSteps--;
			// }
			break;
		case SOUTH:
			moveSouth();
			// if (originalDirection == Direction.SOUTH) {
			navigation.currSouthSteps--;
			// }
			break;
		case WEST:
			moveWest();
			// if (originalDirection == Direction.WEST) {
			navigation.currWestSteps--;
			// }
			break;
		default:
			break;
		}
	}

	private boolean isNorthBlocked(MapTile[][] mapTiles) {
		int centerIndex = (scanMap.getEdgeSize() - 1) / 2;
		return mapTiles[centerIndex][centerIndex - 1].getHasRover()
				|| mapTiles[centerIndex][centerIndex - 1].getTerrain() == Terrain.ROCK;
	}

	private boolean isEastBlocked(MapTile[][] mapTiles) {
		int centerIndex = (scanMap.getEdgeSize() - 1) / 2;
		return mapTiles[centerIndex + 1][centerIndex].getHasRover()
				|| mapTiles[centerIndex + 1][centerIndex].getTerrain() == Terrain.ROCK;
	}

	private boolean isSouthBlocked(MapTile[][] mapTiles) {
		int centerIndex = (scanMap.getEdgeSize() - 1) / 2;
		return mapTiles[centerIndex][centerIndex + 1].getHasRover()
				|| mapTiles[centerIndex][centerIndex + 1].getTerrain() == Terrain.ROCK;
	}

	private boolean isWestBlocked(MapTile[][] mapTiles) {
		int centerIndex = (scanMap.getEdgeSize() - 1) / 2;
		return mapTiles[centerIndex - 1][centerIndex].getHasRover()
				|| mapTiles[centerIndex - 1][centerIndex].getTerrain() == Terrain.ROCK;
	}

	private boolean isNorthEastBlocked(MapTile[][] mapTiles) {
		int centerIndex = (scanMap.getEdgeSize() - 1) / 2;
		return mapTiles[centerIndex + 1][centerIndex - 1].getHasRover()
				|| mapTiles[centerIndex + 1][centerIndex - 1].getTerrain() == Terrain.ROCK;
	}

	private boolean isNorthWestBlocked(MapTile[][] mapTiles) {
		int centerIndex = (scanMap.getEdgeSize() - 1) / 2;
		return mapTiles[centerIndex - 1][centerIndex - 1].getHasRover()
				|| mapTiles[centerIndex - 1][centerIndex - 1].getTerrain() == Terrain.ROCK;
	}

	private boolean isSouthEastBlocked(MapTile[][] mapTiles) {
		int centerIndex = (scanMap.getEdgeSize() - 1) / 2;
		return mapTiles[centerIndex + 1][centerIndex + 1].getHasRover()
				|| mapTiles[centerIndex + 1][centerIndex + 1].getTerrain() == Terrain.ROCK;
	}

	private boolean isSouthWestBlocked(MapTile[][] mapTiles) {
		int centerIndex = (scanMap.getEdgeSize() - 1) / 2;
		return mapTiles[centerIndex - 1][centerIndex + 1].getHasRover()
				|| mapTiles[centerIndex - 1][centerIndex + 1].getTerrain() == Terrain.ROCK;
	}

	private Direction getDirectionToMove(MapTile[][] mapTiles) {
		if (isNorthBlocked(mapTiles)) {
			if (isEastBlocked(mapTiles)) {
				return Direction.SOUTH;
			} else {
				return Direction.EAST;
			}
		} else if (isEastBlocked(mapTiles)) {
			if (isSouthBlocked(mapTiles)) {
				return Direction.WEST;
			} else {
				return Direction.SOUTH;
			}
		} else if (isSouthBlocked(mapTiles)) {
			if (isWestBlocked(mapTiles)) {
				return Direction.NORTH;
			} else {
				return Direction.WEST;
			}
		} else if (isWestBlocked(mapTiles)) {
			if (isNorthBlocked(mapTiles)) {
				return Direction.EAST;
			} else {
				return Direction.NORTH;
			}
		} else if (isNorthEastBlocked(mapTiles)) {
			return Direction.EAST;
		} else if (isNorthWestBlocked(mapTiles)) {
			return Direction.NORTH;
		} else if (isSouthEastBlocked(mapTiles)) {
			return Direction.SOUTH;
		} else if (isSouthWestBlocked(mapTiles)) {
			return Direction.WEST;
		}
		return null;
	}

	// ####################### Support Methods #############################

	/**
	 * Runs the client
	 */
	public static void main(String[] args) throws Exception {
		ROVER_03 client;
		// if a command line argument is present it is used
		// as the IP address for connection to SwarmServer instead of localhost

		if (!(args.length == 0)) {
			client = new ROVER_03(args[0]);
		} else {
			client = new ROVER_03();
		}

		client.run();
	}
}