package com.management.HospitalMangementSystem.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {

    @Id
    private Long id;

    private LocalDateTime appointmentTime;


    private String reason;

    private String status;

    private Long doctor_id;

    private Long patient_id;




}
