package edu.icet.coursework.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SoftDelete;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "receipt")
@SoftDelete
public class ReceiptEntity {
    @Id
    @Column(name = "receipt_id")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "receipt_id_generator"
    )
    @SequenceGenerator(
            name = "receipt_id_generator",
            allocationSize = 1
    )
    private Integer receiptId;

    @OneToOne
    @JoinColumn(name = "order_id")
    private OrderEntity orderEntity;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customerEntity;

    @Column(name = "receipt_date_time")
    private LocalDateTime receiptDateTime;

    @Column(name = "payment_type")
    private String paymentType;
}
