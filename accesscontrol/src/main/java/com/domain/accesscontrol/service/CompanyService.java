package com.domain.accesscontrol.service;

import com.domain.accesscontrol.dto.CompanyDTO;
import com.domain.accesscontrol.entity.Company;
import com.domain.accesscontrol.exception.ObjectNotFoundException;
import com.domain.accesscontrol.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public List<CompanyDTO> getCompany(){

        return companyRepository.findAll().stream().map(CompanyDTO::new).collect(Collectors.toList());
    }

    public CompanyDTO getCompanyById(Long id) {
        Optional<Company> company = companyRepository.findById(id);
        return company.map(CompanyDTO::new).orElseThrow(() -> new ObjectNotFoundException("Registro não foi encontrado!"));
    }

    public CompanyDTO insert(Company company) {
        Assert.isNull(company.getId(),"Não foi possivel inserir o registro!");
        return new CompanyDTO(companyRepository.save(company));
    }

    public CompanyDTO update(Company company, Long id) {
        Assert.notNull(id,"Não foi possivel atualizar o registro!");
        //Busca o registro no DB
        Optional<Company> optional = companyRepository.findById(id);
        if(optional.isPresent()) {
            Company db = optional.get();
            // Copia as propriedades
            db.setAddress(company.getAddress());
            db.setTelephone(company.getTelephone());
            db.setCity(company.getCity());
            db.setDistrict(company.getDistrict());
            db.setCnpj(company.getCnpj());
            db.setStates(company.getStates());
            db.setDescription(company.getDescription());
            System.out.println("Empresa de id = " + db.getId());
            // Atualiza o nivel de acesso
            companyRepository.save(db);
            return new CompanyDTO(db);
        }else {
            return null;
        }
    }

    public void delete(Long id) {

        companyRepository.deleteById(id);

    }
}
