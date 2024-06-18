package edu.icet.coursework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private Integer orderId;
    private Integer customerId;
    private Integer userId;
    private LocalDateTime orderDateTime;
    private Double totalCost;
    private List<OrderDetail> orderDetails;

}
