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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String firstName;
	private String lastName;
	private String password;
	private String role;
	private String email;
	private String mobile;
	
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL,orphanRemoval = true)
	@JsonIgnore
	private List<Address>address=new ArrayList<>();
	@Embedded
	@ElementCollection
	@JsonIgnore
	@CollectionTable(name="payment_info",joinColumns = @JoinColumn(name="user_id"))
	private List<PaymentInformation>paymentInformation=new ArrayList<>();
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL,orphanRemoval = true)
	@JsonIgnore
	private List<Rating>ratings=new ArrayList<>();
	@JsonIgnore
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL,orphanRemoval = true)
	
	private List<Review>rivews=new ArrayList<>();
	private LocalDateTime createdAt;
	
	
	
	
}
