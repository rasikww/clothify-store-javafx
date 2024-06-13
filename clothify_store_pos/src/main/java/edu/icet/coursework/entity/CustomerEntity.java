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
@Entity(name = "customer")
@SoftDelete
public class CustomerEntity {
    @Id
    @Column(name = "customer_id")
    private Integer customerId;
    private String name;
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;
}
