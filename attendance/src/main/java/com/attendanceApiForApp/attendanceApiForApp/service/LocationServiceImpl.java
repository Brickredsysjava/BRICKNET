package com.attendanceApiForApp.attendanceApiForApp.service;


import com.attendanceApiForApp.attendanceApiForApp.model.Location;
import com.attendanceApiForApp.attendanceApiForApp.repository.LocationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public Location createlocation(Location location) {
        return locationRepository.save(location);
    }

    @Override
    public List<Location> fetchAlllocation() {
        return locationRepository.findAll();
    }

    @Override
    public boolean deleteLocation(String locationId) {
        if(locationRepository.existsById(locationId)){
            locationRepository.deleteById(locationId);
            return  true;
        }
        return false;
    }


}

