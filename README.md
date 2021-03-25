## Spring-boot-netty server.

This is an SSE server designed to push the event in a certain interval.

## Required: 
* JDK11
* gradle

## Compile:

     gradle clean build

## Run:
##### Note: No additional parameter required right now 

    java -jar build/libs/netty-0.0.1-SNAPSHOT.jar

## EndPoints:

1. ### Request: SSE end point
    * ### Method 
        GET
    * ### path
        /public/ping/subscribe
    ### Response
    * Media type: `Content-Type: text/event-stream;charset=UTF-8`
    
            {"id":"e26e1c66-98a9-4cc7-8d36-347ace6ce810","epoch":1616665061,"time":"15:37:41","event":"PING","data":" id->1 total consumer->:1"}
            {"id":"1f902d1e-4b19-46b6-ab54-0b3d840437e5","epoch":1616665055,"time":"15:37:35","event":"PING","data":" id->1 total consumer->:1"}

2. ### Request: REST endpoint(A large html file)
    * ### Method
      GET
    * ### path
      /public/file
   ### Response
    * Media type: `Content-Type: text/html;charset=UTF-8`

            <!DOCTYPE html><html lang="en"><head><meta charset="UTF-8"><meta http-equiv="X-UA-Compatible" content="IE=edge"> ................. </html>



# CPU-THREAD-MEMORY- usage.
used `visualvm` to measure the stat.

# Initial stat: No load.
![initial](benchmark-2/Screenshot%20from%202021-03-25%2018-09-53.png)

# Initial stat: No load. Threads-1
![initial](benchmark-2/Screenshot%20from%202021-03-25%2018-10-04.png)

# 100 individual SSE client connected concurrent.
![initial](benchmark-2/Screenshot%20from%202021-03-25%2018-11-29.png)

# 100 individual Rest client concurrent
![initial](benchmark-2/Screenshot%20from%202021-03-25%2018-12-57.png)

# 100 SSE & 10 individual Rest client concurrent - Jmeter
![initial](benchmark-2/Screenshot%20from%202021-03-25%2018-13-06.png)

# 100 SSE & 10 individual Rest client concurrent - App stat
![initial](benchmark-2/Screenshot%20from%202021-03-25%2018-13-59.png)

# 100 SSE & 10 individual Rest client concurrent - APP Threads
![initial](benchmark-2/Screenshot%20from%202021-03-25%2018-14-07.png)

# 100 SSE & 10 individual Rest client concurrent thread ENDED - APP stat
![initial](benchmark-2/Screenshot%20from%202021-03-25%2018-14-36.png)




