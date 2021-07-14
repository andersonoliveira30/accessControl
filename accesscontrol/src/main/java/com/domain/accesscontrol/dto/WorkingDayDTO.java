package com.domain.accesscontrol.dto;

import com.domain.accesscontrol.entity.WorkingDay;
import lombok.Data;

@Data
public class WorkingDayDTO {

    private Long id;
    private String description;

    public WorkingDayDTO(WorkingDay workingDay) {
        this.id = workingDay.getId();
        this.description = workingDay.getDescription();
    }
}
