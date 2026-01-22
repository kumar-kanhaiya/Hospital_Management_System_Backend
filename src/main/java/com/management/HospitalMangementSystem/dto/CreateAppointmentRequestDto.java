package com.management.HospitalMangementSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAppointmentRequestDto {
    Long doctorId;
    Long patientId;
    LocalDateTime appointmentTime;
    String reason;
}
