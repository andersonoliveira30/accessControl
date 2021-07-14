package com.domain.accesscontrol.controller;

import com.domain.accesscontrol.dto.AccessLevelDTO;
import com.domain.accesscontrol.dto.BankHourDTO;
import com.domain.accesscontrol.entity.AccessLevel;
import com.domain.accesscontrol.entity.BankHour;
import com.domain.accesscontrol.service.AccessLevelService;
import com.domain.accesscontrol.service.BankHourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/api/v1/bank-hour")
public class BankHourController {
    @Autowired
    private BankHourService bankHourService;

    @GetMapping()
    public ResponseEntity<List<BankHourDTO>> getBankHour(){
        return ResponseEntity.ok(bankHourService.getBankHour());
    }

    @GetMapping("/{id}")
    public ResponseEntity getByIdBankHour(@PathVariable("id") Long id){
        BankHourDTO bankHourDTO = bankHourService.getBankHourById(id);
        return ResponseEntity.ok(bankHourDTO);
    }

    @PostMapping
    public ResponseEntity postBankHour(@RequestBody BankHour bankHour){

       BankHourDTO bk = bankHourService.insert(bankHour);
        URI location = getUriBH(bk.getId());
        return ResponseEntity.created(null).build();
    }

    private URI getUriBH(BankHour.BankHourId id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
    }

    private URI getUriBankHour(Long id){
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
    }

    @PutMapping("/{id}")
    public ResponseEntity putBankHour(@PathVariable("id")Long id, @RequestBody BankHour bankHour){
        bankHour.setId(bankHour.getId());
        BankHourDTO bh = bankHourService.update(bankHour, id);
        return bh != null ? ResponseEntity.ok().build() :
                ResponseEntity.notFound().build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteBankHour(@PathVariable("id")Long id){
        bankHourService.delete(id);
        return ResponseEntity.ok().build();

    }

}
