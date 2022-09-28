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
@Table(name = "inpatient_room")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class InpatientRoom extends PanacheEntityBase {
    @Id
    @SequenceGenerator(name = "inpatientroomSequence", sequenceName = "inpatientroom_sequence",
            allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "inpatientroomSequence", strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false, unique = true)
    private Long idRoom;

    @Column(name = "room_prefix", nullable = false)
    private String roomPrefix;

    @Column(name = "room_number", nullable = false)
    private String roomNumber;

    @Column(name = "room_category", nullable = false)
    private String roomCategory;

    @Column(name = "is_empty", nullable = false)
    private Boolean isEmpty;

    @Column(name = "created_at")
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;
}
