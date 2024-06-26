package edu.icet.coursework.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Receipt {
    private Integer receiptId;
    private Order order;
    private Customer customer;
    private LocalDateTime receiptDateTime;
    private String paymentType;
}
