package com.shopspace.shopspaceadminservice.service;

import com.shopspace.shopspaceadminservice.dto.ColorDTO;
import com.shopspace.shopspaceadminservice.dto.pageable.PageDTO;

public interface ColorService {
    PageDTO<ColorDTO[]> getAllPagedColors(String search, Integer page, Integer size);

    ColorDTO create(ColorDTO colorDTO);

    ColorDTO update(ColorDTO colorDTO, Long id);

    Boolean delete(Long id);
}
