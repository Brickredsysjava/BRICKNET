package com.attendanceApiForApp.attendanceApiForApp.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Table(name = "location")
public class Location {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "location_id", columnDefinition = "VARCHAR(255)")

    private String locationId;
    private  String latitude;
    private  String longitude;
    private String timestamps;
    private String empId;

}
