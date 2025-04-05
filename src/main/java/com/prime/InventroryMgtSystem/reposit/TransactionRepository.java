package com.prime.InventroryMgtSystem.reposit;

import com.prime.InventroryMgtSystem.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

    //jpa specification filter allow us to filter trans using any parameter
public interface TransactionRepository extends JpaRepository<Transaction,Long>, JpaSpecificationExecutor<Transaction> {


}
