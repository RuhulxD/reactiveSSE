## Spring-boot-netty server.

This is an SSE server designed to push the event in a certain interval.

##Required: 
* JDK11
* gradle

##Compile:

     gradle clean build

##Run:
#####Note: No additional parameter required right now 

    java -jar netty-0.0.1-SNAPSHOT.jar

##EndPoints:

1. ###Request: SSE end point
    * ###Method 
        GET
    * ###path
        /public/ping/subscribe
    ###Response
    * Media type: `Content-Type: text/event-stream;charset=UTF-8`
    
            {"id":"e26e1c66-98a9-4cc7-8d36-347ace6ce810","epoch":1616665061,"time":"15:37:41","event":"PING","data":" id->1 total consumer->:1"}
            {"id":"1f902d1e-4b19-46b6-ab54-0b3d840437e5","epoch":1616665055,"time":"15:37:35","event":"PING","data":" id->1 total consumer->:1"}

2. ###Request: REST endpoint(A large html file)
    * ###Method
      GET
    * ###path
      /public/file
   ###Response
    * Media type: `Content-Type: text/html;charset=UTF-8`

            <!DOCTYPE html><html lang="en"><head><meta charset="UTF-8"><meta http-equiv="X-UA-Compatible" content="IE=edge"> ................. </html>


