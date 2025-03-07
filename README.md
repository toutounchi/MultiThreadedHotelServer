# Hotel Reservation System

## Overview
A simple hotel reservation system implemented in Java. This system allows users to make reservations, check room availability, and cancel existing reservations for a single room in a hotel for the month of March.

## Features
- **Reservation Management**: Users can request a reservation for any set of days in March, provided the room is available and the user doesn’t already have an existing reservation.
- **Availability Check**: Users can check the availability of the room for each day in the month.
- **Cancellation**: Users can cancel their reservations at any time.
- **Server-Client Architecture**: The application consists of a multi-threaded server that handles client requests, allowing for multiple clients to interact with the reservation system simultaneously.

## Components
### `Hotel` Class
- Represents the hotel and manages room availability and reservations for the month of March.

### `HotelClient` Class
- A console-based client that allows users to interact with the hotel reservation system by entering commands to check availability, make reservations, or cancel them.

### `HotelServer` Class
- A server that listens for incoming client connections, processes commands from the client, and communicates with the hotel reservation system.

### `HotelService` Class
- Handles the execution of commands for each client, running in a separate thread to allow concurrent processing.

## Command List
- **USER**: Registers a user with the system.
- **AVAIL**: Displays the availability of the room for the entire month.
- **RESERVE**: Makes a reservation for a specified range of days (start and end dates).
- **CANCEL**: Cancels any existing reservation for the user.
- **QUIT**: Ends the session and closes the connection.

## How to Run

### Server:
1. Run the `HotelServer` class to start the server. The server listens on port `8090`.

### Client:
1. Run the `HotelClient` class to start the client. The client connects to the server and allows the user to make reservations or check availability.

2. Follow the prompts to:
   - Enter your name.
   - Check room availability.
   - Reserve the room for a specified date range.
   - Cancel your reservation.

### Example Usage
1. Start the server by running `HotelServer`.
2. Start the client by running `HotelClient`.
3. Follow the on-screen prompts to interact with the reservation system.

## Technologies Used
- Java
- Socket Programming (Client-Server architecture)

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

