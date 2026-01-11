package com.management.HospitalMangementSystem.Entity;

import com.management.HospitalMangementSystem.type.BloodGroupType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Collate;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString(exclude = {"appointments", "insurance"})
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false , length = 40)
    private String name;

//    @Column(nullable = false)
    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    private BloodGroupType bloodGroup;

    @Column(unique = true , nullable = false)
    private String email;

//    @Column(nullable = false)
    private String gender;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDate createdAt;

    @OneToOne(cascade = {CascadeType.MERGE , CascadeType.PERSIST}) // this is the owning side
    @JoinColumn(
            name = "insurance_id"
    )
    private Insurance insurance;

    @OneToMany(mappedBy = "patient" , cascade = {CascadeType.REMOVE  } , orphanRemoval = true ) // this is not owning side this is inverse side
    private List<Appointment> appointments = new ArrayList<>();

}
