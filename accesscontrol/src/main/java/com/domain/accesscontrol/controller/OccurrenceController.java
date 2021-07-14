package com.domain.accesscontrol.controller;

import com.domain.accesscontrol.dto.OccurenceDTO;
import com.domain.accesscontrol.entity.Occurrence;
import com.domain.accesscontrol.service.OccurrenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/api/v1/occurrence")
public class OccurrenceController {

    @Autowired
    private OccurrenceService occurrenceService;

    @GetMapping()
    public ResponseEntity<List<OccurenceDTO>> get(){
        return ResponseEntity.ok(occurrenceService.getOccurrence());
    }

    @GetMapping("/{id}")
    public ResponseEntity getByIdOccurrence(@PathVariable("id") Long id){
       OccurenceDTO occurenceDTO = occurrenceService.getOccurrenceById(id);
        return ResponseEntity.ok(occurenceDTO);
    }

    @PostMapping
    public ResponseEntity post(@RequestBody Occurrence occurrence){

       OccurenceDTO oc = occurrenceService.insert(occurrence);
        URI location = getUri(oc.getId());
        return ResponseEntity.created(null).build();
    }

    private URI getUri(Long id){
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
    }

    @PutMapping("/{id}")
    public ResponseEntity put(@PathVariable("id")Long id, @RequestBody Occurrence occurrence){
        occurrence.setId(id);
       OccurenceDTO oc = occurrenceService.update(occurrence, id);
        return oc != null ? ResponseEntity.ok().build() :
                ResponseEntity.notFound().build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id")Long id){
        occurrenceService.delete(id);
        return ResponseEntity.ok().build();

    }
}
