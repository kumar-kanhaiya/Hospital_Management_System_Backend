package com.management.HospitalMangementSystem.service;

import com.management.HospitalMangementSystem.Entity.Insurance;
import com.management.HospitalMangementSystem.Entity.Patient;
import com.management.HospitalMangementSystem.repository.InsuranceRepository;
import com.management.HospitalMangementSystem.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InsuranceService {

    private final InsuranceRepository insuranceRepository;
    private final PatientRepository   patientRepository;


    @Transactional // either commited or rollback
    public Patient assignInsuranceToPatient(Insurance insurance , Long patientId){
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(()-> new EntityNotFoundException("Patient not found with id " + patientId));

        patient.setInsurance(insurance);

        insurance.setPatient(patient); // bidirectional consistency maintain

        patientRepository.save(patient);
        return patient;
    }

    @Transactional
    public Patient disaccociateInsuranceFromPatient(Long patientId){
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(()-> new RuntimeException("Patient not found with id " + patientId));

        patient.setInsurance(null);
        return patient;
    }
}
