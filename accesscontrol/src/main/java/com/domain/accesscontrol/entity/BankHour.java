package com.domain.accesscontrol.entity;

import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class BankHour {

    // Chave Composta
    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode
    @Embeddable
    public class BankHourId implements Serializable {
        private Long IdBankHour;
        private Long IdMovement;
        private Long IdUser;
    }
    @EmbeddedId
    private BankHourId id;
    private LocalDateTime dateWorked;
    private BigDecimal amountHours;
    private BigDecimal hoursBalance;

}
