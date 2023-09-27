package com.shopspace.shopspaceadminservice.service.impl;

import com.shopspace.shopspaceadminservice.client.CategoryClient;
import com.shopspace.shopspaceadminservice.dto.CategoryDTO;
import com.shopspace.shopspaceadminservice.dto.pageable.PageDTO;
import com.shopspace.shopspaceadminservice.enums.FirebaseFoldersEnum;
import com.shopspace.shopspaceadminservice.exception.DataNotFoundException;
import com.shopspace.shopspaceadminservice.service.CategoryService;
import com.shopspace.shopspaceadminservice.service.FileService;
import com.shopspace.shopspaceadminservice.util.ShopSpaceAdminUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);
    @Autowired
    CategoryClient categoryClient;
    @Autowired
    FileService fileService;

    @Override
    public PageDTO<CategoryDTO[]> getAllPagedCategories(String search, Integer page, Integer size) {
        return categoryClient.getAllPagedCategories(search, page, size);
    }

    @Override
    public List<CategoryDTO> getAllActiveCategories() {
        return categoryClient.getCategoriesByStatus(1);
    }

    @Override
    public CategoryDTO create(CategoryDTO categoryDTO){
        logger.info("processing file to create category {}", categoryDTO.getName());
        var identifier = fileService.upload(categoryDTO.getImage(), FirebaseFoldersEnum.CATEGORIES);
        categoryDTO.setImage(identifier);

        return categoryClient.create(categoryDTO);
    }

    @Override
    public CategoryDTO update(CategoryDTO categoryDTO, Long id){
        Optional<CategoryDTO> oldCategory = categoryClient.getOneCategory(id);

        if (oldCategory.isEmpty()) throw new DataNotFoundException("Category to updated doesn't exists.");

        categoryDTO.setId(oldCategory.get().getId());

        logger.info("processing file to update category {}", categoryDTO.getName());
        if (categoryDTO.getImage().contentEquals("default")) {
            logger.info("path from DB {}", oldCategory.get().getImage());
            categoryDTO.setImage(oldCategory.get().getImage());
        } else {
            //removing old image
            logger.info("removing file to {}, because have base64", oldCategory.get().getImage());
            fileService.delete(ShopSpaceAdminUtil.encode(oldCategory.get().getImage()));

            //saving new image
            var identifier = fileService.upload(categoryDTO.getImage(), FirebaseFoldersEnum.CATEGORIES);;
            categoryDTO.setImage(identifier);
            logger.info("new file is {}", identifier);
        }

        return categoryClient.create(categoryDTO);
    }

    @Override
    public Boolean delete(Long id){
        Optional<CategoryDTO> oldCategory = categoryClient.getOneCategory(id);
        if (oldCategory.isEmpty()) throw new DataNotFoundException("Category to removed doesn't exists.");

        return categoryClient.delete(id);
    }
}
