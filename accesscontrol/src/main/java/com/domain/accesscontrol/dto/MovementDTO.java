package com.domain.accesscontrol.dto;

import com.domain.accesscontrol.entity.Calendar;
import com.domain.accesscontrol.entity.Movement;
import com.domain.accesscontrol.entity.Occurrence;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class MovementDTO {

    private Movement.MovementId id;
    private LocalDateTime dateEntry;
    private LocalDateTime departureDate;
    private BigDecimal timeCourse;
    private List<Occurrence> occurrence;
    private Calendar calendar;

    public MovementDTO(Movement movement) {
        this.id = movement.getId();
        this.dateEntry = movement.getDateEntry();
        this.departureDate = movement.getDepartureDate();
        this.timeCourse = movement.getTimeCourse();
        this.occurrence = movement.getOccurrence();
        this.calendar = movement.getCalendar();
    }
}
