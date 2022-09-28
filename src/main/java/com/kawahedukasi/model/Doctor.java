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
@Table(name = "doctor")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Doctor extends PanacheEntityBase {
    @Id
    @SequenceGenerator(name = "doctorSequence", sequenceName = "doctor_sequence",allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "doctorSequence", strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false, unique = true)
    private Long idDoctor;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "is_specialist", nullable = true)
    private boolean isSpecialist;

    @Column(name = "specialist_name", nullable = false)
    private String specialistName;

    @Column(name = "status", nullable = true)
    private String status;

    @Column(name = "salary", nullable = false)
    private Long salary;

    @Column(name = "created_at")
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;

}
