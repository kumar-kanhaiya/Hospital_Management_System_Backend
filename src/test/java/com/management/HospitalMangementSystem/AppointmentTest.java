package com.management.HospitalMangementSystem;

import com.management.HospitalMangementSystem.Entity.Appointment;
import com.management.HospitalMangementSystem.service.AppointmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.LocalTime;

@SpringBootTest
public class AppointmentTest {

    @Autowired
    private AppointmentService appointmentService;

    @Test
    public void createAppointmentTest(){
        Appointment appointment = Appointment.builder()
                .appointmentTime(LocalDateTime.of(2026,11,12,14,0))
                .reason("pain")
                .build();

        var newAppointment = appointmentService.createNewAppointment(appointment , 2L , 1L);
        System.out.println(newAppointment);

        var reassign = appointmentService.reAssignAppointmentToAnotherDoctor(1L , 1L);
        System.out.println(reassign.toString());
    }
}
