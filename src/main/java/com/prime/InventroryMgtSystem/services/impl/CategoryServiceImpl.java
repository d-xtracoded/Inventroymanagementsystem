package com.prime.InventroryMgtSystem.services.impl;

import com.prime.InventroryMgtSystem.dtos.CategoryDTO;
import com.prime.InventroryMgtSystem.dtos.Response;
import com.prime.InventroryMgtSystem.dtos.UserDTO;
import com.prime.InventroryMgtSystem.exceptions.NotFoundExecption;
import com.prime.InventroryMgtSystem.models.Category;
import com.prime.InventroryMgtSystem.reposit.CategoryRepository;
import com.prime.InventroryMgtSystem.services.CategoryService;
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
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper; // use to map dto to entities

    @Override
    public Response createCategory(CategoryDTO categoryDTO) {
        Category categorytosave = modelMapper.map(categoryDTO,Category.class);
        categoryRepository.save(categorytosave);
        return Response.builder()
                .status(200)
                .message("category created succesfully")
                .build();
    }
    @Override
    public Response getAllCategory() {
        List<Category> categories = categoryRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        // return properties of cat without products
     categories.forEach(category -> category.setProducts(null));
        // we need to map to DTO
        List<CategoryDTO> categoryDTOSlist = modelMapper.map(categories, new TypeToken<List<CategoryDTO>>(){}
                .getType());
     return Response.builder()
             .status(200)
             .categories(categoryDTOSlist)
             .message("success")
             .build();
    }
    @Override
    public Response getCategorybyid(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(()-> new NotFoundExecption("Category Not found"));
        CategoryDTO categoryDTO = modelMapper.map(category,CategoryDTO.class);
        return Response.builder()
                .status(200)
                .message("success")
                .category(categoryDTO)
                .build();
    }
    @Override
    public Response updateCategory(Long id, CategoryDTO categoryDTO) {
        Category existingcategory = categoryRepository.findById(id).orElseThrow(()-> new NotFoundExecption("Category not found"));
        existingcategory.setName(categoryDTO.getName());
        categoryRepository.save(existingcategory);

        return Response.builder()
                .status(200)
                .message("Category successfully updated")
                .build();
    }

    @Override
    public Response deleteCategory(Long id) {
        Category deleteCategory = categoryRepository.findById(id).orElseThrow(()-> new NotFoundExecption("Category not found"));
        categoryRepository.delete(deleteCategory);
        return Response.builder()
                .status(200)
                .message("deleted successfully")
                .build();
    }
}
