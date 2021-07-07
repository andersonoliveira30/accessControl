package com.domain.accesscontrol.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class Calendar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100)
    private String description;
    private LocalDateTime specialDate;
    @OneToOne
    private DateType dateType;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Movement> movement;


}
