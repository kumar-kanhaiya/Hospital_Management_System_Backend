package com.management.HospitalMangementSystem.repository;

import com.management.HospitalMangementSystem.Entity.Patient;
import com.management.HospitalMangementSystem.Entity.type.BloodGroupType;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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

    @Query("SELECT new com.management.HospitalMangementSystem.dto.BloodGroupCountResponseEntity( p.bloodGroup , Count(p) )" +
            "FROM Patient  p group by p.bloodGroup") // jpl query
//    List<Object[]> countEachBloodGroupType();
    List<Object[]> countEachBloodGroupType();


    @Transactional
    @Modifying
    @Query("UPDATE Patient p SET p.name = :name WHERE p.id = :id ")
    int updateNameWithId(@Param("name") String name  , @Param("id") Long id);

    @Query(value = "select * from patient", nativeQuery = true)
    Page<Patient> findAllPatients(Pageable pageable);// use of pageable

    @Query( "SELECT p FROM Patient p LEFT JOIN FETCH p.appointments")
    List<Patient>  findAllPatientWithAppointment();
}
