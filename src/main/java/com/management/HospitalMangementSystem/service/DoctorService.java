package com.management.HospitalMangementSystem.service;

import com.management.HospitalMangementSystem.Entity.Doctor;
import com.management.HospitalMangementSystem.Entity.User;
import com.management.HospitalMangementSystem.Entity.type.RoleType;
import com.management.HospitalMangementSystem.dto.DoctorResponseDto;
import com.management.HospitalMangementSystem.dto.OnBoardRequestDto;
import com.management.HospitalMangementSystem.repository.DoctorRepository;
import com.management.HospitalMangementSystem.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public List<DoctorResponseDto> getAllDoctor(){
        return doctorRepository.findAll()
                .stream()
                .map(doctor -> modelMapper.map(doctor , DoctorResponseDto.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public DoctorResponseDto onBoardNewDoctor(OnBoardRequestDto onBoardRequestDto) {
        User user = userRepository.findById(onBoardRequestDto.getUserId()).orElseThrow();

        if(doctorRepository.existsById(onBoardRequestDto.getUserId())){
            throw new IllegalArgumentException("Already a Doctor");
        }

        Doctor doctor = Doctor.builder()
                .name(onBoardRequestDto.getName())
                .specialization(onBoardRequestDto.getSpecialization())
                .email(user.getUsername())
                .user(user)
                .build();

        user.getRoles().add(RoleType.DOCTOR);
        return modelMapper.map(doctorRepository.save(doctor) , DoctorResponseDto.class);


    }
}
