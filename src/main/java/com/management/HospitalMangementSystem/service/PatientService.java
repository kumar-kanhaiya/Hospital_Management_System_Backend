package com.management.HospitalMangementSystem.service;

import com.management.HospitalMangementSystem.Entity.Patient;
import com.management.HospitalMangementSystem.dto.PatientResponseDto;
import com.management.HospitalMangementSystem.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
//import org.modelmapper.ModelMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;


    @Transactional
    public PatientResponseDto getPatientById(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found with id " + id));

//        return PatientResponseDto.builder()
//                .name(patient.getName())
//                .gender(patient.getGender())
//                .birthDate(patient.getBirthDate())
//                .bloodGroup(patient.getBloodGroup())
//                .build();
        return modelMapper.map(patient , PatientResponseDto.class);
    }

    //  pagination
    public List<PatientResponseDto> getAllPatient(Integer pageNumber , Integer pageSize){
        return patientRepository.findAllPatients(PageRequest.of(pageNumber , pageSize))
                .stream()
                .map(patient -> modelMapper.map(patient , PatientResponseDto.class))
                .collect(Collectors.toList());
    }
}

