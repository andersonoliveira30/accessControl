package com.domain.accesscontrol.dto;


import com.domain.accesscontrol.entity.AccessLevel;
import lombok.Data;

@Data
public class AccessLevelDTO {

    private Long id;
    private String description;

    public AccessLevelDTO(AccessLevel accessLevel){
        this.id = accessLevel.getId();
        this.description = accessLevel.getDescription();
    }

}
