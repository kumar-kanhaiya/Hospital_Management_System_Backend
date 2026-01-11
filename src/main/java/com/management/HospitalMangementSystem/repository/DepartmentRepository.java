package com.management.HospitalMangementSystem.repository;

import com.management.HospitalMangementSystem.Entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}