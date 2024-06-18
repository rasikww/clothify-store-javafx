package edu.icet.coursework.dto;

import edu.icet.coursework.entity.OrderEntity;
import edu.icet.coursework.entity.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetail {
    private Integer quantity;
    private Integer orderId;
    private Product product;
    private Double totalPrice;
}
