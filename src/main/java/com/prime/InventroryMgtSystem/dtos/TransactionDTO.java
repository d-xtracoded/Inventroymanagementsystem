package com.prime.InventroryMgtSystem.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.prime.InventroryMgtSystem.enums.TransactionStatus;
import com.prime.InventroryMgtSystem.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionDTO {

    private Long id;

     private Integer totalproducts;
     private BigDecimal totalPrice;
    private TransactionType transactionType;


    private TransactionStatus status;

    private String description;
    private String note;


    private  LocalDateTime createdAt;

    private  LocalDateTime updatedAt;


    private ProductDTO product;


    private UserDTO user;

    private SupplierDTO supplier;

}
