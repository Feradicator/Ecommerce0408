package com.app.model;

import java.time.LocalDateTime;
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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String firstName;
	private String lastName;
	private String password;
	private String email;
	private String mobie;
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL)
	private List<Address>address=new ArrayList<>();
	@Embedded
	@ElementCollection
	@CollectionTable(name="payment_info",joinColumns = @JoinColumn(name="user_id"))
	private List<PaymentInformation>paymentInformation=new ArrayList<>();
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL)
	@JsonIgnore
	private List<Rating>ratings=new ArrayList<>();
	@JsonIgnore
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL)
	
	private List<Review>rivews=new ArrayList<>();
	private LocalDateTime createdAt;
	
	
	
	
}
