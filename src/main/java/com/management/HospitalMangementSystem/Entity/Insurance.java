package com.management.HospitalMangementSystem.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Insurance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false , unique = true , length = 50)
    private String policyNumber;

    @Column(nullable = false , length = 100)
    private String provider;

    @Column(nullable = false)
    private LocalDate validUntil;

    @CreationTimestamp
    @Column(updatable = false , nullable = false)
    private LocalDate createdAt;

    // Bidirectional patient can access with the help of insurance id
    @OneToOne(mappedBy = "insurance") // this is called the inverse side
    private Patient patient;

}
