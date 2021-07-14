package com.domain.accesscontrol.controller;

import com.domain.accesscontrol.dto.UserCategoryDTO;
import com.domain.accesscontrol.entity.UserCategory;
import com.domain.accesscontrol.service.UserCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/api/v1/user-category")
public class UserCategoryController {

    @Autowired
    private UserCategoryService userCategoryService;

    @GetMapping()
    public ResponseEntity<List<UserCategoryDTO>> get(){
        return ResponseEntity.ok(userCategoryService.getUserCategory());
    }

    @GetMapping("/{id}")
    public ResponseEntity getByIdUserCategory(@PathVariable("id") Long id){
        UserCategoryDTO uc = userCategoryService.getUserCategoryById(id);
        return ResponseEntity.ok(uc);
    }

    @PostMapping
    public ResponseEntity post(@RequestBody UserCategory userCategory){

        UserCategoryDTO uc = userCategoryService.insert(userCategory);
        URI location = getUri(uc.getId());
        return ResponseEntity.created(null).build();
    }

    private URI getUri(Long id){
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
    }

    @PutMapping("/{id}")
    public ResponseEntity put(@PathVariable("id")Long id, @RequestBody UserCategory userCategory){
        userCategory.setId(id);
        UserCategoryDTO uc = userCategoryService.update(userCategory, id);
        return uc != null ? ResponseEntity.ok().build() :
                ResponseEntity.notFound().build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id")Long id){
        userCategoryService.delete(id);
        return ResponseEntity.ok().build();

    }
}
