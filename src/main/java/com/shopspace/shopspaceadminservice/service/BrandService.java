package com.shopspace.shopspaceadminservice.service;

import com.shopspace.shopspaceadminservice.dto.BrandDTO;
import com.shopspace.shopspaceadminservice.dto.pageable.PageDTO;

public interface BrandService {
    PageDTO<BrandDTO[]> getAllPagedBrands(String search, Integer page, Integer size);

    BrandDTO create(BrandDTO brandDTO);

    BrandDTO update(BrandDTO brandDTO, Long id);

    Boolean delete(Long id);
}
