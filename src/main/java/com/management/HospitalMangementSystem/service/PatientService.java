package com.management.HospitalMangementSystem.service;

import com.management.HospitalMangementSystem.Entity.Patient;
import com.management.HospitalMangementSystem.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientService {

    @Autowired
    private final PatientRepository patientRepository;

    @Transactional
    public Patient getPatientById(Long id){
        Patient requiredPatient = patientRepository.findById(id).orElseThrow();
        Patient requiredPatient2 = patientRepository.findById(id).orElseThrow();
        return requiredPatient;
    }
}
