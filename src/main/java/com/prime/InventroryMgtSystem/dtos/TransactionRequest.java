package com.prime.InventroryMgtSystem.dtos;


import com.prime.InventroryMgtSystem.enums.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequest {

    @Positive(message = "Product is required")
    private Long productid;

    @Positive(message = "Quantity is required")
    private Integer quantity;

    private String supplierId;

    private String description;
    private String note;

}
