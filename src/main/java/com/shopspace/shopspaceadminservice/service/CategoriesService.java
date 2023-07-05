package com.shopspace.shopspaceadminservice.service;

import com.shopspace.shopspaceadminservice.dto.CategoriesDTO;
import com.shopspace.shopspaceadminservice.dto.pageable.PageDTO;

public interface CategoriesService {
    PageDTO<CategoriesDTO[]> getAllPagedCategories(String search, Integer page, Integer size);

    CategoriesDTO create(CategoriesDTO categoriesDTO);

    CategoriesDTO update(CategoriesDTO categoriesDTO, Long id);

    Boolean delete(Long id);
}
