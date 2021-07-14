package com.domain.accesscontrol.controller;

import com.domain.accesscontrol.dto.LocationDTO;
import com.domain.accesscontrol.entity.Location;
import com.domain.accesscontrol.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/api/v1/location")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @GetMapping()
    public ResponseEntity<List<LocationDTO>> get(){
        return ResponseEntity.ok(locationService.getLocation());
    }

    @GetMapping("/{id}")
    public ResponseEntity getByIdLocation(@PathVariable("id") Long id){
        LocationDTO locationDTO = locationService.getLocationById(id);
        return ResponseEntity.ok(locationDTO);
    }

    @PostMapping
    public ResponseEntity post(@RequestBody Location locations){

        LocationDTO lo = locationService.insert(locations);
        URI location = getUri(lo.getId());
        return ResponseEntity.created(null).build();
    }

    private URI getUri(Long id){
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
    }

    @PutMapping("/{id}")
    public ResponseEntity put(@PathVariable("id")Long id, @RequestBody Location location){
       location.setId(id);
        LocationDTO lo = locationService.update(location, id);
        return lo != null ? ResponseEntity.ok().build() :
                ResponseEntity.notFound().build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id")Long id){
        locationService.delete(id);
        return ResponseEntity.ok().build();

    }


}
