package com.app.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
@Entity
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
