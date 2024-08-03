package com.innoventes.test.app.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CompanyDTO {

	private Long id;
	
	//task1
	@NotBlank(message = "Company name is mandatory")
	@Size(min = 5, message = "Company name should be at least 5 characters")
	private String companyName;
	
	//task1
	@NotBlank(message = "Email is mandatory")
	@Email(message = "Email should be valid")
	private String email;
	
	//task1
	@Min(value = 0, message = "Strength should be a positive number or zero")
	private Integer strength;

	private String webSiteURL;
	
	//task2
	@Pattern(regexp = "^[a-zA-Z]{2}\\d{2}[ENen]$", message = "Company code must be 5 characters: first 2 are letters, next 2 are digits, last is E or N.")
	private String companyCode;
}
