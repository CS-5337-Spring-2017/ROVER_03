# Rover Swarm Project

## Part 1

**3) How can the project be setup to run locally using Eclipse, or command line, or runnable JAR’s?**

First install git on your local machine in order to pull and push code from GitHub and local machine.
Use following link for refrence.
(https://git-scm.com/book/en/v2/Getting-Started-Installing-Git)

To run Rover Swarm Project locally, you need to take care of following things.
* Pull code from GitHub repository.
* Set it up that code in local machine.

Now, setting up in local machine can be done using Eclipse or Command line or JAR files.
Running this project depends upon the way you have files in your local computer i.e. JAR, Bat files, or inside Eclipse as a whole project.

To set up project  locally using Eclipse follow steps mentioned bellow:
1. In your local machine create a new folder where you want to store your project.
2. Create a folder ROVER_XX inside that folder. (Where XX represents your rover number)
3. Go to your rover specific repository on GitHub and use big green button to copy link to clipboard.
!(/Users/bug/Desktop/Clone_link_github.paint)
4. On your local machine folder where you're storing your project, create a foler ROVER_XX(Agian as previously XX represents your rover number)
5. Open terminal at that location and type following commands.
```
  1. git init
  2. git remote set origin "paste your url here"
  3. git pull origin master
```
4. Name it as ROVER_XX (where XX represents your rover number).
4. Create blank Eclipse java project at that folder.
