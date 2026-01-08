package com.management.HospitalMangementSystem.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
    private Long id;

    private String name;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDate createdAt;


}
