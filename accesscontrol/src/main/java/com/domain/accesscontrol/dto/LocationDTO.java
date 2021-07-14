package com.domain.accesscontrol.dto;

import com.domain.accesscontrol.entity.Location;
import lombok.Data;

@Data
public class LocationDTO {

    private Long id;
    private String description;

    public LocationDTO(Location location) {
        this.id = location.getId();
        this.description = location.getDescription();
    }
}
