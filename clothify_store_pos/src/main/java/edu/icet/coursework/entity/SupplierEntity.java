package edu.icet.coursework.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SoftDelete;

import java.util.List;

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

    @OneToMany(mappedBy = "supplierEntity")
    private List<ProductEntity> productEntities;

    public void addProductEntity(ProductEntity entity){
        productEntities.add(entity);
        entity.setSupplierEntity(this);
    }
    public void removeProductEntity(ProductEntity entity){
        productEntities.remove(entity);
        entity.setSupplierEntity(null);
    }

}
