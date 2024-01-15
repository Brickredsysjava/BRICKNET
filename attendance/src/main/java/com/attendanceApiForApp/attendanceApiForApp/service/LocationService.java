package com.attendanceApiForApp.attendanceApiForApp.service;


import com.attendanceApiForApp.attendanceApiForApp.model.Location;

import java.util.List;

public interface LocationService {
    Location createlocation(Location location);
    List<Location> fetchAlllocation();

    boolean deleteLocation(String locationId);

}
