package com.prime.InventroryMgtSystem.controller;

import com.prime.InventroryMgtSystem.dtos.Response;
import com.prime.InventroryMgtSystem.dtos.UserDTO;
import com.prime.InventroryMgtSystem.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

        private final UserService userService;

        @GetMapping("/all")
        @PreAuthorize("hasAnyAuthority('ADMIN')")
        public ResponseEntity<Response> getallusers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

        @GetMapping("/{id}")
        public ResponseEntity<Response> getuser(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUserbyID(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Response> getuser(@PathVariable Long id, @RequestBody UserDTO userDTO){
        return ResponseEntity.ok(userService.updateUser(id,userDTO));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<Response> deleteuser(@PathVariable Long id){
        return ResponseEntity.ok(userService.deleteUser(id));
    }


    @GetMapping("/gettransactions/{userid}")
    public ResponseEntity<Response> getuserAndTransaction(@PathVariable Long userid){
        return ResponseEntity.ok(userService.getUserTransaction(userid));
    }

    }
