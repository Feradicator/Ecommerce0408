package com.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentDetails {
    private String paymentMethod;
    private String status;
    private String paymentld;
    private String razorpayPaymentLinkId;
    private String razorpayPaymentLinkReferenceld;
    private String razorpayPaymentLinkStatus;
    private String razorpayPaymentld;


}
