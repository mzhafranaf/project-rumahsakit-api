package com.kawahedukasi.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.Roles;
import io.quarkus.security.jpa.UserDefinition;
import io.quarkus.security.jpa.Username;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name =  "users")
@UserDefinition
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User extends PanacheEntityBase {
    @Id
//    @GenericGenerator(name = "UUID",
//            strategy = "org.hibernate.id.UUIDGenerator")
//    @GeneratedValue(generator = "UUID")
    @SequenceGenerator(name = "userSequence", sequenceName = "user_sequence",allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "userSequence", strategy = GenerationType.SEQUENCE)
    @Column(name = "id", length = 36, nullable = false, unique = true)
    private Long idUser;

    @Column(name = "name", nullable = false)
    private String name;

    @Username
    @Column(name = "username", nullable = false)
    private String username;

    @Password
    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Roles
    @Column(name = "user_type")
    private String userType;

    @Column(name = "created_at")
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;
}
