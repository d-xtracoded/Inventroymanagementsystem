package com.prime.InventroryMgtSystem.controller;

import com.prime.InventroryMgtSystem.dtos.ProductDTO;
import com.prime.InventroryMgtSystem.dtos.Response;
import com.prime.InventroryMgtSystem.reposit.ProductRepository;
import com.prime.InventroryMgtSystem.services.ProductService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> saveProduct(
            @RequestParam("imageFile") MultipartFile imageFile,
            @RequestParam("name") String name,
            @RequestParam("sku") String sku,
            @RequestParam("price") BigDecimal price,
            @RequestParam("stockQuantity") Integer stockQuantity,
            @RequestParam("categoryId") Long categoryId,
            @RequestParam(value = "description", required = false) String description
    ) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName(name);
        productDTO.setSku(sku);
        productDTO.setPrice(price);
        productDTO.setStockQuantity(stockQuantity);
        productDTO.setCategoryid(categoryId);
        productDTO.setDescription(description);

        return ResponseEntity.ok(productService.addProduct(productDTO, imageFile));

    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> updateProduct(
            @RequestParam(value = "imageFile", required = false) MultipartFile imageFile,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "sku", required = false) String sku,
            @RequestParam(value = "price", required = false) BigDecimal price,
            @RequestParam(value = "stockQuantity", required = false) Integer stockQuantity,
            @RequestParam(value = "categoryId", required = false) Long categoryId,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam("productId") Long productId
    ) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName(name);
        productDTO.setSku(sku);
        productDTO.setPrice(price);
        productDTO.setProductid(productId);
        productDTO.setStockQuantity(stockQuantity);
        productDTO.setCategoryid(categoryId);
        productDTO.setDescription(description);

        return ResponseEntity.ok(productService.updateProduct(productDTO, imageFile));

    }
    @GetMapping("/all")
    public ResponseEntity<Response> getAllProducts() {
        return ResponseEntity.ok(productService.getProduct());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductbyid(id));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteProduct(@PathVariable Long id) {
        return ResponseEntity.ok(productService.deleteProduct(id));
    }

    @GetMapping("/search")
    public ResponseEntity<Response> searchProduct(@RequestParam String input) {
        return ResponseEntity.ok(productService.searchProduct(input));
    }



}
