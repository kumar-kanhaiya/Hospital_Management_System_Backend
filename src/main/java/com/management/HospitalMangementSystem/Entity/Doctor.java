package com.management.HospitalMangementSystem.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false , length = 100)
    String name ;

    @Column(length = 100)
    private String specialization;

    @Column(nullable = false , unique = true , length = 100)
    private String email;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDate createdAt;

    @OneToMany(mappedBy = "doctor")
    private List<Appointment> doctors;


}
