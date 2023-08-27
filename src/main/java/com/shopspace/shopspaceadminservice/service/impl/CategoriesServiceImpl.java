package com.shopspace.shopspaceadminservice.service.impl;

import com.shopspace.shopspaceadminservice.client.CategoriesClient;
import com.shopspace.shopspaceadminservice.dto.CategoriesDTO;
import com.shopspace.shopspaceadminservice.dto.pageable.PageDTO;
import com.shopspace.shopspaceadminservice.exception.DataNotFoundException;
import com.shopspace.shopspaceadminservice.service.CategoriesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriesServiceImpl implements CategoriesService {
    Logger logger = LoggerFactory.getLogger(CategoriesServiceImpl.class);
    @Autowired
    CategoriesClient categoriesClient;

    @Override
    public PageDTO<CategoriesDTO[]> getAllPagedCategories(String search, Integer page, Integer size) {
        return categoriesClient.getPagedCategories(search, page, size);
    }

    @Override
    public List<CategoriesDTO> getAllActiveCategories() {
        return categoriesClient.getCategoriesByStatus(1);
    }

    @Override
    public CategoriesDTO create(CategoriesDTO categoriesDTO){
        return categoriesClient.create(categoriesDTO);
    }

    @Override
    public CategoriesDTO update(CategoriesDTO categoriesDTO, Long id){
        Optional<CategoriesDTO> oldCategories = categoriesClient.getOneCategories(id);

        if (oldCategories.isEmpty()) throw new DataNotFoundException("El tipo de categoría a modificar no existe.");

        categoriesDTO.setId(oldCategories.get().getId());

        return categoriesClient.create(categoriesDTO);
    }

    @Override
    public Boolean delete(Long id){
        Optional<CategoriesDTO> categories = categoriesClient.getOneCategories(id);
        if (categories.isEmpty()) throw new DataNotFoundException("El tipo de categoría a eliminar no existe.");

        return categoriesClient.delete(id);
    }
}
