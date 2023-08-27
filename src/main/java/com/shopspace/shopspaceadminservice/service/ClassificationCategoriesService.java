package com.shopspace.shopspaceadminservice.service;

import com.shopspace.shopspaceadminservice.dto.ClassificationCategoriesDTO;
import com.shopspace.shopspaceadminservice.dto.pageable.PageDTO;

import java.util.List;

public interface ClassificationCategoriesService {
    PageDTO<ClassificationCategoriesDTO[]> getAllPagedClassificationCategories(String search, Integer page, Integer size);

    List<ClassificationCategoriesDTO> getAllActiveClassificationCategories();

    ClassificationCategoriesDTO create(ClassificationCategoriesDTO classificationCategoriesDTO);

    ClassificationCategoriesDTO update(ClassificationCategoriesDTO classificationCategoriesDTO, Long id);

    Boolean delete(Long id);
}
