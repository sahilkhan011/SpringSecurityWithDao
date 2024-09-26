# SpringSecurityWithDao

## Overview
This project is a Spring Boot application that implements security features using the DAO (Data Access Object) pattern. It focuses on securing the application routes and managing user authentication and authorization without the use of JWT tokens.

## Features
- User authentication using Spring Security.
- DAO pattern for data access and management.
- Role-based access control to secure application routes.
- Basic user registration and login functionality.

## Implementation Steps

1. **Project Setup**:
   - Created a new Spring Boot project using Spring Initializr.
   - Added dependencies for Spring Security and Spring Data JPA.

2. **DAO Layer**:
   - Implemented the DAO pattern to interact with the database.
   - Created a User entity and a UserRepository interface for data access.

3. **Spring Security Configuration**:
   - Configured Spring Security to secure the application.
   - Defined authentication and authorization rules in a security configuration class.

4. **Service Layer**:
   - Created a UserService class to handle user registration and authentication logic.
   - Integrated the service layer with the DAO layer for data management.

5. **Controller Layer**:
   - Developed REST controllers for user registration and login.
   - Implemented endpoints for securing access based on user roles.

6. **Testing the Application**:
   - Tested user registration and login functionalities.
   - Verified access control by attempting to access secured endpoints with different user roles.

