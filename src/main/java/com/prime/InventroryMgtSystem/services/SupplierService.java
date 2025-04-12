package com.prime.InventroryMgtSystem.services;

import com.prime.InventroryMgtSystem.dtos.SupplierDTO;
import org.apache.coyote.Response;

public interface SupplierService {
    // Serve as a bridge btw DTO and Controller
    Response addSupplier(SupplierDTO supplierDTO);
    //id and response from Db ans user
    Response updateSupplier(Long id, SupplierDTO supplierDTO);
    Response getSupplier(Long id);
    //need id
    Response deleteSupplier(Long id);
    Response getallSupplier();

}
