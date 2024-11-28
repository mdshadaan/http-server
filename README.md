## Overview
A  simple http server that serves up html content. I was inspired to work on this project after coming across this - https://codingchallenges.substack.com/p/coding-challenged-11-build-your-own

Here's a high level overview of how it works - 
- Create a socket and bind it to the address of your server. In java , we can do this using the `ServerSocket` class.
- Once that is done , our server needs to listen to the incoming requests and accept them.  This is a great scenario to learn about threads. Since we want to serve multiple concurrent requests , **we create a new thread for each connection , binding each of them to a new socket and sending and receiving data over that socket**. 
- Parse the data coming from the client to extract information like the the request method , requested path , headers etc.
- If the requested path is **/ or /index.html** , send back the index.html file present in the **src/resources** directly to the client.

And that's it ! Now we have a very basic server that returns a simple html file. 
![Screenshot from 2024-11-28 21-26-11](https://github.com/user-attachments/assets/a2e4d95a-b032-4044-b9a4-69221dd1ae12)

## Resources I used while making this - 
- https://docs.oracle.com/javase/tutorial/networking/sockets/readingWriting.html
- https://jenkov.com/tutorials/java-multithreaded-servers/multithreaded-server.html


