package com.domain.accesscontrol.dto;

import com.domain.accesscontrol.entity.Company;
import lombok.Data;

import javax.persistence.Column;

@Data
public class CompanyDTO {

    private Long id;
    private String description;
    private String cnpj;
    private String address;
    private String district;
    private String city;
    private String states;
    private String telephone;

    public CompanyDTO(Company company) {
        this.id = company.getId();
        this.description = company.getDescription();
        this.cnpj = company.getCnpj();
        this.address = company.getAddress();
        this.district = company.getDistrict();
        this.city = company.getCity();
        this.states = company.getStates();
        this.telephone = company.getTelephone();
    }
}
