package com.prime.InventroryMgtSystem.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.prime.InventroryMgtSystem.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {
    //Data transfer Object
    private Long id;

    private String name;

    private String email;

    @JsonIgnore //ignore password feilds during serialization
    private String password;

    private String phonenumber;

    private UserRole role;

    private List <TransactionDTO> transaction;

    private LocalDateTime createdAt;


}
