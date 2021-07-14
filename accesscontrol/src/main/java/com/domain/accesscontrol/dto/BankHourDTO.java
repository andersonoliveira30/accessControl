package com.domain.accesscontrol.dto;

import com.domain.accesscontrol.entity.BankHour;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class BankHourDTO {

    private BankHour.BankHourId id;
    private LocalDateTime dateWorked;
    private BigDecimal amountHours;
    private BigDecimal hoursBalance;

    public BankHourDTO(BankHour bankHour) {
        this.id = bankHour.getId();
        this.dateWorked = bankHour.getDateWorked();
        this.amountHours = bankHour.getAmountHours();
        this.hoursBalance = bankHour.getHoursBalance();
    }
}
