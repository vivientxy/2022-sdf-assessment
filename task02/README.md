## SDF 2022 Assessment - Task 02

To run the code:
```
java -cp target/classes Client.ClientApp
```

## Description

This project connects to a server and receives an initial message from the server, in the format:

> requestID int,int,int,int,int...

The client will send the following information back to server:
1. request ID
2. name
3. email
4. average of the list of integers

The server will verify the information, and respond with either a true or false with error message.

The client will then print out SUCCESS if true, FAILURE and the error message if false.

## Java Version

This project runs on Java 21