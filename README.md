# Rover Swarm Project

## Part 1

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

####Running the project####

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
2. Question 4.
