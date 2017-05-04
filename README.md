# Rover Swarm Project

## Part 1

<<<<<<< HEAD
## **3) How can the project be setup to run locally using Eclipse, or command line, or runnable JAR’s?**
=======
**About the Simulation Project:**

The rover simulation software contains three major components that work in coordination to provide the simulation where rovers explore any given terrain made of different types of material and collect and analyze material substances of interest called science located at different regions of the terrain. Examples of terrain types are soil, rock, fluid, gravel, etc. Examples of sciences are minerals, radioactive materials, etc.

**Components of Rover Simulation Project:**

The following are the three major components of the rover simulation software:

1.	Rover Command Processor
2.	Rovers
3.	Communication Server

**Rover Command Processor**

The rover command processor is the Java Swing user interface (UI) that reads the map information stored in a text file format and displays the visual representation of the map terrain along with the rovers and science information. The rover command processor also receives the movement information from each of the rovers and updates the display to show the most current location of each of the rovers. The rover command processor and the rovers are packaged into the same Java project called "RoverSwarmProject."

**Rovers**

Rovers are Java programs that contain algorithms to explore the terrain looking for sciences. Each rover has different drive types such as wheels, treads, legs (walkers), etc., and they have different capabilities and are equipped with different types of tools to analyze and report the different types of sciences. Rovers may encounter sciences that they are capable of gathering and analyzing or they may encounter sciences that they are not equipped to handle. If they encounter sciences they cannot gather and analyze, they report the sciences they have discovered to the communications server that the other rovers equipped to handle that science can read and harvest. The rover java programs are packaged along with the rover command processor into the same Java project: "RoverSwarmProject."

**Communication Server**

The communication server is a RESTful server application implemented in Node.JS. The communication server mimics a satellite that receives messages sent by rovers on the ground and also broadcasts information to rovers. The information shared via communication server includes details about activities of all active rovers in the terrain being explored, the details about all sciences that were communicated to the communication server by the rovers and the overall terrain map information showing what regions of the overall map have been explored so far and what portion is yet to be explored.

![image 1](http://i.imgur.com/ka699td.png)

**RoverSwarmProject – Java Project Structure**

The following table lists all Java packages in the RoverSwarmProject project and descriptions of key classes in those packages.

![image 2](http://i.imgur.com/L6HqmRo.png)

**SwarmCommunicationServer – Node.JS application structure**

The following is the table that shows various key files in the SwarmCommunicationServer Node.JS application:

![image 3](http://i.imgur.com/OiCQeVQ.png)

### How can the project be set up to run locally using Eclipse, command line, or runnable JAR’s?
>>>>>>> master

First install Git on your local machine in order to pull and push code from GitHub and the local machine.

Use the following link for reference.

(https://git-scm.com/book/en/v2/Getting-Started-Installing-Git)

To run the Rover Swarm Project locally, you need to take care of the following things:

	* Pull code from GitHub repository.
	* Set up that code in local machine.

Now, setting up the project in the local machine can be done using Eclipse.
Running this project depends on the way you have files in your local computer, i.e., JAR, BAT files, or inside Eclipse as a whole project.

#### Using Eclipse

To set up the project locally using Eclipse, follow the steps mentioned below:

<<<<<<< HEAD
### Grab rover code ###
=======
#### Grab Rover Code
>>>>>>> master

1. In your local machine, create a new folder where you want to store your project.
2. Create a blank Eclipse Java project in that folder.
3. Create a folder ROVER_XX inside that folder, Where XX represents your rover number.
4. Go to your rover-specific repository on GitHub and use the big green button to copy the link to the clipboard.

![image for clone link](http://i.imgur.com/DyvM8Y5.png)

5. On your local machine folder where you're storing your project, create a folder ROVER_XX. (Again as previously, XX represents your rover number.)
6. Open the terminal at that location and type following commands:
```
  1. git init
  2. git remote set origin "paste your url here"
  3. git pull origin master
```
7. Now, rename this folder to something else and create folder ROVER_XX.
8. Copy everything from the other folder to your src folder under ROVER_XX folder.
9. Now, once you open Eclipse and refresh your project, you'll get all the files there, but with several errors.
10. To remove all errors, right click on your project, navigate to Build Path --> Configure Build Path under the Java Build Path tab, click on Libraries, and click on add External JARs.

![image for adding jars](http://i.imgur.com/kw9Ja0m.png)

11. Navigate to your project folder, and inside the Lib folder, add all the JAR files and refresh your project. Errors are now gone.

<<<<<<< HEAD
### Grab communication server ###
=======
#### Grab Communication Server
>>>>>>> master

Communication server does not need to be started in order to run the Rovers, but it must be started first if you want all the Rovers to communicate with one another.

Setting up the communication server is fairly similar to the Rover process.

The prerequisite is to have Node JS installed on your computer.

Note: Follow the link below for reference.

(https://nodejs.org/en/download/)

1. Now, go to your course repository on GitHub, and then go to the Swarm Communication Server Repo and copy link to clone as shown in the Rover part.

2. Create a local folder with any name on your local computer.

3. Open the terminal to that location and type in the following commands:
Note: Make sure you have Git installed.
```
1. git init
2. git remote set origin "paste url to swarm communication server"
3. git pull origin master
```

#### Running the Project

1. Run Swarm Communication Server by opening the terminal to the Swarm Communication Server folder and type in the following command:

```
node app.js
```
Note: Make sure you have Node JS installed.

2. Inside your Eclipse project under src/controlServer, run `RoverCommandProcessor.java`.
This will run the Map and the Command Processor, and then you'll be able to see all Rovers at their initial positions.

3. Now, to make your Rover move, run the `ROVER_XX.java` file under the src/swarmBots folder from Eclipse.

### How are the Windows BAT files used, and how are the runnable JARs generated in Eclipse and used?

#### **JAR Files**

	* JAR files are created to easily run the program so we don't have to go through the entire sequence of steps from Eclipse each time.

	* There are two main files of which JAR files need to be created.

1) ROVER_XX.java

2) RoverCommandProcessor.java

Steps to create JAR files are as follows:

1. Right click on your Java file and click on `Export`.

2. Under the Java tab, select `Runnable Jar Files`.

<<<<<<< HEAD
### Running the project ###
=======
![Jar file 1](http://i.imgur.com/mIz4Qqg.png)
>>>>>>> master

3. Click `Next` and under Launch Configuration, select your Java file and export destination, and then select your location to save.

![Jar file 2](http://i.imgur.com/6tDqKYF.png)

4. Click `Next` and follow the remaining steps.

Note: Just ignore all errors from Eclipse because these executable JAR files will still run.

**Running JAR files**

* Open the terminal at that location, and type in the following command:
```
java -jar <myjarfile.jar>
```
<<<<<<< HEAD
**Note:** Make sure you have NodeJs installed.
=======
>>>>>>> master

**BAT files**

1. BAT files are essentially shortcuts to run JAR files so that you don't have to run the Rovers using a terminal.

2. First, make sure your BAT file and JAR files are in the same directory.

3. BAT files can be created using any text editor. Open the text editor and type in the following command:
```
start java -jar JarFileName.jar
```
4. Save it as a .bat file.

<<<<<<< HEAD
## **4) How are the windows bat files used, how are the runnable jars generated in Eclipse and used.**

### **JAR**

* JAR files are created to run program easily so we don't have to go trough all sequence of steps every time from eclipse.

* There are main two files of which JAR files need to be created.

1) ROVER_XX.java

2) RoverCommandProcessor.java

Steps to create JAR files are as following:

1. Right click on your java file and click on `Export`.

2. Under java tab select Runnable Jar Files.

![Jar file 1](http://i.imgur.com/mIz4Qqg.png)

3. Click next and under launch configuration select your java file and export destination select your location to save.

![Jar file 2](http://i.imgur.com/6tDqKYF.png)

4. Click next and follow along steps.

5. Ignore all errors still jar files will run.


**Running JAR files**

* Open terminal at that location. And type following command.

```
java -jar <myjarfile.jar>
```

**BAT files**

1. BAT files are essentially shortcut to run JAR files so you don't have to open terminal and type coomand all the time

2. First make sure your BAT file and JAR files are in same directory.

3. BAT files can be created using any text editor. Open text editor and type following command:

```
start java -jar JarFileName.jar
```

4. Save it as .bat file.

5. Double click to run BAT file.

**Note:** You can use one bat file to run multiple JARs. See the example bellow.

```
start java -jar ROVER_XX.jar
start java -jar ROVER_XX2.jar
```
=======
5. Double click to run the BAT file.

**Note:** You can use one BAT file to run multiple JAR files. See the example below.

```
start java -jar ROVER_XX.jar
start java -jar ROVER_XX2.jar
```

### What is the sequence for starting the project?

#### Using Eclipse to start a project without the use of the Swarm Communication Server: 

	* First, go to your Rover Project and open the source folder.

	* Locate and open the Control Server package.

	* Locate the Rover Command Processor dot java file.

	* Right click on it, and select run as Java application.

	* A GUI Map will display on your screen, and the console will display the message > The Swarm server is running.  

	* Next, locate and open the Swarm Bots package.

	* Select the Rover that you want to run.

	* Right click on it and select run as Java application.

	* Your Rover is now moving in the GUI Map.

#### Using Eclipse to start a project with the use of the Swarm Communication Server: 

	* First, make sure you have Node JS installed in your system.

	* Open Git Bash or Command Prompt, and navigate to the Swarm Communication Server project.

	* To start the Swarm Communication Server, enter `node app.js`.

	* It will display the message > Express app running on port 3000.

	* Note: To terminate the Swarm Communication Server hit `control + c`.

	* Next, go to your Rover Project and open the source folder.  

	* Locate and open the Control Server package.

	* Locate the Rover Command Processor dot java file.

	* Right click on it, and select run as Java application.

	* A GUI Map will display on your screen, and the console will display the message > The Swarm server is running.

	* Finally, locate and open the Swarm Bots package.

	* Select the Rover that you want to run.

	* Right click on it, and select run as Java application.

	* Your Rover is now moving in the GUI Map, communicating with the server and other Rovers, and collecting pieces of science.

### How are the maps structured and edited? How can the RoverCommandProcessor be started with a custom map?

* The maps are structured as a text file where each square has X and Y coordinates.  The (0, 0) coordinates are at the top left corner.  You can place the starting position for your particular Rover anywhere on the map by adding the Rover's number to the square on the map, as long as there is no duplicate.  There are some key terrain and science features on the map such as R for rock, G for gravel, S for sand, X for abyss, Y for radioactive, C for crystal, M for mineral, and O for organic.

* Because the map is a text file, you can pretty much edit anything as long as it is consistent with all the default values.

* To start the Rover Command Processor with a custom map, all you have to do is add the map to your project folder and name it MapDefault.txt.

### How can ROVER_xx be started with a custom URL?

#### Running on a Custom Server:

First, we need to install Node and Java on the server. Then, to run the Swarm Communication Server, install the dependencies in `package.json` and run the server file `app.js` with node. Finally, run the Java Swarm project by first running the Rover Command Processor and then running any Rover Swarm Bots.

### Make some recommendations on how to improve the implementation of the project.

#### Possible Implementations to Improve Simulation

There can be a system that regulates time by days and keeps account of months and years.  If this is implemented, there can be all sorts of applications. The first thing is to have weather implemented in such a way that when it is winter, the weather will be colder, and the program will create ice on the ground at night; therefore, it could greatly hinder the movement of the Rovers. Also, random occurrences of clouds block sunlight to the solar panels, which will slow down the exploration and resource gathering process of the Rovers.  There could also be dust storms that could possibly block the solar panels from receiving any light.  When a Rover breaks down, it would be a random occurrence because of weather conditions.  There can also be a random breakdown, depending on how much time and how far it has traveled.

##### Possible Implementations on Rover Capabilities

1. checkRange() - This will check the range between two Rovers and its ETA of both Rovers.

2. foundResources() - This will give the location of found resources from all Rovers and display which Rover has discovered what resources.

3. setQueue() - This will set the queue for the Rover to retrieve its next resource.

4. getQueue() - This will show the next resource to which the Rover will be attending.

5. GetCurrentQueue() - This will show the current resource to which the Rover is attending.   

6. getQueueOptions() - This will give options regarding the Rover's next available resource.

7. getRoversQueue() - This will display every Rover’s current resource and its next resource.   

8. switchToLongMessages() - If need be, this will project more messages for the Rover.

9. statusCheckConnectionAll() - This will forward all messages to and from each Rover and return the time it takes to communicate.

10. statusCheckDiagnosisAll() - This will check all Rovers and do a diagnosis.
>>>>>>> master
