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
			StartLocation = getStartLocation();
			System.out.println(rovername + " START_LOC " + StartLocation);

			// **** Request TARGET_LOC Location from SwarmServer ****
			TargetLocation = getTargetLocation();
			System.out.println(rovername + " TARGET_LOC " + TargetLocation);

			int northSteps = 1, currNorthSteps = 1;
			int eastSteps = 1, currEastSteps = 1;
			int southSteps = 1, currSouthSteps = 1;
			int westSteps = 1, currWestSteps = 1;

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

				System.out.println("============ DEBUG ===============");
				System.out.println("currLoc: " + getCurrentLocation());
				System.out.println("northSteps = " + northSteps + ", currNorthSteps = " + currNorthSteps);
				System.out.println("eastSteps = " + eastSteps + ", currEastSteps = " + currEastSteps);
				System.out.println("southSteps = " + southSteps + ", currSouthSteps = " + currSouthSteps);
				System.out.println("westSteps = " + westSteps + ", currWestSteps = " + currWestSteps);
				System.out.println("==================================");

				// ***** MOVING *****
				if (currentDir == Direction.NORTH) {
					if (currNorthSteps > 0) {
						if (scanMapTiles[centerIndex][centerIndex - 1].getHasRover()
								|| scanMapTiles[centerIndex][centerIndex - 1].getTerrain() == Terrain.ROCK) {
							takeDiversion(currentDir);
						} else {
							System.out.println("======> Loc before move north: " + getCurrentLocation());
							moveNorth();
							System.out.println("------> Loc after move north: " + getCurrentLocation());
							currNorthSteps--;
						}
					}
					if (currNorthSteps == 0) {
						northSteps += 1;
						currNorthSteps = northSteps;
						currentDir = Direction.EAST;
					}
				} else if (currentDir == Direction.EAST) {
					if (currEastSteps > 0) {
						if (scanMapTiles[centerIndex + 1][centerIndex].getHasRover()
								|| scanMapTiles[centerIndex + 1][centerIndex].getTerrain() == Terrain.ROCK) {
							takeDiversion(currentDir);
						} else {
							System.out.println("======> Loc before move east: " + getCurrentLocation());
							moveEast();
							System.out.println("------> Loc after move east: " + getCurrentLocation());
							currEastSteps--;
						}
					}
					if (currEastSteps == 0) {
						eastSteps += 1;
						currEastSteps = eastSteps;
						currentDir = Direction.SOUTH;
					}
				} else if (currentDir == Direction.SOUTH) {
					if (currSouthSteps > 0) {
						if (scanMapTiles[centerIndex][centerIndex + 1].getHasRover()
								|| scanMapTiles[centerIndex][centerIndex + 1].getTerrain() == Terrain.ROCK) {
							takeDiversion(currentDir);
						} else {
							System.out.println("======> Loc before move south: " + getCurrentLocation());
							moveSouth();
							System.out.println("------> Loc after move south: " + getCurrentLocation());
							currSouthSteps--;
						}
					}
					if (currSouthSteps == 0) {
						southSteps += 1;
						currSouthSteps = southSteps;
						currentDir = Direction.WEST;
					}
				} else if (currentDir == Direction.WEST) {
					if (currWestSteps > 0) {
						if (scanMapTiles[centerIndex - 1][centerIndex].getHasRover()
								|| scanMapTiles[centerIndex - 1][centerIndex].getTerrain() == Terrain.ROCK) {
							takeDiversion(currentDir);
						} else {
							System.out.println("======> Loc before move west: " + getCurrentLocation());
							moveWest();
							System.out.println("------> Loc after move west: " + getCurrentLocation());
							currWestSteps--;
						}
					}
					if (currWestSteps == 0) {
						westSteps += 1;
						currWestSteps = westSteps;
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

	private void takeDiversion(Direction originalDirection) throws IOException, InterruptedException {
		Thread.sleep(sleepTime);
		System.out.println("Taking diversion along " + originalDirection);

		scanMap = doScan();
		MapTile[][] scanMapTiles = scanMap.getScanMap();
		int centerIndex = (scanMap.getEdgeSize() - 1) / 2;

		if (originalDirection == Direction.NORTH) {
			if (scanMapTiles[centerIndex + 1][centerIndex].getHasRover()
					|| scanMapTiles[centerIndex + 1][centerIndex].getTerrain() == Terrain.ROCK) {
				takeDiversion(Direction.EAST);
			} else {
				moveEast();
			}
		} else if (originalDirection == Direction.EAST) {
			if (scanMapTiles[centerIndex][centerIndex + 1].getHasRover()
					|| scanMapTiles[centerIndex][centerIndex + 1].getTerrain() == Terrain.ROCK) {
				takeDiversion(Direction.SOUTH);
			} else {
				moveSouth();
			}
		} else if (originalDirection == Direction.SOUTH) {
			if (scanMapTiles[centerIndex - 1][centerIndex].getHasRover()
					|| scanMapTiles[centerIndex - 1][centerIndex].getTerrain() == Terrain.ROCK) {
				takeDiversion(Direction.WEST);
			} else {
				moveWest();
			}
		} else if (originalDirection == Direction.WEST) {
			if (scanMapTiles[centerIndex][centerIndex - 1].getHasRover()
					|| scanMapTiles[centerIndex][centerIndex - 1].getTerrain() == Terrain.ROCK) {
				takeDiversion(Direction.NORTH);
			} else {
				moveNorth();
			}
		}
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