package com.management.HospitalMangementSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OnBoardRequestDto {
     private Long userId ;
     private String specialization;
     private String name;

}
