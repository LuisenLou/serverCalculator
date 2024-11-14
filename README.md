# Simple Calculator Client-Server Application

This project demonstrates a basic client-server architecture for a simple calculator application, designed to perform addition over a network connection. It consists of a server and two clients that communicate using sockets.

## Overview

### Server
The server listens for incoming connections on port `3000` of the local machine (`localhost`). When a client connects, the server:
1. Receives two numbers from the client.
2. Adds the numbers together.
3. Sends the result back to the client.

### Clients
Each client:
1. Connects to the server at `localhost:3000`.
2. Sends two numbers to the server.
3. Waits to receive the calculated result from the server.
4. Displays the result to the user.

## Key Features

- **Client-Server Model**: Implements a basic client-server architecture.
- **Number Addition**: Performs a simple arithmetic operation (addition) on numbers received from clients.
- **Socket-Based Communication**: Uses Java sockets to enable networking and communication.
- **Localhost Communication**: All communication takes place on the same machine (localhost).
- **Fixed Port**: Uses port `3000` as the designated server listening port.

## Technologies

- **Java**: Main programming language for the application.
- **Sockets**: Enables network communication between the clients and server.

## Purpose

This project serves as an introductory exercise to network programming concepts, particularly focusing on:
- Client-server interactions using sockets.
- Implementing basic distributed applications.

It is an ideal starting point for learning about networking principles, Java socket programming, and inter-process communication.
