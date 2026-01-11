package com.management.HospitalMangementSystem.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    @JoinTable(
            name = "my_dpt_doctors",
            joinColumns = @JoinColumn(name = "dpt_id"),  // owning side column name
            inverseJoinColumns = @JoinColumn(name = "doctor_id") // inverse side column name
    )
    private Set<Doctor> doctors = new HashSet<>();

    @OneToOne // department owns the relationship
    private Doctor headDoctor;
}
