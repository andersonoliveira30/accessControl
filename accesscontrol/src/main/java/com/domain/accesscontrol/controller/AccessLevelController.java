package com.domain.accesscontrol.controller;

import com.domain.accesscontrol.dto.AccessLevelDTO;
import com.domain.accesscontrol.entity.AccessLevel;
import com.domain.accesscontrol.service.AccessLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/access-level")
public class AccessLevelController {


    @Autowired
    private AccessLevelService accessLevelService;

    @GetMapping
    public ResponseEntity<List<AccessLevelDTO>> get(){
        return ResponseEntity.ok(accessLevelService.getAccessLevel());
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable("id") Long id){
        Optional<AccessLevelDTO> accessLevel= accessLevelService.getAccessLevelById(id);
        if(accessLevel.isPresent()){
            return ResponseEntity.ok(accessLevel.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity post(@RequestBody AccessLevel accessLevel){
        try {
            AccessLevelDTO al = accessLevelService.insert(accessLevel);
            URI location = getUri(al.getId());
            return ResponseEntity.created(null).build();
        }catch (Exception ex){
            return ResponseEntity.badRequest().build();
        }
    }

    private URI getUri(Long id){
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
    }

    @PutMapping("/{id}")
    public ResponseEntity put(@PathVariable("id")Long id, @RequestBody AccessLevel accessLevel){
        accessLevel.setId(id);
        AccessLevelDTO al = accessLevelService.update(accessLevel, id);
        return al != null ? ResponseEntity.ok().build() :
                            ResponseEntity.notFound().build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id")Long id){
        boolean ok = accessLevelService.delete(id);

        return ok ? ResponseEntity.ok().build() :
                    ResponseEntity.notFound().build();
    }

}
