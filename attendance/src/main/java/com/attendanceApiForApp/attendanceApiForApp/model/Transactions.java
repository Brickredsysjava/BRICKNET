package com.attendanceApiForApp.attendanceApiForApp.model;

import jakarta.persistence.*;
import  lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "Transactions")
public class Transactions {
  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
  @Column(name = "transaction_id", columnDefinition = "VARCHAR(255)")
  private String transaction_id;
  @ManyToOne
  @JoinColumn(name = "record_id", nullable = false)
  private Records record;
    @Column(nullable = false)
    private String checkinTimes;
    @Column(nullable = true)
    private String checkoutTimes;
  @Column(nullable = true)
  private String durations;



}
