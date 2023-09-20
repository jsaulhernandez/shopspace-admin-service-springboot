package com.shopspace.shopspaceadminservice.service;

import com.shopspace.shopspaceadminservice.dto.ProductDTO;
import com.shopspace.shopspaceadminservice.dto.ViewProductDTO;
import com.shopspace.shopspaceadminservice.dto.pageable.PageDTO;

public interface ProductService {
    PageDTO<ProductDTO[]> getAllPagedProducts(String search, Integer page, Integer size);

    ProductDTO create(ProductDTO productDTO);

    ProductDTO update(ProductDTO productDTO, Long id);

    Boolean delete(Long id);
}
