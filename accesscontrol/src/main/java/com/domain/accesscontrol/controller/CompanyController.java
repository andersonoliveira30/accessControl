package com.domain.accesscontrol.controller;

import com.domain.accesscontrol.dto.CalendarDTO;
import com.domain.accesscontrol.dto.CompanyDTO;
import com.domain.accesscontrol.entity.Calendar;
import com.domain.accesscontrol.entity.Company;
import com.domain.accesscontrol.service.CalendarService;
import com.domain.accesscontrol.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/api/v1/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping()
    public ResponseEntity<List<CompanyDTO>> get(){
        return ResponseEntity.ok(companyService.getCompany());
    }

    @GetMapping("/{id}")
    public ResponseEntity getByIdCompany(@PathVariable("id") Long id){
        CompanyDTO companyDTO = companyService.getCompanyById(id);
        return ResponseEntity.ok(companyDTO);
    }

    @PostMapping
    public ResponseEntity post(@RequestBody Company company){

        CompanyDTO co = companyService.insert(company);
        URI location = getUri(co.getId());
        return ResponseEntity.created(null).build();
    }

    private URI getUri(Long id){
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
    }

    @PutMapping("/{id}")
    public ResponseEntity put(@PathVariable("id")Long id, @RequestBody Company company){
        company.setId(id);
        CompanyDTO co = companyService.update(company, id);
        return co != null ? ResponseEntity.ok().build() :
                ResponseEntity.notFound().build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id")Long id){
        companyService.delete(id);
        return ResponseEntity.ok().build();

    }


}
