package com.management.HospitalMangementSystem.repository;

import com.management.HospitalMangementSystem.Entity.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsuranceRepository extends JpaRepository<Insurance, Long> {
}