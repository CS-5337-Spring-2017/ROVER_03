# Rover Swarm Project

## Part 1

**3) How can the project be setup to run locally using Eclipse, or command line, or runnable JAR’s?**

First install git on your local machine in order to pull and push code from GitHub and local machine.

Use following link for reference.

(https://git-scm.com/book/en/v2/Getting-Started-Installing-Git)

To run Rover Swarm Project locally, you need to take care of following things.
* Pull code from GitHub repository.
* Set it up that code in local machine.

Now, setting up in local machine can be done using Eclipse or Command line or JAR files.
Running this project depends upon the way you have files in your local computer i.e. JAR, Bat files, or inside Eclipse as a whole project.

To set up project  locally using Eclipse follow steps mentioned bellow:

1. In your local machine create a new folder where you want to store your project.
2. Create blank Eclipse Java project at that folder.
3. Create a folder ROVER_XX inside that folder. (Where XX represents your rover number)
4. Go to your rover specific repository on GitHub and use big green button to copy link to clipboard.

[image for clone link]!(http://imgur.com/DyvM8Y5)

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

[image for adding jars]!(http://imgur.com/kw9Ja0m)

11. Navigate to your project folder and inside lib folder add all the jars and refresh your project. Boom, errors gone!
