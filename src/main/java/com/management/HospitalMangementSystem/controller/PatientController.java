package com.management.HospitalMangementSystem.controller;

import com.management.HospitalMangementSystem.dto.AppointmentResponseDto;
import com.management.HospitalMangementSystem.dto.CreateAppointmentRequestDto;
import com.management.HospitalMangementSystem.dto.PatientResponseDto;
import com.management.HospitalMangementSystem.service.AppointmentService;
import com.management.HospitalMangementSystem.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;
    private final AppointmentService appointmentService;

    @PostMapping("/appointments")
    public ResponseEntity<AppointmentResponseDto> createNewAppointment(@RequestBody CreateAppointmentRequestDto createAppointmentRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(appointmentService.createNewAppointment(createAppointmentRequestDto));
    }

    @GetMapping("/profile/{id}")
    private ResponseEntity<PatientResponseDto> getPatientProfile(@PathVariable Long id) {
        Long patientId = 4L;
        return ResponseEntity.ok(patientService.getPatientById(id));
    }
}
