Arithmetic Calculator REST API
Overview
This project implements a REST API for an Arithmetic Calculator with functionalities such as addition, subtraction, multiplication, division, square root, and random string generation. Each operation has a specific cost, which is deducted from the user's balance. The project uses Java and Spring Boot for the backend, MySQL for the database, and integrates with a third-party service for random string generation.

Tech Stack
Java
Spring Boot
MySQL
JPA/Hibernate
JWT for Authentication
Maven for Build
Swagger for API Documentation
Setup Instructions
Prerequisites
Java 17 or higher
Maven
MySQL
Installation
Clone the repository:

sh
# requirment 
Java 17 
maven 3.97 
git clone <repository-url>
cd calculator-backend
Set up MySQL Database:

sql

CREATE DATABASE calculator_db;
CREATE USER 'calculator_user'@'localhost' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON calculator_db.* TO 'calculator_user'@'localhost';
FLUSH PRIVILEGES;
Update application.properties:

properties

spring.datasource.url=jdbc:mysql://localhost:3306/calculator_db
spring.datasource.username=calculator_user
spring.datasource.password=password
spring.jpa.hibernate.ddl-auto=update

# JWT Properties
jwt.secret=mySecretKey
jwt.expiration=3600000
Build and run the application:

sh

mvn clean install
mvn spring-boot:run
Swagger UI
After starting the application, you can access the Swagger UI for API documentation at:

bash
# for live Open Api Specification visit 
[Live Swagger UI](http://203.161.60.224:8000//swagger-ui/index.html#/)

API Endpoints
Authentication
Register
URL: /api/v1/auth/register
Method: POST
Request Body:
json

{
  "email": "user@example.com",
  "password": "password"
}
Response:
json
{
  "token": "jwt-token"
}
Login
URL: /api/v1/auth/login
Method: POST
Request Body:
json
{
  "email": "user@example.com",
  "password": "password"
}
Response:
json
{
  "token": "jwt-token"
}
User Operations
Add Record
URL: /records/user/{userId}/record-add
Method: POST
Request Body:
json
{
  "balance": 1000,
  "operationAmount": 10,
  "operation": {
    "type": "addition",
    "cost": 3
  }
}
Response:
json
{
  "message": "addition success"
}
Subtract Record
URL: /records/user/{userId}/record-sub
Method: POST
Request Body:
json

{
  "balance": 1000,
  "operationAmount": 10,
  "operation": {
    "type": "subtraction",
    "cost": 3
  }
}
Response:
json

{
  "message": "subtraction success"
}
Multiply Record
URL: /records/user/{userId}/record-multi
Method: POST
Request Body:
json

{
  "balance": 1000,
  "operationAmount": 10,
  "operation": {
    "type": "multiplication",
    "cost": 3
  }
}
Response:
json

{
  "message": "multiplication success"
}
Divide Record
URL: /records/user/{userId}/record-div
Method: POST
Request Body:
json

{
  "balance": 1000,
  "operationAmount": 10,
  "operation": {
    "type": "division",
    "cost": 3
  }
}
Response:
json

{
  "message": "division success"
}
Get All Records
URL: /records/
Method: GET
Request Parameters:
pageNumber (default: 0)
pageSize (default: 10)
sortBy (default: "date")
sortDir (default: "asc")
Response:
json

{
  "records": [
    {
      "id": 1,
      "operationId": 1,
      "userId": 1,
      "amount": 10,
      "userBalance": 997,
      "operationResponse": "10 + 10 = 20",
      "date": "2024-07-16T00:00:00"
    }
  ],
  "pageNumber": 0,
  "pageSize": 10,
  "totalElements": 1,
  "totalPages": 1,
  "lastPage": true
}
Delete Record
URL: /records/{recordId}
Method: DELETE
Response:
json

{
  "message": "Record soft-deleted successfully"
}

Running Tests
To run the unit tests, use the following command:

sh


This README file includes basic setup instructions, API endpoints, and examples for running and testing the application. Adjust it as needed based on your specific implementation and deployment environment.


