package com.domain.accesscontrol.dto;

import com.domain.accesscontrol.entity.DateType;
import lombok.Data;


@Data
public class DateTypeDTO {

    private Long id;
    private String description;

    public DateTypeDTO(DateType dateType) {
        this.id = dateType.getId();
        this.description = dateType.getDescription();
    }
}
