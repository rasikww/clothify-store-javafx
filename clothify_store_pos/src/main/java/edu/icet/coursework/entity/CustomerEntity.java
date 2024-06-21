package edu.icet.coursework.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SoftDelete;

import java.util.List;

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

    @OneToMany(mappedBy = "customerEntity")
    private List<OrderEntity> orderEntities;

    @OneToMany(mappedBy = "customerEntity")
    private List<ReceiptEntity> receiptEntityList;


    public void addOrderEntity(OrderEntity entity) {
        orderEntities.add(entity);
        entity.setCustomerEntity(this);
    }
    public void removeOrderEntity(OrderEntity entity) {
        orderEntities.remove(entity);
        entity.setCustomerEntity(null);
    }

    public void addReceiptEntity(ReceiptEntity entity){
        receiptEntityList.add(entity);
        entity.setCustomerEntity(this);
    }
    public void removeReceiptEntity(ReceiptEntity entity){
        receiptEntityList.remove(entity);
        entity.setCustomerEntity(null);
    }
}
