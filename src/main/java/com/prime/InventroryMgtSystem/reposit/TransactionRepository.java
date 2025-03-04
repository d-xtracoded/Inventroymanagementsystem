package com.prime.InventroryMgtSystem.reposit;

import com.prime.InventroryMgtSystem.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Category,Long> {


}
