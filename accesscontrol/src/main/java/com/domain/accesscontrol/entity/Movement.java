package com.domain.accesscontrol.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class Movement {

    /*** Classe com chave composta ***/
    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode
    @Embeddable
    public class MovementId implements Serializable {
        private Long IdMovement;
        private Long IdUser;
    }
    @EmbeddedId
    private MovementId id;
    private LocalDateTime dateEntry;
    private LocalDateTime departureDate;
    private BigDecimal timeCourse;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "movement")
    private List<Occurrence> occurrence;
    @ManyToOne
    private Calendar calendar;

}
