package com.example.SuperAdmin.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "userId", nullable = false)
    private Long userId;
@OneToOne(cascade = CascadeType.ALL)
@JoinColumn(name = "profile_id")
private Profile profile;

@OneToOne(cascade = CascadeType.ALL)
@JoinColumn(name = "personal_details_id")
private PersonalDetails personalDetails;

@OneToMany(cascade = CascadeType.ALL)
@JoinColumn(name = "education_id")
private List<Education> education;

@OneToOne(cascade = CascadeType.ALL)
@JoinColumn(name = "bank_details_id")
private BankDetails bankDetails;

@OneToMany(cascade = CascadeType.ALL)
@JoinColumn(name = "address_id")
private List<Address> address;

}
