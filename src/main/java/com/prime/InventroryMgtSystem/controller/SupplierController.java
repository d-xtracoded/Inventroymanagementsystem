package com.prime.InventroryMgtSystem.controller;

import com.prime.InventroryMgtSystem.dtos.Response;
import com.prime.InventroryMgtSystem.dtos.SupplierDTO;
import com.prime.InventroryMgtSystem.services.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public ResponseEntity<Response>getsupplier(@PathVariable Long id){
        return ResponseEntity.ok(supplierService.getSupplier(id));
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<Response> updatesupplier(@RequestBody SupplierDTO supplierDTO, @PathVariable Long id){
        return ResponseEntity.ok(supplierService.updateSupplier(id,supplierDTO));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<Response> deletecategory(@PathVariable Long id){
        return ResponseEntity.ok(supplierService.deleteSupplier(id));
    }

}
