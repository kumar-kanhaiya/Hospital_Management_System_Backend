package com.management.HospitalMangementSystem.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false , unique = true , length = 100 )
    private String name;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDate createdAt;

    @ManyToMany
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @OneToOne // department owns the relationship
    private Doctor headDoctor;
}
