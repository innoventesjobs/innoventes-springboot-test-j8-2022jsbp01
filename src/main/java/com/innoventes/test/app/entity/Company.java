package com.innoventes.test.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "company")
@Entity
public class Company extends BaseEntity {

	@Id
	@SequenceGenerator(sequenceName = "company_seq", allocationSize = 1, name = "company_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "company_seq")
	private Long id;
    
	//task1
	@NotBlank(message = "Company name is mandatory")
	@Size(min = 5, message = "Company name should be at least 5 characters")
	@Column(name = "company_name")
	private String companyName;
    
	//task1
	@NotBlank(message = "Email is mandatory")
	@Email(message = "Email should be valid")
	@Column(name = "email")
	private String email;
	
	//task1
	@Min(value = 0, message = "Strength should be a positive number or zero")
	@Column(name = "strength")
	private Integer strength;
	
	//task1
	@Column(name = "website_url")
	private String webSiteURL;
	
	//task2
	@Pattern(regexp = "^[a-zA-Z]{2}\\d{2}[ENen]$", message = "Company code must be 5 characters: first 2 are letters, next 2 are digits, last is E or N.")
	@Column(name = "company_code", unique = true)
	private String companyCode;
}
