# Simple Chat App 

This java app is created using Spring Boot and h2 in memory database. This app has 3 endpoints 

1. sendMessage
2. getMessageForSender 
3. getMessageBetweenSenderAndReceiver

# Database Modeling 
 
For the simimplicy of the app, I have created on object to store the messages. column sentDate will be helpful to test the old messages. 

![Data Model](https://user-images.githubusercontent.com/9857819/126576422-66b62435-6e2e-48a9-a20c-421d13eecf5f.png)

During the boottime, Message table will be created and also inserts the 1000 dummy records .  Create statements/Insert statements can found at 

[data.sql](https://github.com/kranthides/simple-chat-app/blob/master/src/main/resources/data.sql).




