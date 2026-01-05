package com.management.HospitalMangementSystem.Entity;

import com.management.HospitalMangementSystem.type.BloodGroupType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;

    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    private BloodGroupType bloodGroup;

    private String email;

    private String gender;

}
