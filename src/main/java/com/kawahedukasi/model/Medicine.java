package com.kawahedukasi.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "medicine")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Medicine extends PanacheEntityBase {
    @Id
    @SequenceGenerator(name = "medicineSequence", sequenceName = "medicine_sequence",
            allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "medicineSequence", strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false, unique = true)
    private Long idMedicine;

    @Column(name = "medicine_name", nullable = false)
    private String medicineName;

    @Column(name = "production", nullable = false)
    private String production;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "description", nullable = true)
    private String description;

    @Column(name = "created_at")
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;
}
