package com.shopspace.shopspaceadminservice.service;

import com.shopspace.shopspaceadminservice.dto.BrandDTO;
import com.shopspace.shopspaceadminservice.dto.pageable.PageDTO;

import java.util.List;

public interface BrandService {
    PageDTO<BrandDTO[]> getAllPagedBrands(String search, Integer page, Integer size);

    List<BrandDTO> getAllActiveBrands();

    BrandDTO create(BrandDTO brandDTO);

    BrandDTO update(BrandDTO brandDTO, Long id);

    Boolean delete(Long id);
}
