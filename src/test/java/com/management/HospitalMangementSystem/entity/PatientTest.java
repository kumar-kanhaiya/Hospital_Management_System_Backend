package com.management.HospitalMangementSystem.entity;

import com.management.HospitalMangementSystem.Entity.Patient;
import com.management.HospitalMangementSystem.repository.PatientRepository;
import com.management.HospitalMangementSystem.service.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class PatientTest {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PatientService patientService;

    @Test
    public void testPatientRepository(){
        List<Patient> patientList = patientRepository.findAllPatientWithAppointment();
        System.out.println(patientList);
    }

    @Test
    public void testTransactionMethods(){
        Patient p1 = patientService.getPatientById(1L);
        System.out.println(p1);
    }

    @Test
    public void checkingRepositoryLayer(){
        List<Patient> patients = patientRepository.findByBornAfterDate(LocalDate.of(2005,3,14));
        for(Patient patient : patients){
            System.out.println(patient);
        }

        List<Object[]> bloodGroupList = patientRepository.countEachBloodGroupType();
        for(Object[]  objects : bloodGroupList){
            System.out.println(objects[0] + " " + objects[1]);
        }

        int updated = patientRepository.updateNameWithId("Kanhaiya Singh" , 1L);
        System.out.println(updated);
    }

    @Test
    public void pageableTest(){
        Page<Patient> patientList = patientRepository.findAllPatients(PageRequest.of(1, 2, Sort.by("name")));

        for(Patient patient: patientList) {
            System.out.println(patient);
        }
    }



}

//    @Test
//    public void pageableTest(){
//
//    }

