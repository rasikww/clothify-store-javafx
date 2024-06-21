package edu.icet.coursework.dto;


import java.time.LocalDateTime;

public class Receipt {
    private Integer receiptId;
    private Order order;
    private Customer customer;
    private LocalDateTime receiptDateTime;
    private String paymentType;
}
