package edu.icet.coursework.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SoftDelete;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "order_detail")
@SoftDelete
public class OrderDetailEntity {

    @Id
    @Column(name = "order_detail_id")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "order_detail_id_generator"
    )
    @SequenceGenerator(
            name = "order_detail_id_generator",
            initialValue = 1,
            allocationSize = 1
    )
    private Long orderDetailId;

    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity orderEntity;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity productEntity;

    @Column(name = "total_price")
    private Double totalPrice;
}
