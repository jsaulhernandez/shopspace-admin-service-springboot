package com.shopspace.shopspaceadminservice.service.impl;

import com.shopspace.shopspaceadminservice.client.ProductClient;
import com.shopspace.shopspaceadminservice.dto.ProductDTO;
import com.shopspace.shopspaceadminservice.dto.pageable.PageDTO;
import com.shopspace.shopspaceadminservice.exception.DataNotFoundException;
import com.shopspace.shopspaceadminservice.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
    @Autowired
    ProductClient productClient;

    @Override
    public PageDTO<ProductDTO[]> getAllPagedProducts(String search, Integer page, Integer size) {
        return productClient.getPagedProducts(search, page, size);
    }

    @Override
    public ProductDTO create(ProductDTO productDTO){
        return productClient.create(productDTO);
    }

    @Override
    public ProductDTO update(ProductDTO productDTO, Long id){
        Optional<ProductDTO> oldProduct = productClient.getOneProduct(id);

        if (oldProduct.isEmpty()) throw new DataNotFoundException("El producto a modificar no existe.");

        productDTO.setId(oldProduct.get().getId());

        return productClient.create(productDTO);
    }

    @Override
    public Boolean delete(Long id){
        Optional<ProductDTO> product = productClient.getOneProduct(id);
        if (product.isEmpty()) throw new DataNotFoundException("El producto a eliminar no existe.");

        return productClient.delete(id);
    }
}
