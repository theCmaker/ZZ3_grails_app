# ZZ3_grails_app

ZZ3 project on Grails to create a stackoverflow-like. A gamification will be in place to reward users.

## Folder structure

The structure is the following :
* angular : the code for the client side of the app
* app : the code for the 'api' side of the app, in grails
* docs : specification documents, uml and usefull documents

### Angular

This folder contains the `Angular2` code of the client.
Several components and services have been created to limit code duplication.

### App

This part concerns the `Grails` app. We tend to use this as an API to interact with the different mechanisms of the app. It allows to fetch and save data. It also allows to do the same actions as in the fist part of the project.
All actions from the first part of the project are not working. This is still a work is progress, but the folowwing mechanisms are in place (making all the features working is a matter of time) :
* stateless login, using JWT method
* health check url : http://localhost:8080/health
* feature flipping : http://localhost:8080/feature, this enables to turn on or off some features in the app (such as answering questions, creating accounts, vote, etc...). This is only available for admin users
* a stateless angular client is also available

## How to use ?

To use this project, you will first need to run the `Grails` server.
To do so, make sure you have the Grails CLI with at least the following versions (`grails -v`) :
* Grails Version: 3.2.3
* Groovy Version: 2.4.7
* JVM Version: 1.8

Once this is checked, go to the `app` folder and run `grails run-app`. This will start the server side.

Then, go back on root folder and go to the `angular` folder.
Make sure `node` (v5.0 at least) and `npm` (v3.0 at least) are installed.

Some npm packages will need to be installed globally (`npm install -g <package>`):
C:\Users\bbs-0\AppData\Roaming\npm
* angular-cli
* typescript

When this is done, use the command `npm install` in the `angular` folder to install the dependancies, then start the server using `npm start`. When a message says that the server is ready, you can now use the app in your browser at [http://localhost:4200/]().
