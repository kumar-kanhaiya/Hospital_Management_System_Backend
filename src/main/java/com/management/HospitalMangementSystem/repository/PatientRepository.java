package com.management.HospitalMangementSystem.repository;

import com.management.HospitalMangementSystem.Entity.Patient;
import com.management.HospitalMangementSystem.type.BloodGroupType;
import org.hibernate.annotations.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    Patient findByName(String name );
    List<Patient> findByBirthDateOrEmail(LocalDate BirthDate , String email);

    @Query("SELECT p FROM Patient p WHERE p.bloodGroup = ?1")
    List<Patient> findByBloodGroup(@Param("bloodGroup") BloodGroupType bloodGroup);

    @Query("SELECT p FROM Patient p WHERE p.birthDate > :birthDate")
    List<Patient> findByBornAfterDate(@Param("birthDate") LocalDate birthDate);

}
