package com.prime.InventroryMgtSystem.services;

import com.prime.InventroryMgtSystem.dtos.CategoryDTO;
import com.prime.InventroryMgtSystem.dtos.Response;

public interface CategoryService {

    Response createCategory(CategoryDTO categoryDTO);

}
