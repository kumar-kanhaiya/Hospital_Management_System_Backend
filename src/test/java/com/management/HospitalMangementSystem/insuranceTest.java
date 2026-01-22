package com.management.HospitalMangementSystem;

import com.management.HospitalMangementSystem.Entity.Insurance;
import com.management.HospitalMangementSystem.Entity.Patient;
import com.management.HospitalMangementSystem.repository.InsuranceRepository;
import com.management.HospitalMangementSystem.service.InsuranceService;
import org.aspectj.apache.bcel.classfile.InnerClass;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class insuranceTest {

    @Autowired
    private InsuranceRepository insuranceRepository;

    @Autowired
    private  InsuranceService insuranceService;

    @Test
    public void insuranceServiceTest(){
        Insurance insurance = Insurance.builder()
                .policyNumber("HDFC1234").provider("HDFC")
                .validUntil(LocalDate.of(2030,12,22))
                .build();

        Patient patient = insuranceService.assignInsuranceToPatient(insurance , 1L);
        System.out.println(patient);

//        Patient afterRemovingInsurance = insuranceService.disaccociateInsuranceFromPatient(1L);
//        System.out.println(afterRemovingInsurance);
    }

    @Test
    public void testCascade(){
        Insurance insurance = Insurance.builder()
                .policyNumber("PNB9654").provider("PNB")
                .validUntil(LocalDate.of(2028,6,21))
                .build();

        Patient patient = insuranceService.assignInsuranceToPatient(insurance,3L);
        System.out.println(patient);
    }



}
