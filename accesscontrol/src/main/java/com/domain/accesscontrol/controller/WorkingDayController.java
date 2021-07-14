package com.domain.accesscontrol.controller;

import com.domain.accesscontrol.dto.WorkingDayDTO;
import com.domain.accesscontrol.entity.WorkingDay;
import com.domain.accesscontrol.service.WorkingDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/api/v1/working-day")
public class WorkingDayController {
    @Autowired
    private WorkingDayService workingDayService;

    @GetMapping()
    public ResponseEntity<List<WorkingDayDTO>> get(){
        return ResponseEntity.ok(workingDayService.getWorkingDay());
    }

    @GetMapping("/{id}")
    public ResponseEntity getByIdWorkingDay(@PathVariable("id") Long id){
        WorkingDayDTO wd = workingDayService.getWorkingDayById(id);
        return ResponseEntity.ok(wd);
    }

    @PostMapping
    public ResponseEntity post(@RequestBody WorkingDay workingDay){

        WorkingDayDTO wd = workingDayService.insert(workingDay);
        URI location = getUri(wd.getId());
        return ResponseEntity.created(null).build();
    }

    private URI getUri(Long id){
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
    }

    @PutMapping("/{id}")
    public ResponseEntity put(@PathVariable("id")Long id, @RequestBody WorkingDay workingDay){
        workingDay.setId(id);
        WorkingDayDTO wd = workingDayService.update(workingDay, id);
        return wd != null ? ResponseEntity.ok().build() :
                ResponseEntity.notFound().build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id")Long id){
        workingDayService.delete(id);
        return ResponseEntity.ok().build();

    }

}
