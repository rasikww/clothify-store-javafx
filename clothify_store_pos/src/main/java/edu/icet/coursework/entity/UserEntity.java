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
@Entity(name = "user")
@SoftDelete
public class UserEntity {
    @Id
    private Integer userId;

    private String name;

    @Column(name = "phone_number")
    private String phoneNumber;

    private String email;

    @Column(name = "password_hash")
    private String passwordHash;

    @Column(name = "is_admin")
    private Boolean isAdmin;

    @OneToMany(mappedBy = "userEntity")
    private List<OrderEntity> orderEntities;

}
