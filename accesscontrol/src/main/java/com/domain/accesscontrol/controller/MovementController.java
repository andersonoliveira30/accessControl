package com.domain.accesscontrol.controller;

import com.domain.accesscontrol.dto.CompanyDTO;
import com.domain.accesscontrol.dto.MovementDTO;
import com.domain.accesscontrol.entity.Company;
import com.domain.accesscontrol.entity.Movement;
import com.domain.accesscontrol.service.CompanyService;
import com.domain.accesscontrol.service.MovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/api/v1/movement")
public class MovementController {

    @Autowired
    private MovementService movementService;

    @GetMapping()
    public ResponseEntity<List<MovementDTO>> get(){
        return ResponseEntity.ok(movementService.getMovement());
    }

    @GetMapping("/{id}")
    public ResponseEntity getByIdMovement(@PathVariable("id") Long id){
        MovementDTO movementDTO = movementService.getMovementById(id);
        return ResponseEntity.ok(movementDTO);
    }

    @PostMapping
    public ResponseEntity post(@RequestBody Movement movement){

       MovementDTO mo = movementService.insert(movement);
        URI location = getUri(mo.getId());
        return ResponseEntity.created(null).build();
    }

    private URI getUri(Movement.MovementId id){
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
    }

    @PutMapping("/{id}")
    public ResponseEntity put(@PathVariable("id")Long id, @RequestBody Movement movement){
        movement.setId(movement.getId());
        MovementDTO mo = movementService.update(movement, id);
        return mo != null ? ResponseEntity.ok().build() :
                ResponseEntity.notFound().build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id")Long id){
        movementService.delete(id);
        return ResponseEntity.ok().build();

    }
}
