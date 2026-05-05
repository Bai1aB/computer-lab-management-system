# Computer Lab Management System

## Project Description

Computer Lab Management System is a Java Spring Boot web application for managing computer laboratories.

The system helps to manage lab rooms, computers, operating systems, users, bookings, and maintenance tickets.  
It can be used in schools, universities, or computer labs where computers need to be tracked, booked, and maintained.

## Main Features

- Manage users
- Manage lab rooms
- Manage operating systems
- Manage computers
- Create and manage bookings
- Create and manage maintenance tickets
- Filter computers by status
- Filter computers by lab room
- REST API support
- PostgreSQL database integration

## Technologies Used

- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- PostgreSQL
- Lombok
- Maven
- Postman
- Git and GitHub

## Project Architecture

The project uses layered architecture:

Controller -> Service -> Repository -> Database

Controller receives HTTP requests from the client.

Service contains the business logic of the application.

Repository communicates with the database using Spring Data JPA.

## Business Logic

The project is not only simple CRUD.

Main business rules:

1. A computer cannot be booked if it is BROKEN or under MAINTENANCE.
2. When a booking is created, the computer status changes to BOOKED.
3. When a maintenance ticket is created, the computer status changes to MAINTENANCE.

## Main Entities

The project contains six main entities:

1. User
2. LabRoom
3. OperatingSystem
4. Computer
5. Booking
6. MaintenanceTicket

## Database Relationships

- One LabRoom can have many Computers.
- One OperatingSystem can be installed on many Computers.
- One User can have many Bookings.
- One Computer can have many Bookings.
- One Computer can have many MaintenanceTickets.

## API Endpoints

### Users

GET /api/users  
GET /api/users/{id}  
POST /api/users  
PUT /api/users/{id}  
DELETE /api/users/{id}

### Lab Rooms

GET /api/labrooms  
GET /api/labrooms/{id}  
POST /api/labrooms  
PUT /api/labrooms/{id}  
DELETE /api/labrooms/{id}

### Operating Systems

GET /api/operating-systems  
GET /api/operating-systems/{id}  
POST /api/operating-systems  
PUT /api/operating-systems/{id}  
DELETE /api/operating-systems/{id}

### Computers

GET /api/computers  
GET /api/computers/{id}  
POST /api/computers  
PUT /api/computers/{id}  
DELETE /api/computers/{id}  
GET /api/computers/status/{status}  
GET /api/computers/labroom/{labRoomId}

### Bookings

GET /api/bookings  
GET /api/bookings/{id}  
POST /api/bookings  
PUT /api/bookings/{id}  
DELETE /api/bookings/{id}

### Maintenance Tickets

GET /api/maintenance-tickets  
GET /api/maintenance-tickets/{id}  
POST /api/maintenance-tickets  
PUT /api/maintenance-tickets/{id}  
DELETE /api/maintenance-tickets/{id}

## Example JSON Requests

### Create User

{
"fullName": "Baidarkhan Student",
"email": "baidar@example.com",
"role": "STUDENT"
}

### Create Lab Room

{
"roomNumber": "305",
"building": "Main Building",
"capacity": 25
}

### Create Operating System

{
"name": "Windows",
"version": "11",
"architecture": "64-bit"
}

### Create Computer

{
"name": "PC-101",
"cpu": "Intel Core i5",
"ram": "16 GB",
"storage": "512 GB SSD",
"status": "AVAILABLE",
"labRoom": {
"id": 1
},
"operatingSystem": {
"id": 1
}
}

### Create Booking

{
"startTime": "2026-05-05T10:00:00",
"endTime": "2026-05-05T12:00:00",
"purpose": "Operating Systems practical lesson",
"user": {
"id": 1
},
"computer": {
"id": 1
}
}

### Create Maintenance Ticket

{
"description": "Computer has keyboard problem",
"priority": "MEDIUM",
"computer": {
"id": 1
}
}

## How to Run the Project

1. Clone the repository.

git clone https://github.com/your-username/computer-lab-management-system.git

2. Open the project in IntelliJ IDEA.

3. Create a PostgreSQL database:

CREATE DATABASE computer_lab_db;

4. Create application.properties in this folder:

src/main/resources/application.properties

5. Copy the content from application-example.properties and replace YOUR_PASSWORD_HERE with your PostgreSQL password.

6. Run the main class:

ComputerlabApplication.java

7. Test the API using Postman.

## Author

This project was developed individually as a final project for Computer Architecture and Operating Systems course.