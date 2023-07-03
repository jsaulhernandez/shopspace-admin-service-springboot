package com.shopspace.shopspaceadminservice.service.impl;

import com.shopspace.shopspaceadminservice.client.CategoryClient;
import com.shopspace.shopspaceadminservice.dto.CategoryDTO;
import com.shopspace.shopspaceadminservice.dto.pageable.PageDTO;
import com.shopspace.shopspaceadminservice.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryClient categoryClient;

    @Override
    public PageDTO<CategoryDTO[]> getAllPagedCategories(String search, Integer page, Integer size) {
        return categoryClient.getAllPagedCategories(search, page, size);
    }
}
