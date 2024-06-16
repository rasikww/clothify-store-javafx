package edu.icet.coursework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private Integer productId;
    private Integer supplierId;
    private String name;
    private String description;
    private Double price;
    private Integer stockQuantity;
    private String productImageLink;
    private String category;
}
