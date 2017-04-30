<<<<<<< HEAD
# Rover Swarm Project

## Part 1
=======
# CS-5337-Spring 2017 Rover Swarm Project

# Group-3 - Describe how the Rover Swarm Project simulation engine works

**About the Simulation Project:**

The rover simulation software contains 3 major components that work in co-ordination to provide the simulation where rovers explore any given terrain made of different types of material and collect and analyze material substances of interest, called science, located at different regions of the terrain.	Examples of terrain types are soil, rock, fluid, gravel, etc. Examples of sciences are minerals, radioactive materials etc.

**Components of Rover Simulation Project:**

Following are the 3 major components of the rover simulation software:

1.	Rover Command Processor
2.	Rovers
3.	Communication Server

**Rover Command Processor**

The rover command processor is the Java Swing user interface (UI) that reads the map information stored in a text file format and display the visual representation of the map terrain along with the rovers and science information. The rover command processor also receives the movement information from each of the rovers and updates the display to show the most current location of each of the rover. The rover command

**Rovers**

Rovers are Java programs that contain algorithms to explore the terrain looking for sciences. Each rover has different drive types like wheels, treads, legs (walker), etc. and they have different capabilities and equipped with different types of tools to analyze and report different types of sciences. Rovers may encounter sciences that they are capable of gathering analyzing or they may encounter sciences that they are not equipped to handle. If they encounter sciences they cannot gather and analyze, they report the sciences they discovered to the communications server which the other rovers that are equipped to handle that science can read and harvest. The rover java programs are packaged along with the rover command processor into the same Java project “RoverSwarmProject”.

**Communication Server**

The communication server is a RESTful server application implemented in node.js. The communication server mimics a satellite that receives messages sent by rovers on the ground and also broadcasts information to rovers. The information shared via communication server includes details about activities of all active rovers in the terrain being explored, the details about all sciences that were communicated to the communication server by the rovers and the overall terrain map information showing what regions of the overall map have been explored so far and what portion is yet to be explored.

![image 1](http://i.imgur.com/ka699td.png)

**RoverSwarmProject – Java Project Structure**

The following table lists all the java packages in the RoverSwarmProject project and descriptions of key classes in those packages

![image 2](http://i.imgur.com/L6HqmRo.png)

**SwarmCommunicationServer – node.js application structure**

The following is the table that shows various key files in the SwarmCommunicationServer node.js application:

![image 3](http://i.imgur.com/OiCQeVQ.png)
>>>>>>> f455d3480785888cfe7d61b99510d26374544843

**3) How can the project be setup to run locally using Eclipse, or command line, or runnable JAR’s?**

First install git on your local machine in order to pull and push code from GitHub and local machine.

Use following link for reference.

(https://git-scm.com/book/en/v2/Getting-Started-Installing-Git)

To run Rover Swarm Project locally, you need to take care of following things.

* Pull code from GitHub repository.
* Set it up that code in local machine.

Now, setting up project in local machine can be done using Eclipse.
Running this project depends upon the way you have files in your local computer i.e. JAR, Bat files, or inside Eclipse as a whole project.

### **Using eclipse** ###

To set up project locally using Eclipse follow steps mentioned bellow:

#### Grab rover code ####

1. In your local machine create a new folder where you want to store your project.
2. Create blank Eclipse Java project at that folder.
3. Create a folder ROVER_XX inside that folder. (Where XX represents your rover number)
4. Go to your rover specific repository on GitHub and use big green button to copy link to clipboard.

![image for clone link](http://i.imgur.com/DyvM8Y5.png)

5. On your local machine folder where you're storing your project, create a folder ROVER_XX. (Again as previously XX represents your rover number)
6. Open terminal at that location and type following commands.
```
  1. git init
  2. git remote set origin "paste your url here"
  3. git pull origin master
```
7. Now rename this folder to anything else and create folder ROVER_XX.
8. Copy everything from other folder to your src folder under ROVER_XX folder.
9. Now, once you open Eclipse and refresh your project you'll all the files there but with serval errors.
10. To remove all errors, right click on your project navigate to Build path --> Configure build path under Java Build path tab click on Libraries and click on add external jars.

![image for adding jars](http://i.imgur.com/kw9Ja0m.png)

11. Navigate to your project folder and inside lib folder add all the jars and refresh your project. Boom, errors gone!

#### Grab communication server ####

Communication sever is not needed to check rover moment, but it's important when all the rovers start communicating with each other.

Setting up communication sever is fairly similar to ROVER process.

Prerequisite is to have NodeJs installed on your windows or mac pc.

Follow link bellow for reference.

(https://nodejs.org/en/download/)

1. Now, go to your course repository on GitHub and go to Swarm Communication Server repo and copy link to clone as shown in Rover part.

2. Create local folder with any name on your local pc.

3. Open terminal at that location and type following code.
Note: Make sure you have git installed.

```
1. git init
2. git remote set origin "paste url to swarm communication server"
3. git pull origin master

```

All done!

#### Running the project ####

1. Run Swarm communication sever by opening terminal at that specific location and type following command.

```
node app.js
```
Note: Make sure you have NodeJs installed.

2. Inside your eclipse project under src/controlServer run `RoverCommandProcessor.java`.
This will run map and command processor and you'll be able to see all rovers at initial position.

3. Now, to make your rover move, run `ROVER_XX.java` file under src/swarmBots folder from Eclipse.


**Pending**
1. Run using Jar and Bat files.
<<<<<<< HEAD
2. Question 4.
=======
2. Creating JAR and Bats files.

### What is the sequence for starting the project?

#### To start a project without without using the Swarm Communication Server using Eclipse

* First, go to your Rover Project and open the source folder

* Locate and open the Control Server package

* Locate the Rover Command Processor dot java file

* Right click on it and select run as Java application

* A GUI Map will display on your screen, and the console will display a message > The Swarm server is running.  

* Next, locate and open the Swarm Bots package

* Select the Rover that you want to run

* Right click on it and select run as Java application

* Your Rover is now moving in the GUI Map

#### To start a project using the Swarm Communication Server using Eclipse

* First, make sure you have Node JS installed in your system

* Open Git Bash or Command Prompt and navigate to the Swarm Communication Server project

* To start the Swarm Communication Server enter `node app.js`

* It will display a message > Express app running on port 3000

* Note: to terminate the Swarm Communication Server hit control + c

* Next, go to your Rover Project and open the source folder  

* Locate and open the Control Server package

* Locate the Rover Command Processor dot java file

* Right click on it and select run as Java application

* A GUI Map will display on your screen, and the console will display a message > The Swarm server is running.

* Finally, locate and open the Swarm Bots package

* Select the Rover that you want to run

* Right click on it and select run as Java application

* Your Rover is now moving in the GUI Map, communicating with the server and other Rovers, and collecting pieces of science

### How are the maps structured and edited? How can the RoverCommandProcessor be started with a custom map?

* The maps are structured as a text file where each square has an X and Y coordinates.  The (0, 0) coordinates are at the top left corner.  You can place the starting position for your particular Rover anywhere on the map by adding the Rover's number to the square on the map as long as there is no duplicate.  There are some key terrain and science features on the map such as R for rock, G for gravel, S for sand, X for abyss, Y for radioactive, C for crystal, M for mineral, and O for organic.

* Because the map is a text file, you can pretty much edit anything as long as it is consistent with all the default values.

* To start the Rover Command Processor with a custom map, all you have to do is add the map to your project folder and name it MapDefault.txt.

### How can ROVER_xx be started with a custom URL?
# Running on a Custom Server:
###### First we need to install node and java in the server.  Then to run the Swarm Communication Server, install the dependencies in `package.json` and run the server file `app.js` with node.  And finally run the java swarm project by first opening the Rover Command Processor and with any Rover swarm bots.

### Make some recommendations on how to improve the implementation of the project  
# Possible implementations to improve simulation
###### There can be a system that regulates time by days and keeps account of months and years.  If this is implemented, there can be all sorts of applications.  First thing is having weather implemented in such a way that when its winter season, the weather will be colder and it’ll create ice on the ground at nights and therefore it could deteriorate the time to explore.  Also random occurrences of clouds are blocking sunlight to the solar panels will slow the exploration and resource gathering process of the rover.  There could also be dust storms which could possibly block the solar panels from receiving any light.  When a rover breaks down, it would be a random occurrence because of weather condition.  There can also be a random break depending on how much time and kilometers it has traveled.

##### Possible Implementations on Rover capabilities.


1. checkRange() - This will check the range between two rovers and it’s eta of both rovers.

2. foundResources() - This will give the location of found resources from all rovers and displays which rover has discovered which resources.

3. setQueue() - This will set the queue for the rover to retrieve it’s next resource.

4. getQueue() - This will show the next resource the rover will be attending.

5. GetCurrentQueue() - This will show the current resource the rover is attending to.   

6. getQueueOptions() - This will give options where it’s next available resource should attend to.

7. getRoversQueue() - will display every Rover’s current resource and it’s next .   

8. switchToLongMessages() - If need be, This will project more messages for the Rover.

9. statusCheckConnectionAll() - This will forward all messages from and to each Rover and return the time it takes.

10. statusCheckDiagnosisAll() - This will check all rovers and do a diagnosis.
>>>>>>> f455d3480785888cfe7d61b99510d26374544843
