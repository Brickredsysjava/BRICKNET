package com.attendanceApiForApp.attendanceApiForApp.controller;

import com.attendanceApiForApp.attendanceApiForApp.model.Location;
import com.attendanceApiForApp.attendanceApiForApp.service.LocationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/Location")
public class LocationController {
    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping("/createlocation")
    public ResponseEntity<?> createLocation(@RequestBody Location location){
        Location createLocation = locationService.createlocation(location);
        return   ResponseEntity.ok(createLocation);
    }

    @GetMapping("/getAlllocations")
    public ResponseEntity<?> getAllLocations(){
        return  ResponseEntity.ok(locationService.fetchAlllocation());
    }

    @DeleteMapping("deleteLocationById/{locationId}")
    public ResponseEntity<?> deleteByid(@PathVariable String locationId){
        boolean deleted = locationService.deleteLocation(locationId);
        if (deleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
