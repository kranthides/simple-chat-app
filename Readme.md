# Simple Chat App 

This java app is created using Spring Boot and h2 in-memory database. It has 3 endpoints 

1. sendMessage
2. getMessageForSender 
3. getMessageBetweenSenderAndReceiver

# Database Modeling 
 
For the simplicity of the app, I have created an object to store the messages. column sentDate will be helpful to test the old messages. 

![Data Model](https://user-images.githubusercontent.com/9857819/126576422-66b62435-6e2e-48a9-a20c-421d13eecf5f.png)

During the boot time, Message table will be created and also inserts the 1000 dummy records.  Create statements/Insert statements can be found at 

[data.sql](https://github.com/kranthides/simple-chat-app/blob/master/src/main/resources/data.sql).


# Architecture - Current State

![Design Document](https://user-images.githubusercontent.com/9857819/126578123-63806b97-8c3c-49fa-b930-f928663a6440.png)

User1 & USer2 can communicate using the above-mentioned APIs, All the incoming messages are going to store in the database.  

**Limitations of the current design**
* If the database is down for maintenance or for other reasons, messages will be lost. 
* All the communications are happening are using the HTTP protocol. The user always needs to submit the request to the server to get the latest messages. There is no continuous polling/connection to the server. 
* If we want to start storing the images, the database is not going to help us to scale. 
* Database is going to act as the bottleneck if the volume of messages increases. 

# Future state 
![Future State](https://user-images.githubusercontent.com/9857819/126579186-dedae732-9c05-4c96-8895-615c36d3ee08.png)

* Registration Service will help create the user accounts and stores them in the database. 
* Web sockets service is going to create a communication channel between a server and the user. And the information is going to be stored in the distributed Cache
* Message service will accept all the incoming messages and it can look up the user information in the distributed cache to push the message to the respective user. 
* We can use CDN systems like S3 to store the images/videos and other attachments 

# Tech Debt 
* Authentication and Autherization 
* CI/CD pipeline using Docker/ Kubernates 
* Unit test cases 
