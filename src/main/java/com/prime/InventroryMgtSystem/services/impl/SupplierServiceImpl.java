package com.prime.InventroryMgtSystem.services.impl;

import com.prime.InventroryMgtSystem.dtos.CategoryDTO;
import com.prime.InventroryMgtSystem.dtos.Response;
import com.prime.InventroryMgtSystem.dtos.SupplierDTO;
import com.prime.InventroryMgtSystem.exceptions.NotFoundExecption;
import com.prime.InventroryMgtSystem.models.Category;
import com.prime.InventroryMgtSystem.models.Supplier;
import com.prime.InventroryMgtSystem.reposit.SupplierRepository;
import com.prime.InventroryMgtSystem.services.SupplierService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

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
        Supplier existingsupplier = supplierRepository.findById(id).orElseThrow(()-> new NotFoundExecption("Category not found"));
        existingsupplier.setName(supplierDTO.getName());
        supplierRepository.save(existingsupplier);

        return Response.builder()
                .status(200)
                .message("Supplier successfully updated")
                .build();
    }

    @Override
    public Response getSupplier(Long id) {
        Supplier supplier = supplierRepository.findById(id).orElseThrow(()-> new NotFoundExecption("Supplier Not found"));
        SupplierDTO supplierDTO = modelMapper.map(supplier, SupplierDTO.class);
        return Response.builder()
                .status(200)
                .message("success")
                .supplier(supplierDTO)
                .build();
    }

    @Override
    public Response deleteSupplier(Long id) {
        return null;
    }

    @Override
    public Response getallSupplier() {
        List<Supplier> supplier = supplierRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        // return properties of cat without products
       // supplier.forEach(category -> supplier.setProducts(null));
        // we need to map to DTO
        List<CategoryDTO> categoryDTOSlist = modelMapper.map(supplier, new TypeToken<CategoryDTO>(){}
                .getType());
        return Response.builder()
                .status(200)
                .categories(categoryDTOSlist)
                .message("success")
                .build();
    }
}
