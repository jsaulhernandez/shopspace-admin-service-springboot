package com.shopspace.shopspaceadminservice.service;

import com.shopspace.shopspaceadminservice.dto.CategoryDTO;
import com.shopspace.shopspaceadminservice.dto.pageable.PageDTO;

public interface CategoryService {
    PageDTO<CategoryDTO[]> getAllPagedCategories(String search, Integer page, Integer size);
}
