package com.prime.InventroryMgtSystem.reposit;

import com.prime.InventroryMgtSystem.models.Category;
import com.prime.InventroryMgtSystem.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> {


}
