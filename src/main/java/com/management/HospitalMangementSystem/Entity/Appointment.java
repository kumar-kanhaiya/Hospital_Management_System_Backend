package com.management.HospitalMangementSystem.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime appointmentTime;

    @Column(length = 500)
    private String reason;


    private String status;


    @ManyToOne
    @JoinColumn( name = "patient_id" , nullable = false) // required field patient are required and not nullable
    private Patient patient;

    @ManyToOne()
    @JoinColumn(name = "doctor_id" , nullable = false)
    private Doctor doctor;


}
