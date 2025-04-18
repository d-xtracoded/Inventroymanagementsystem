package com.prime.InventroryMgtSystem.services.impl;

import com.prime.InventroryMgtSystem.dtos.Response;
import com.prime.InventroryMgtSystem.dtos.TransactionRequest;
import com.prime.InventroryMgtSystem.enums.TransactionStatus;
import com.prime.InventroryMgtSystem.exceptions.NamevalueRequiredExecption;
import com.prime.InventroryMgtSystem.exceptions.NotFoundExecption;
import com.prime.InventroryMgtSystem.models.Product;
import com.prime.InventroryMgtSystem.models.Supplier;
import com.prime.InventroryMgtSystem.reposit.CategoryRepository;
import com.prime.InventroryMgtSystem.reposit.ProductRepository;
import com.prime.InventroryMgtSystem.reposit.SupplierRepository;
import com.prime.InventroryMgtSystem.reposit.TransactionRepository;
import com.prime.InventroryMgtSystem.services.TransactionService;
import com.prime.InventroryMgtSystem.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;

    @Override
    public Response Purchase(TransactionRequest transactionRequest) {
        Long productId= transactionRequest.getProductid();
        Long  supplierId = transactionRequest.getSupplierId();
        Integer quantity = transactionRequest.getQuantity();
        if (supplierId == null) throw new NamevalueRequiredExecption("Supplier Id is Required");

        Product product =productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundExecption("Product Not Found"));

        Supplier supplier = supplierRepository.findById(supplierId)
                .orElseThrow(() -> new NotFoundExecption("Supplier Not Found"));


        return null;
    }

    @Override
    public Response sell(TransactionRequest transactionRequest) {
        return null;
    }

    @Override
    public Response returnToSupplier(TransactionRequest transactionRequest) {
        return null;
    }

    @Override
    public Response getAllTransactions(int page, int size, String filter) {
        return null;
    }

    @Override
    public Response getAllTransactionById(Long id) {
        return null;
    }

    @Override
    public Response getAllTransactionByMonthAndYear(int month, int year) {
        return null;
    }

    @Override
    public Response updateTransactionStatus(Long transactionId, TransactionStatus status) {
        return null;
    }
}
