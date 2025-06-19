Employee Management System
An Employee Management System built using Spring Boot, exposing a REST API for managing employees and handling employee leave requests. The application includes CRUD operations for employees and leave requests and is documented using Swagger UI.

#Features
Employee Management
Create, update, delete, and retrieve employees.

#Manage employee details such as:
First name
Last name
Email
Department
Employee Leave Request Management

#Employees can:
Apply for leave with a reason and dates.
View their leave requests.

#Admins can:
Update the status of leave requests (e.g., Approved, Rejected).
Additional Features
REST API endpoints with Swagger UI documentation.
Built-in exception handling for invalid operations.

#Technologies Used
Java: Backend language.
Spring Boot: Framework for building the application.
Spring Data JPA: For database interactions.
H2 Database: In-memory database for development and testing.
Swagger 3: API documentation and testing.
Prerequisites
Java 17 or later.
Maven 3.6+.
IntelliJ IDEA or another IDE.
Postman (optional, for testing APIs).
Installation and Setup
Clone the Repository:

bash
Copy code
git clone https://github.com/your-username/employee-management-system.git
cd employee-management-system
Configure the Application:

By default, the application uses an H2 in-memory database.
No additional configuration is needed for development.
Build and Run:

To build the project:
bash
Copy code
mvn clean install
To run the application:
bash
Copy code
mvn spring-boot:run
Access the Application:

Swagger UI: http://localhost:8080/swagger-ui/index.html
