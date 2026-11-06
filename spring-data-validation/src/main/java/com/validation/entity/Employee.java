package com.validation.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Data
public class Employee
{
	@Id
	@Pattern(regexp = "^TYP[0-9]{3}$",
			 message = "enter field like TYP122")
	private String empid;
	
	@NotBlank(message = "Name is mandatory...")
	@Pattern(regexp = "^[A-Za-z]+$")
	private String name;
	
	@NotBlank(message = "email is mandatory...")
	@Email(message = "Email valid email...")
	private String email;
	
	@NotBlank(message = "Mobile No is mandatory...")
	@Pattern(regexp = "^[0-9]{10}$", 
			 message = "please enter 10-digits mobile number...")
	private String mobile;
}
