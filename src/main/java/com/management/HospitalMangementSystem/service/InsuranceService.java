package com.management.HospitalMangementSystem.service;

import com.management.HospitalMangementSystem.Entity.Insurance;
import com.management.HospitalMangementSystem.Entity.Patient;
import com.management.HospitalMangementSystem.repository.InsuranceRepository;
import com.management.HospitalMangementSystem.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InsuranceService {

    @Autowired
    private InsuranceRepository insuranceRepository;

    @Autowired
    private PatientRepository   patientRepository;

    public Patient assignInsuranceToPatient(Insurance insurance , Long patientId){
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(()-> new EntityNotFoundException("Patient not found with id " + patientId));

        patient.setInsurance(insurance);

        insurance.setPatient(patient); // bidirectional consistency maintain

        patientRepository.save(patient);
        return patient;
    }
}
