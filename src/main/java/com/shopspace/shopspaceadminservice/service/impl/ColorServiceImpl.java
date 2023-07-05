package com.shopspace.shopspaceadminservice.service.impl;

import com.shopspace.shopspaceadminservice.client.ColorClient;
import com.shopspace.shopspaceadminservice.dto.ColorDTO;
import com.shopspace.shopspaceadminservice.dto.pageable.PageDTO;
import com.shopspace.shopspaceadminservice.exception.DataNotFoundException;
import com.shopspace.shopspaceadminservice.service.ColorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ColorServiceImpl implements ColorService {
    Logger logger = LoggerFactory.getLogger(ColorServiceImpl.class);
    @Autowired
    ColorClient colorClient;

    @Override
    public PageDTO<ColorDTO[]> getAllPagedColors(String search, Integer page, Integer size) {
        return colorClient.getPagedColors(search, page, size);
    }

    @Override
    public ColorDTO create(ColorDTO colorDTO){
        return colorClient.create(colorDTO);
    }

    @Override
    public ColorDTO update(ColorDTO colorDTO, Long id){
        Optional<ColorDTO> oldColor = colorClient.getOneColor(id);

        if (oldColor.isEmpty()) throw new DataNotFoundException("El color a modificar no existe.");

        colorDTO.setId(oldColor.get().getId());

        return colorClient.create(colorDTO);
    }

    @Override
    public Boolean delete(Long id){
        Optional<ColorDTO> color = colorClient.getOneColor(id);
        if (color.isEmpty()) throw new DataNotFoundException("El color a eliminar no existe.");

        return colorClient.delete(id);
    }
}
