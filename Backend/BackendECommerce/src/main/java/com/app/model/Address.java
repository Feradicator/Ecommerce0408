package com.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
public class Address {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Column(name="first_name")
	private String firstName;
	@Column(name="last_name")
	private String lastName;
	@Column(name = "street_address")
	private String streetAddress;
	@Column(name = "city")
	private String city;
	@Column(name = "state")
	private String state;
	@Column(name="zip_code")
	private String zipCode;
	@ManyToOne
	@JoinColumn(name="user_id")
	@JsonIgnore
	private User user;
	private String mobile;
	


	
	
	

}

