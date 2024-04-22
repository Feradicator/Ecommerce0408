package com.app.model;

import java.time.LocalDate;
import java.time.LocalDateTime;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class PaymentInformation {
	
	@Column(name = "cardholder_name")
	private String cardholderName;
	@Column(name = "card_number")
	private String cardNumber;
	@Column(name = "expiration_date")
	private LocalDate expirationDate;
	@Column(name = "cvv")
	private String cvv;


}
