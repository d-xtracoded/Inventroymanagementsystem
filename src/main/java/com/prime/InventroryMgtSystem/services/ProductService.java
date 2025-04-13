package com.prime.InventroryMgtSystem.services;

import com.prime.InventroryMgtSystem.dtos.ProductDTO;
import com.prime.InventroryMgtSystem.dtos.Response;
import org.springframework.web.multipart.MultipartFile;

public interface ProductService {
    Response addProduct(ProductDTO productDTO, MultipartFile imageFile);
    Response updateProduct(ProductDTO productDTO,MultipartFile imageFile);
    Response getProduct();
    Response getProductbyid(Long id);
    Response deleteProduct(Long id);

    Response searchProduct(String input);
}
