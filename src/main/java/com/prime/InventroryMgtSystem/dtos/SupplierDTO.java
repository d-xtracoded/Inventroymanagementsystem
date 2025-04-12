package com.prime.InventroryMgtSystem.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class SupplierDTO {

    //Data transfer Object
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "ContactInfo is required")
    private String contactInfo;

    private String address;

}
