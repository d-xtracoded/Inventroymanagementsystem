package com.prime.InventroryMgtSystem.reposit;

import com.prime.InventroryMgtSystem.models.Category;
import com.prime.InventroryMgtSystem.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TransactionRepository extends JpaRepository<Category,Long>, JpaSpecificationExecutor<Transaction> {


}
