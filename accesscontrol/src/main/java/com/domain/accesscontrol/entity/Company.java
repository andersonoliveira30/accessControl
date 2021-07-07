package com.domain.accesscontrol.entity;

import com.domain.accesscontrol.enuns.States;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100)
    private String description;
    @Column(length = 13, unique = true)
    private String cnpj;
    @Column(length = 30)
    private String address;
    @Column(length = 10)
    private String district;
    @Column(length = 12)
    private String city;
    @Column(length = 10)
    private States states;
    @Column(length = 10)
    private String telephone;
}
