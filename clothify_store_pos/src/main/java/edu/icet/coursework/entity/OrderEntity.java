package edu.icet.coursework.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SoftDelete;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "_order")
@SoftDelete
public class OrderEntity {
    @Id
    @Column(name = "order_id")
    private Integer orderId;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customerEntity;

    @Column(name = "order_datetime")
    private LocalDateTime orderDateTime;

    @Column(name = "total_cost")
    private Double totalCost;

    @OneToMany(mappedBy = "orderEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderDetailEntity> orderDetailEntities;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;



}
