package com.domain.accesscontrol.dto;

import com.domain.accesscontrol.entity.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class UserDTO {

    private Long id;
    private String name;
    private BigDecimal tolerance;
    private LocalDateTime startjourney;
    private LocalDateTime journeyend;
    private UserCategory userCategory;
    private Company company;
    private AccessLevel accessLevel;
    private WorkingDay workingDay;

    public UserDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.tolerance = user.getTolerance();
        this.startjourney = user.getStartjourney();
        this.journeyend = user.getJourneyend();
        this.userCategory = user.getUserCategory();
        this.company = user.getCompany();
        this.accessLevel = user.getAccessLevel();
        this.workingDay = user.getWorkingDay();
    }
}
