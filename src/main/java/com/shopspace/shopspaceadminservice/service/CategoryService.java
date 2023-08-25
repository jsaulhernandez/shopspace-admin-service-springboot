package com.shopspace.shopspaceadminservice.service;

import com.shopspace.shopspaceadminservice.dto.CategoryDTO;
import com.shopspace.shopspaceadminservice.dto.pageable.PageDTO;

import java.util.List;

public interface CategoryService {
    PageDTO<CategoryDTO[]> getAllPagedCategories(String search, Integer page, Integer size);

    List<CategoryDTO> getAllActiveCategories();

    CategoryDTO create(CategoryDTO categoryDTO);

    CategoryDTO update(CategoryDTO categoryDTO, Long id);

    Boolean delete(Long id);
}
