package com.domain.accesscontrol.controller;

import com.domain.accesscontrol.dto.UserDTO;
import com.domain.accesscontrol.entity.User;
import com.domain.accesscontrol.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public ResponseEntity<List<UserDTO>> get(){
        return ResponseEntity.ok(userService.getUser());
    }

    @GetMapping("/{id}")
    public ResponseEntity getByIdUser(@PathVariable("id") Long id){
        UserDTO userDTO = userService.getUserById(id);
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping
    public ResponseEntity post(@RequestBody User user){

        UserDTO userDTO = userService.insert(user);
        URI location = getUri(userDTO.getId());
        return ResponseEntity.created(null).build();
    }

    private URI getUri(Long id){
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
    }

    @PutMapping("/{id}")
    public ResponseEntity put(@PathVariable("id")Long id, @RequestBody User user){
        user.setId(id);
        UserDTO userDTO = userService.update(user, id);
        return userDTO != null ? ResponseEntity.ok().build() :
                ResponseEntity.notFound().build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id")Long id){
        userService.delete(id);
        return ResponseEntity.ok().build();

    }
}
