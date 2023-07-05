package com.shopspace.shopspaceadminservice.service.impl;

import com.shopspace.shopspaceadminservice.client.CategoryClient;
import com.shopspace.shopspaceadminservice.dto.CategoryDTO;
import com.shopspace.shopspaceadminservice.dto.pageable.PageDTO;
import com.shopspace.shopspaceadminservice.exception.DataNotFoundException;
import com.shopspace.shopspaceadminservice.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);
    @Autowired
    CategoryClient categoryClient;

    @Override
    public PageDTO<CategoryDTO[]> getAllPagedCategories(String search, Integer page, Integer size) {
        return categoryClient.getAllPagedCategories(search, page, size);
    }

    @Override
    public CategoryDTO create(CategoryDTO categoryDTO){
        return categoryClient.create(categoryDTO);
    }

    @Override
    public CategoryDTO update(CategoryDTO categoryDTO, Long id){
        Optional<CategoryDTO> oldCategory = categoryClient.getOneCategory(id);

        if (oldCategory.isEmpty()) throw new DataNotFoundException("La categoría a modificar no existe.");

        categoryDTO.setId(oldCategory.get().getId());

        return categoryClient.create(categoryDTO);
    }

    @Override
    public Boolean delete(Long id){
        Optional<CategoryDTO> oldCategory = categoryClient.getOneCategory(id);
        if (oldCategory.isEmpty()) throw new DataNotFoundException("La categoría a eliminar no existe.");

        return categoryClient.delete(id);
    }
}
