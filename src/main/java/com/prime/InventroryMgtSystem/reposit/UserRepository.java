package com.prime.InventroryMgtSystem.reposit;

import com.prime.InventroryMgtSystem.models.Category;
import com.prime.InventroryMgtSystem.models.Transaction;
import com.prime.InventroryMgtSystem.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long>, JpaSpecificationExecutor<Transaction> {
    Optional<User> findByEmail(String email);


}
