package com.prime.InventroryMgtSystem.controller;

import com.prime.InventroryMgtSystem.dtos.CategoryDTO;
import com.prime.InventroryMgtSystem.dtos.Response;
import com.prime.InventroryMgtSystem.models.Category;
import com.prime.InventroryMgtSystem.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("/all")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<Response> createCategory(@RequestBody CategoryDTO categoryDTO){
        return ResponseEntity.ok(categoryService.createCategory(categoryDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response>getcategory(@PathVariable Long id){
        return ResponseEntity.ok(categoryService.getCategorybyid(id));
    }

    @PostMapping("/update/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<Response> updatecategory(@RequestBody CategoryDTO categoryDTO, @PathVariable Long id){
        return ResponseEntity.ok(categoryService.updateCategory(id,categoryDTO));
    }

}
