package com.prime.InventroryMgtSystem.services;

import com.prime.InventroryMgtSystem.dtos.Response;
import com.prime.InventroryMgtSystem.dtos.TransactionDTO;
import com.prime.InventroryMgtSystem.dtos.TransactionRequest;
import com.prime.InventroryMgtSystem.enums.TransactionStatus;

public interface TransactionService {

    Response Purchase(TransactionRequest transactionRequest);

    Response sell(TransactionRequest transactionRequest);

    Response returnToSupplier(TransactionRequest transactionRequest);

    Response getAllTransactions(int page, int size, String filter);
    Response getAllTransactionById(Long id);

    Response getAllTransactionByMonthAndYear(int month, int year);

    Response updateTransactionStatus(Long transactionId, TransactionStatus status);

}
