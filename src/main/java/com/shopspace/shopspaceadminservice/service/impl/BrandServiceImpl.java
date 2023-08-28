package com.shopspace.shopspaceadminservice.service.impl;

import com.shopspace.shopspaceadminservice.client.BrandClient;
import com.shopspace.shopspaceadminservice.dto.BrandDTO;
import com.shopspace.shopspaceadminservice.dto.pageable.PageDTO;
import com.shopspace.shopspaceadminservice.exception.DataNotFoundException;
import com.shopspace.shopspaceadminservice.service.BrandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService {
    Logger logger = LoggerFactory.getLogger(BrandServiceImpl.class);
    @Autowired
    BrandClient brandClient;

    @Override
    public PageDTO<BrandDTO[]> getAllPagedBrands(String search, Integer page, Integer size) {
        return brandClient.getPagedBrands(search, page, size);
    }

    @Override
    public List<BrandDTO> getAllActiveBrands() {
        return brandClient.getBrandsByStatus(1);
    }

    @Override
    public BrandDTO create(BrandDTO brandDTO){
        return brandClient.create(brandDTO);
    }

    @Override
    public BrandDTO update(BrandDTO brandDTO, Long id){
        Optional<BrandDTO> oldBrand = brandClient.getOneBrand(id);

        if (oldBrand.isEmpty()) throw new DataNotFoundException("La marca a modificar no existe.");

        brandDTO.setId(oldBrand.get().getId());

        return brandClient.create(brandDTO);
    }

    @Override
    public Boolean delete(Long id){
        Optional<BrandDTO> brand = brandClient.getOneBrand(id);
        if (brand.isEmpty()) throw new DataNotFoundException("La marca a eliminar no existe.");

        return brandClient.delete(id);
    }
}
