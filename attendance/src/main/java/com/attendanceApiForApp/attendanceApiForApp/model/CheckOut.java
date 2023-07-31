package com.attendanceApiForApp.attendanceApiForApp.model;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "check_outs")
public class CheckOut {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  checkOut_id;

    @ManyToOne
    @JoinColumn(name = "record_id", nullable = false)
    private Records record;

    @Column(nullable = false)
    private String checkoutTime;

    @Column(nullable = false)
    private String timezone;

}
