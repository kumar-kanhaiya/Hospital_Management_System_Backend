package com.management.HospitalMangementSystem.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Insurance {

    @Id
    private Long Id;

    private String policyNumber;

    private String provider;

    private LocalDate validUntil;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDate createdAt;

}
