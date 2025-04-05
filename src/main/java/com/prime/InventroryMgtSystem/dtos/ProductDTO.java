package com.prime.InventroryMgtSystem.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL) // ignore fields that are not null
@JsonIgnoreProperties(ignoreUnknown = true)// ignore fields that are not true
public class ProductDTO {

    private Long id;
    private Long categoryid;
    private Long productid;
    private Long supplierid;

    private String name;


    private String sku;


    private BigDecimal price;


    private Integer stockQuantity;

    private String description;
    private LocalDateTime expiryDate;
    private  String imageurl;

    private  LocalDateTime createdAt;

}
