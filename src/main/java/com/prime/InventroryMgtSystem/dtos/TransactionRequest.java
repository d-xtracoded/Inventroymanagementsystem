package com.prime.InventroryMgtSystem.dtos;


import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequest {

    @Positive(message = "ProductService is required")
    private Long productid;

    @Positive(message = "Quantity is required")
    private Integer quantity;

    private Long supplierId;

    private String description;
    private String note;

}
