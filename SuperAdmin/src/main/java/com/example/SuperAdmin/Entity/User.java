package com.example.SuperAdmin.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

    @NoArgsConstructor
    @AllArgsConstructor
    @Setter
    @Getter
    @Entity

    public class  User {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "user_id", columnDefinition = "VARCHAR(255)")
    private String id;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "personalDetails_id")
    private PersonalDetails personalDetails;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @OrderColumn
    private List<Education> education;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "bankDetails_id")
    private BankDetails bankDetails;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @OrderColumn
    private List<Address> address;

}
