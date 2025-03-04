package com.prime.InventroryMgtSystem.reposit;

import com.prime.InventroryMgtSystem.models.Category;
import com.prime.InventroryMgtSystem.models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier,Long> {


}
