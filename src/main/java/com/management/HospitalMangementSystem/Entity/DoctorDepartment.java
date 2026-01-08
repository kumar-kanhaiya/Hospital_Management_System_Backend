package com.management.HospitalMangementSystem.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "doctor_department")
public class DoctorDepartment {

    @Id
    private Long doctor_id ;

    @Id
    private Long department_id;

}
