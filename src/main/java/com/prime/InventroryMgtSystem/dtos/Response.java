package com.prime.InventroryMgtSystem.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.prime.InventroryMgtSystem.enums.UserRole;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL) // fields that are not present in the response let not display them
public class Response {
    // User to Show out result

    //Generic
    private int status;
    private String message;
    // login
    private String token;
    private UserRole role;
    private String expirationTime;
    //pagination
    private Integer totalPages;
    private Long totalElement;
    //Data output optional
    private UserDTO user;
    private List<UserDTO> users;

    private SupplierDTO supplier;
    private List<SupplierDTO> suppliers;

    private CategoryDTO category;
    private List<CategoryDTO> categories;

    private ProductDTO product ;
    private List<ProductDTO> products;

    private TransactionDTO transaction;
    private List<TransactionDTO> transactions;

    private final LocalDateTime timestamp = LocalDateTime.now();


}
