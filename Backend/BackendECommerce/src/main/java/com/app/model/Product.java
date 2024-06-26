package com.app.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.transaction.Transactional;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.bytebuddy.agent.builder.AgentBuilder.InitializationStrategy.SelfInjection.Eager;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data

public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String title;
	private String description;
	
	private int price;
	@Column(name = "discounted_price")
	private int discountedPrice;
	@Column(name="discount_persent")
	private int discountPersent;
	
	private int quantity;
	@Column(name="brand")
	private String brand;
	private String color;
	@Embedded
	@ElementCollection(fetch=FetchType.EAGER)
	@Column(name = "sizes")
	private Set<Size>sizes=new HashSet<>();
	@Column (name="image_url")
	private String imageUrl;
	@JsonIgnore
	@OneToMany(mappedBy="product",cascade=CascadeType.ALL,orphanRemoval = true)
	
	private List<Rating>ratings=new ArrayList<>();
	@OneToMany(mappedBy="product",cascade=CascadeType.ALL,orphanRemoval = true,fetch=FetchType.EAGER)
	
	private List<Review>reviews=new ArrayList<>();
	@Column(name="num_ratings")
	private int numRatings;
	@ManyToOne()
	@JoinColumn(name="category_id")
	private Category category;
	private LocalDateTime createdAt;
	
	
	
	
	
	
	

	

}
