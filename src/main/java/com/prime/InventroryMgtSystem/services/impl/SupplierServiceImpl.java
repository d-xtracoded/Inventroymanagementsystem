package com.prime.InventroryMgtSystem.services.impl;

import com.prime.InventroryMgtSystem.dtos.Response;
import com.prime.InventroryMgtSystem.dtos.SupplierDTO;
import com.prime.InventroryMgtSystem.models.Supplier;
import com.prime.InventroryMgtSystem.reposit.SupplierRepository;
import com.prime.InventroryMgtSystem.services.SupplierService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SupplierServiceImpl implements SupplierService {

    private final ModelMapper modelMapper;
    private final SupplierRepository supplierRepository;


    @Override
    public Response addSupplier(SupplierDTO supplierDTO) {
        Supplier suppliertosave = modelMapper.map(supplierDTO,Supplier.class);
        supplierRepository.save(suppliertosave);
        return Response.builder()
                .message("Supplier successfully saved")
                .status(200)
                .build();
    }

    @Override
    public Response updateSupplier(Long id, SupplierDTO supplierDTO) {
        return null;
    }

    @Override
    public Response getSupplier(Long id) {
        return null;
    }

    @Override
    public Response deleteSupplier(Long id) {
        return null;
    }

    @Override
    public Response getallSupplier() {
        return null;
    }
}
