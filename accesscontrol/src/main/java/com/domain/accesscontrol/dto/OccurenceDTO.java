package com.domain.accesscontrol.dto;

import com.domain.accesscontrol.entity.Movement;
import com.domain.accesscontrol.entity.Occurrence;
import lombok.Data;


@Data
public class OccurenceDTO {

    private Long id;
    private String name;
    private String description;
    private Movement movement;

    public OccurenceDTO(Occurrence occurrence) {
        this.id = occurrence.getId();
        this.name = occurrence.getName();
        this.description = occurrence.getDescription();
        this.movement = occurrence.getMovement();
    }
}
