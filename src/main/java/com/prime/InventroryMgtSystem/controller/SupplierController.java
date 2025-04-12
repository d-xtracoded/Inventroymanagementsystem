package com.prime.InventroryMgtSystem.controller;

import com.prime.InventroryMgtSystem.dtos.Response;
import com.prime.InventroryMgtSystem.dtos.SupplierDTO;
import com.prime.InventroryMgtSystem.services.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/supplier")
public class SupplierController {

    private final SupplierService supplierService;
    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<Response> createsupplier(@RequestBody SupplierDTO supplierDTO){
       return ResponseEntity.ok(supplierService.addSupplier(supplierDTO));
    }
    @GetMapping("/all")
    public ResponseEntity<Response> getallsuppliers(){
        return  ResponseEntity.ok(supplierService.getallSupplier());
    }


}
