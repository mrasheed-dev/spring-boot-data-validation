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

4. Important Data Validation Annotations

4.1 @NotNull

Purpose: Ensures that a field must not contain a null value.

It does not reject empty strings or whitespace-only strings.

Example:

@NotNull(message = "Employee ID cannot be null")
private String empid;

Input

Result

null

Rejected

""

Accepted

" "

Accepted

"TYP101"

Accepted

Use case: When a field must contain some value and null is not allowed.

4.2 @NotEmpty

Purpose: Ensures that a value is neither null nor empty.

It rejects empty strings but accepts whitespace-only strings.

Example:

@NotEmpty(message = "Name cannot be empty")
private String name;

Input

Result

null

Rejected

""

Rejected

" "

Accepted

"Khizar"

Accepted

Use case: When a String, collection, or array must contain at least one element.

4.3 @NotBlank

Purpose: Ensures that a String is not null, empty, or whitespace-only.

Example:

@NotBlank(message = "Name is mandatory")
private String name;

Input

Result

null

Rejected

""

Rejected

" "

Rejected

"Khizar"

Accepted

Use case: Best suited for mandatory String fields such as names, usernames, and addresses.

Difference Between @NotNull, @NotEmpty and @NotBlank

Annotation

Null

Empty String

Whitespace

Normal String

@NotNull

Rejected

Accepted

Accepted

Accepted

@NotEmpty

Rejected

Rejected

Accepted

Accepted

@NotBlank

Rejected

Rejected

Rejected

Accepted

4.4 @Email

Purpose: Checks whether an email address has a valid email format.

Example:

@Email(message = "Please enter a valid email")
private String email;

Accepted:

khizar@gmail.com

user123@yahoo.com

Rejected:

khizargmail.com

user@

@gmail.com

Important: @Email does not make a field mandatory. Combine it with @NotBlank if an email is required.

@NotBlank(message = "Email is mandatory")
@Email(message = "Please enter a valid email")
private String email;

4.5 @Pattern

Purpose: Validates a String using a Regular Expression (Regex).

It is useful for validating mobile numbers, employee IDs, passwords, and other formatted strings.

Example 1: Mobile Number

@Pattern(
    regexp = "^[0-9]{10}$",
    message = "Enter a valid 10-digit mobile number"
)
private String mobile;

Accepted:

9876543210

9123456789

Rejected:

12345

98765ABCDE

98765432101

Example 2: Employee ID

@Pattern(
    regexp = "^TYP[0-9]{3}$",
    message = "Employee ID must be like TYP101"
)
private String empid;

Accepted:

TYP101

TYP123

TYP999

Rejected:

TYP1

EMP101

TYPAB1

Example 3: Password

@Pattern(
    regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[@#%&]).*$",
    message = "Password must contain uppercase, lowercase, digit and special character"
)
private String password;

This checks for:

At least one uppercase letter.

At least one lowercase letter.

At least one digit.

At least one special character from @, #, %, &.

Note: This example does not impose a minimum or maximum password length.

4.6 @Size

Purpose: Checks the minimum and maximum size of a String, Collection, Map, or Array.

Example:

@Size(
    min = 3,
    max = 20,
    message = "Name must contain 3 to 20 characters"
)
private String name;

Accepted:

Ali

Khizar

MohdKhizar

Rejected:

Al

A very long name exceeding 20 characters

Use case: Restricting the length of usernames, names, descriptions, and collections.

Important: @Size does not reject null. Combine it with @NotNull or @NotBlank when the field is mandatory.

4.7 @Min

Purpose: Specifies the minimum permitted numerical value.

Example:

@Min(value = 18, message = "Age must be at least 18")
private int age;

Accepted:

18

25

40

Rejected:

17

10

0

Use case: Age restrictions, minimum quantity, and minimum marks.

4.8 @Max

Purpose: Specifies the maximum permitted numerical value.

Example:

@Max(value = 60, message = "Age must not exceed 60")
private int age;

Accepted:

18

30

60

Rejected:

61

75

100

Use case: Maximum age, maximum marks, and quantity restrictions.

Difference Between @Min and @Max

Annotation

Purpose

Example

@Min(18)

Minimum value is 18

18, 25, 30

@Max(60)

Maximum value is 60

30, 50, 60

To restrict age between 18 and 60, use both:

@Min(value = 18, message = "Minimum age is 18")
@Max(value = 60, message = "Maximum age is 60")
private int age;

5. @Valid Annotation

Purpose: Triggers validation on an object using the validation annotations defined in its fields.

Example:

@PostMapping("/save")
public Employee saveEmployee(
        @Valid @RequestBody Employee employee) {

    return employeeRepository.save(employee);
}

Explanation:

@RequestBody converts incoming JSON into a Java object.

@Valid triggers the validation process.

If the data is invalid, Spring raises a validation exception.

If the data is valid, the controller method continues executing.

Without @Valid, validation is not automatically triggered for a typical @RequestBody object.

Import:

import jakarta.validation.Valid;

6. Complete Employee Entity Example

package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import jakarta.validation.constraints.*;

import lombok.Data;

@Entity
@Data
public class Employee {

    @Id
    @NotBlank(message = "Employee ID is mandatory")
    @Pattern(
        regexp = "^TYP[0-9]{3}$",
        message = "Employee ID must be like TYP101"
    )
    private String empid;

    @NotBlank(message = "Name is mandatory")
    @Pattern(
        regexp = "^[A-Za-z ]+$",
        message = "Name should contain only alphabets"
    )
    private String name;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Enter a valid email")
    private String email;

    @NotBlank(message = "Mobile number is mandatory")
    @Pattern(
        regexp = "^[0-9]{10}$",
        message = "Enter a valid 10-digit mobile number"
    )
    private String mobile;

    @Min(value = 18, message = "Minimum age is 18")
    @Max(value = 60, message = "Maximum age is 60")
    private int age;
}

Note: This example uses a manually assigned Employee ID. The @Id annotation identifies the primary key; it does not generate its value.

7. Postman Testing

API Endpoint

POST http://localhost:8080/employees

Select:

Body

Raw

JSON

Valid Request

{
    "empid": "TYP341",
    "name": "Khizar",
    "email": "khizar@gmail.com",
    "mobile": "9876543210",
    "age": 25
}

Expected result: Employee details are saved successfully.

Invalid Request

{
    "empid": "TYP1",
    "name": "",
    "email": "invalid-email",
    "mobile": "12345",
    "age": 15
}

Expected result: HTTP 400 Bad Request with validation error messages.

8. Important Notes

@Pattern works with String values, not primitive int or Integer fields.

@Email checks format but does not make a field mandatory.

@Size checks length or collection size, not whether a value is present.

@Min and @Max are used for numeric values.

@NotBlank is suitable for mandatory String fields.

@Valid triggers validation in the controller.

Validation annotations do not replace database constraints or business-level security checks.

9. Conclusion

Spring Boot Data Validation helps developers maintain data accuracy, prevent invalid inputs, and build reliable REST APIs.

By using Jakarta Bean Validation annotations, developers can implement validation rules easily without writing repetitive manual validation logic.

Learn the annotations, understand their differences, and practise them using real-world projects.

Technologies Used:

Java

Spring Boot

Spring Data JPA

Jakarta Bean Validation

Hibernate Validator

MySQL

Postman

Lombok
