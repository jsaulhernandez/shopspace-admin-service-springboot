package com.shopspace.shopspaceadminservice.service.impl;

import com.shopspace.shopspaceadminservice.client.TypeClassificationClient;
import com.shopspace.shopspaceadminservice.dto.TypeClassificationDTO;
import com.shopspace.shopspaceadminservice.dto.pageable.PageDTO;
import com.shopspace.shopspaceadminservice.exception.DataNotFoundException;
import com.shopspace.shopspaceadminservice.service.TypeClassificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeClassificationServiceImpl implements TypeClassificationService {
    Logger logger = LoggerFactory.getLogger(TypeClassificationServiceImpl.class);
    @Autowired
    TypeClassificationClient typeClassificationClient;

    @Override
    public PageDTO<TypeClassificationDTO[]> getAllPagedTypesClassifications(String search, Integer page, Integer size) {
        return typeClassificationClient.getPagedTypesClassifications(search, page, size);
    }

    @Override
    public List<TypeClassificationDTO> getActiveTypesClassifications() {
        return typeClassificationClient.getTypesClassificationsByStatus(1);
    }

    @Override
    public TypeClassificationDTO create(TypeClassificationDTO typeClassificationDTO){
        return typeClassificationClient.create(typeClassificationDTO);
    }

    @Override
    public TypeClassificationDTO update(TypeClassificationDTO typeClassificationDTO, Long id){
        Optional<TypeClassificationDTO> oldTypeClassification = typeClassificationClient.getOneTypeClassification(id);

        if (oldTypeClassification.isEmpty()) throw new DataNotFoundException("El tipo de clasificación a modificar no existe.");

        typeClassificationDTO.setId(oldTypeClassification.get().getId());

        return typeClassificationClient.create(typeClassificationDTO);
    }

    @Override
    public Boolean delete(Long id){
        Optional<TypeClassificationDTO> typeClassification = typeClassificationClient.getOneTypeClassification(id);
        if (typeClassification.isEmpty()) throw new DataNotFoundException("El tipo de clasificación a eliminar no existe.");

        return typeClassificationClient.delete(id);
    }
}
