package com.management.HospitalMangementSystem.repository;

import com.management.HospitalMangementSystem.Entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}