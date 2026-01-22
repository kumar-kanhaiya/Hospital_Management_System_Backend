package com.management.HospitalMangementSystem.service;

import com.management.HospitalMangementSystem.dto.DoctorResponseDto;
import com.management.HospitalMangementSystem.repository.DoctorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final ModelMapper modelMapper;

    public List<DoctorResponseDto> getAllDoctor(){
        return doctorRepository.findAll()
                .stream()
                .map(doctor -> modelMapper.map(doctor , DoctorResponseDto.class))
                .collect(Collectors.toList());
    }

}
