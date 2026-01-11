package com.management.HospitalMangementSystem.service;

import com.management.HospitalMangementSystem.Entity.Appointment;
import com.management.HospitalMangementSystem.Entity.Doctor;
import com.management.HospitalMangementSystem.Entity.Patient;
import com.management.HospitalMangementSystem.repository.AppointmentRepository;
import com.management.HospitalMangementSystem.repository.DoctorRepository;
import com.management.HospitalMangementSystem.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    private final DoctorRepository doctorRepository;

    private final PatientRepository patientRepository;

    @Transactional
    public Appointment createNewAppointment(Appointment appointment , Long doctorId , Long patientId){
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(()-> new EntityNotFoundException("Doctor not found with id " + doctorId));

        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(()-> new EntityNotFoundException("Patient not found with id " + patientId));

        if(appointment.getId() != null){
            throw new IllegalArgumentException("Appointment should not have id ");
        }

        appointment.setPatient(patient);
        appointment.setDoctor(doctor);

        patient.getAppointments().add(appointment); // to maintain bidirectional consistency
        return appointmentRepository.save(appointment);
    }

    @Transactional
    public Appointment reAssignAppointmentToAnotherDoctor(Long appointmentId , Long doctorId){
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(()-> new EntityNotFoundException("Appointment not found with id " + appointmentId));
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(()-> new EntityNotFoundException("Doctor not found with id" + doctorId));

        appointment.setDoctor(doctor);
        return  appointment;
    }
}
