package com.management.HospitalMangementSystem.controller;

import com.management.HospitalMangementSystem.dto.DoctorResponseDto;
import com.management.HospitalMangementSystem.dto.OnBoardRequestDto;
import com.management.HospitalMangementSystem.dto.PatientResponseDto;
import com.management.HospitalMangementSystem.service.DoctorService;
import com.management.HospitalMangementSystem.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final PatientService patientService; // ✅ FINAL
    private final DoctorService doctorService;

    @GetMapping("/patients")
    public ResponseEntity<List<PatientResponseDto>> getAllPatients(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        return ResponseEntity.ok(patientService.getAllPatient(page, size));
    }

    @PostMapping("/onBoardNewDoctor")
    public ResponseEntity<DoctorResponseDto> onBoardNewDoctor(@RequestBody OnBoardRequestDto onBoardRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(doctorService.onBoardNewDoctor(onBoardRequestDto));
    }
}
