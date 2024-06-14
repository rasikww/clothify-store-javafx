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
@Entity(name = "supplier")
@SoftDelete
public class SupplierEntity {
    @Id
    @Column(name = "supplier_id")
    private Integer supplierId;
    private String name;
    private String company;
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;
}
