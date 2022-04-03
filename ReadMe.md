###Courier Demo Project 

This project demonstrates a sample courier application. This project has 2 APIs.

1. /trackCourier API:
With this API, you can track and see if a courier enters 100 meters radius of a store.
This API waits courier id, tracking request time and current longitude and latitude values of courier as request parameters. 
Then API checks the courier entered to this store in 1 minute or not. If courier re-enters in 1 minute, 
the entrance time isn't being updated and this entrance is not being counted. Over 1 minute entrances, is being updated and counted.
When a courier enters to a store for the first time, system logs this line: "The courier <courierId> entered radius of 100 meter of store:<storeName>"
When a courier enters to same store in 1 minute, system logs this line: "The courier entered to same store in 1 minute, this entrance is not being counted!"
When a courier enters to same store over 1 minute, system logs this line: "The courier entered to store over 1 minute. The courier <courierId> entered radius of 100 meter of store:<storeName>"

2. /travellingDistance API:
With this API, you can see the total travelling distance of the courier. This API supports the HTTP GET method and you only need to pass courier id as path variable.

You can find CourierProject-1.0-SNAPSHOT.jar in the root of the project. Download that JAR to any place you want.
Then to run this project on your local machine, change directory to JAR location and
please run this command: java -jar CourierProject-1.0-SNAPSHOT.jar

Here is an example JSON request to track the courier. 

You can POST below JSON to http://localhost:8080/trackCourier

{
    "courierId":1,
    "trackingRequestTime":1648991787604,
    "longitude":29.1244229,
    "latitude":40.9923307
}

Here is an example JSON request to get total travelling distance of courier. 

You can call http://localhost:8080/travellingDistance/1 address with HTTP GET method  to get total travelling distance. 1 is courier id. When you started application, the code
initiates 2 orders for courier 1 and when you call API, you will get 33 KM as total travelling distance. 


