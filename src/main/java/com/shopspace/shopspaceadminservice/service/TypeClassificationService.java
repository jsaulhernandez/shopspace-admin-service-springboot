package com.shopspace.shopspaceadminservice.service;

import com.shopspace.shopspaceadminservice.dto.TypeClassificationDTO;
import com.shopspace.shopspaceadminservice.dto.pageable.PageDTO;

import java.util.List;

public interface TypeClassificationService {
    PageDTO<TypeClassificationDTO[]> getAllPagedTypesClassifications(String search, Integer page, Integer size);

    List<TypeClassificationDTO> getActiveTypesClassifications();

    TypeClassificationDTO create(TypeClassificationDTO typeClassificationDTO);

    TypeClassificationDTO update(TypeClassificationDTO typeClassificationDTO, Long id);

    Boolean delete(Long id);
}
