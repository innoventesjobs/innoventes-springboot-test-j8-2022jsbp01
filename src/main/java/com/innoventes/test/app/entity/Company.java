package com.innoventes.test.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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

	@Column(name = "company_name")
	@NotNull (message = "company name is mandatory")
	@NotEmpty(message = "Company name cannot be empty")
	@Size(min = 5, message = "Company name should be least 5 characters long")
	private String companyName;

	@Column(name = "email")
	@NotNull(message = "Email is mandatory")
	@Email(message = "Email should be valid")
	private String email;
	
	@Column(name = "strength")
	@PositiveOrZero(message = "Strength should be a positive number or zero")
	private Integer strength;
	
	@Column(name = "website_url")
	private String webSiteURL;
}
