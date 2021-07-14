package com.domain.accesscontrol.dto;

import com.domain.accesscontrol.entity.Calendar;
import com.domain.accesscontrol.entity.DateType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CalendarDTO {

    private Long id;
    private String description;
    private LocalDateTime specialDate;
    private DateType dateType;

    public CalendarDTO(Calendar calendar) {
        this.id = calendar.getId();
        this.description = calendar.getDescription();
        this.specialDate = calendar.getSpecialDate();
        this.dateType = calendar.getDateType();
    }
}
