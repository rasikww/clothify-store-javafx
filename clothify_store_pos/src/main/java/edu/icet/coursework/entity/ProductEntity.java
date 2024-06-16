package edu.icet.coursework.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SoftDelete;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "product")
@SoftDelete
public class ProductEntity {
    @Id
    @Column(name = "product_id")
    private Integer productId;
    @Column(name = "supplier_id")
    private Integer supplierId;
    private String name;
    private String description;
    private Double price;
    @Column(name = "stock_quantity")
    private Integer stockQuantity;
    @Column(name = "product_image_link")
    private String productImageLink;
    private String category;
}
