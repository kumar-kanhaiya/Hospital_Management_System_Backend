package com.management.HospitalMangementSystem.service;

import com.management.HospitalMangementSystem.Entity.Appointment;
import com.management.HospitalMangementSystem.Entity.Doctor;
import com.management.HospitalMangementSystem.Entity.Patient;
import com.management.HospitalMangementSystem.dto.AppointmentResponseDto;
import com.management.HospitalMangementSystem.dto.CreateAppointmentRequestDto;
import com.management.HospitalMangementSystem.repository.AppointmentRepository;
import com.management.HospitalMangementSystem.repository.DoctorRepository;
import com.management.HospitalMangementSystem.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public AppointmentResponseDto createNewAppointment(CreateAppointmentRequestDto createAppointmentRequestDto){
        Long doctorId = createAppointmentRequestDto.getDoctorId();
        Long patientId = createAppointmentRequestDto.getPatientId();

        Patient patient = patientRepository.findById(patientId).orElseThrow(
                ()-> new EntityNotFoundException("Patient not found with id " + patientId)
        );
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(
                ()-> new EntityNotFoundException("Doctor not found with id" + doctorId));

        Appointment appointment = Appointment.builder()
                .reason(createAppointmentRequestDto.getReason())
                .appointmentTime(createAppointmentRequestDto.getAppointmentTime())
                .build();

        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        patient.getAppointments().add(appointment);
        appointment = appointmentRepository.save(appointment);
        return modelMapper.map(appointment,AppointmentResponseDto.class);
    }

    @Transactional
    public Appointment reAssignAppointmentToAnotherDoctor(Long appointmentId , Long doctorId){
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow(
                ()-> new EntityNotFoundException("Appointment not found with id " + appointmentId)
        );
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(
                ()-> new EntityNotFoundException("Doctor not found with id " + doctorId)
        );
        appointment.setDoctor(doctor);
        doctor.getAppointments().add(appointment);
        return appointment;
    }

    public List<AppointmentResponseDto> getAllAppointmentOfDoctor(Long doctorId){
        Doctor doctor =  doctorRepository.findById(doctorId).orElseThrow(
                ()-> new EntityNotFoundException("Doctor not found with id" + doctorId)
        );

        return doctor.getAppointments()
                .stream()
                .map(appointment -> modelMapper.map(appointment , AppointmentResponseDto.class))
                .collect(Collectors.toList());
    }
}
