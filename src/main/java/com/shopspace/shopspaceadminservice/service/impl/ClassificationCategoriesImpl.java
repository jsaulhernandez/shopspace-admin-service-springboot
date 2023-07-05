package com.shopspace.shopspaceadminservice.service.impl;

import com.shopspace.shopspaceadminservice.client.ClassificationCategoriesClient;
import com.shopspace.shopspaceadminservice.dto.ClassificationCategoriesDTO;
import com.shopspace.shopspaceadminservice.dto.pageable.PageDTO;
import com.shopspace.shopspaceadminservice.exception.DataNotFoundException;
import com.shopspace.shopspaceadminservice.service.ClassificationCategoriesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClassificationCategoriesImpl implements ClassificationCategoriesService {
    Logger logger = LoggerFactory.getLogger(ClassificationCategoriesImpl.class);
    @Autowired
    ClassificationCategoriesClient classificationCategoriesClient;

    @Override
    public PageDTO<ClassificationCategoriesDTO[]> getAllPagedClassificationCategories(String search, Integer page, Integer size) {
        return classificationCategoriesClient.getPagedClassificationCategories(search, page, size);
    }

    @Override
    public ClassificationCategoriesDTO create(ClassificationCategoriesDTO classificationCategoriesDTO){
        return classificationCategoriesClient.create(classificationCategoriesDTO);
    }

    @Override
    public ClassificationCategoriesDTO update(ClassificationCategoriesDTO classificationCategoriesDTO, Long id){
        Optional<ClassificationCategoriesDTO> oldClassificationCategories = classificationCategoriesClient.getOneClassificationCategories(id);

        if (oldClassificationCategories.isEmpty()) throw new DataNotFoundException("La clasificación de categoría a modificar no existe.");

        classificationCategoriesDTO.setId(oldClassificationCategories.get().getId());

        return classificationCategoriesClient.create(classificationCategoriesDTO);
    }

    @Override
    public Boolean delete(Long id){
        Optional<ClassificationCategoriesDTO> classificationCategories = classificationCategoriesClient.getOneClassificationCategories(id);
        if (classificationCategories.isEmpty()) throw new DataNotFoundException("La clasificación de categoría a eliminar no existe.");

        return classificationCategoriesClient.delete(id);
    }
}
