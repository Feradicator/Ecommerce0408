package com.app.model;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User {
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String firstName;
	private String lastName;
	private String password;
	private String email;
	private String mobie;
	@OneToMany(mappedBy="u@OneToMany(mappedBy=\"user\",cascade=CascadeType.ALL)\n"
			+ "	@JsonIgnoreser",cascade=CascadeType.ALL)
	private List<Address>address=new ArrayList<>();
	@Embedded
	@ElementCollection
	@CollectionTable(name="payment_info",joinColumns = @JoinColumn(name="user_id"))
	private List<PaymentInformation>paymentInformation=new ArrayList<>();
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL)
	@JsonIgnore
	private List<Rating>ratings=new ArrayList<>();
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL)
	@JsonIgnore
	private List<Review>rivews=new ArrayList<>();
	private LocalDateTime createdAt;
	
	
	
	
}
