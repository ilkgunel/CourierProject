###Courier Demo Project 

This project demonstrates a sample courier application. This project has 2 APIs.

1. /trackCourier API: With this API, you can track and see if a courier enters 100 meters radius of a store.This API waits courier id, tracking request time and current longitude and latitude values of courier. Then API checks the courier entered to this store in 1 minute or not. If courier re-enters in 1 minute, the entrance time isn't being updated and this entrance is not being counted. Over 1 minute entrances, is being updated and counted.
2. /travellingDistance API: With this API, you can see the total travelling distance of the courier. This API supports the HTTP GET method and you only need to pass courier id as path variable.

You can find CourierProject-1.0-SNAPSHOT.jar in the root of the project. Download that JAR to any place you want.
Then to run this project on your local machine, change directory to JAR location and
please run this command: java -jar CourierProject-1.0-SNAPSHOT.jar

