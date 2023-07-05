package com.shopspace.shopspaceadminservice.service;

import com.shopspace.shopspaceadminservice.dto.CategoryDTO;
import com.shopspace.shopspaceadminservice.dto.pageable.PageDTO;

public interface CategoryService {
    PageDTO<CategoryDTO[]> getAllPagedCategories(String search, Integer page, Integer size);

    CategoryDTO create(CategoryDTO categoryDTO);

    CategoryDTO update(CategoryDTO categoryDTO, Long id);

    Boolean delete(Long id);
}
