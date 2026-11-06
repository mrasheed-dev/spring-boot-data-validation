Spring Boot Data Validation – Complete Guide

1. Introduction to Data Validation

Data Validation is the process of checking whether the data entered by a user is correct, complete, and follows the required rules before processing or storing it in the database.

In Spring Boot, we use Jakarta Bean Validation to validate user input through annotations.

For example, consider an Employee Management System with the following fields:

Employee ID

Employee Name

Email

Mobile Number

Age

Salary

Suppose a user enters the following details:

{
    "empid": "TYP123",
    "name": "",
    "email": "invalid-email",
    "mobile": "12345",
    "age": -5
}

Without proper validation, incorrect data may enter our application and potentially get stored in the database.

With Data Validation, we can reject invalid data and return meaningful error messages.

2. Why do we use Data Validation?

Data Validation is important in real-world applications for the following reasons:

Data Accuracy: Ensures that users enter correct and meaningful information.

Data Integrity: Prevents invalid data from being stored in the database.

Error Prevention: Detects invalid input before executing business logic.

User Experience: Provides meaningful error messages to users.

Application Reliability: Reduces unexpected errors caused by invalid input.

Business Rule Enforcement: Ensures that application-specific rules are followed.

API Quality: Makes REST APIs more reliable and predictable.

Real-Time Examples

Application

Validation Requirement

Banking Application

Account number must follow a specified format

Employee Management

Employee name should not be empty

E-commerce

Product price must be positive

Registration Form

Email must be valid

Banking / Payment

Transaction amount must be greater than zero

Student Management

Marks must be between 0 and 100

3. Add Data Validation Dependency

Add the following dependency inside pom.xml.

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>

This dependency provides Jakarta Bean Validation support through Hibernate Validator.

For Spring Boot 3.x, use jakarta.validation imports.
