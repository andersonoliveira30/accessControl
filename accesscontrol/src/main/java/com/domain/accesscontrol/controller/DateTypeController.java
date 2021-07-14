package com.domain.accesscontrol.controller;

import com.domain.accesscontrol.dto.DateTypeDTO;
import com.domain.accesscontrol.entity.DateType;
import com.domain.accesscontrol.service.DateTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/api/v1/date-type")
public class DateTypeController {

    @Autowired
    private DateTypeService dateTypeService;

    @GetMapping()
    public ResponseEntity<List<DateTypeDTO>> get(){
        return ResponseEntity.ok(dateTypeService.getDateType());
    }

    @GetMapping("/{id}")
    public ResponseEntity getByIdDateType(@PathVariable("id") Long id){
        DateTypeDTO dateTypeDTO = dateTypeService.getDateTypeById(id);
        return ResponseEntity.ok(dateTypeDTO);
    }

    @PostMapping
    public ResponseEntity post(@RequestBody DateType dateType){

       DateTypeDTO dt = dateTypeService.insert(dateType);
        URI location = getUri(dt.getId());
        return ResponseEntity.created(null).build();
    }

    private URI getUri(Long id){
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
    }

    @PutMapping("/{id}")
    public ResponseEntity put(@PathVariable("id")Long id, @RequestBody DateType dateType){
        dateType.setId(id);
        DateTypeDTO dt = dateTypeService.update(dateType, id);
        return dt != null ? ResponseEntity.ok().build() :
                ResponseEntity.notFound().build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id")Long id){
        dateTypeService.delete(id);
        return ResponseEntity.ok().build();

    }



}
