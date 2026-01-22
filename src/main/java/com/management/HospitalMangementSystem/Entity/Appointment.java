package com.management.HospitalMangementSystem.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
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
    @ToString.Exclude
    @JoinColumn( name = "patient_id" , nullable = false) // required field patient are required and not nullable
    private Patient patient;

    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "doctor_id" , nullable = false)
    private Doctor doctor;


}
