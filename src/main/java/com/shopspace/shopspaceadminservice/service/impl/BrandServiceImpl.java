package com.shopspace.shopspaceadminservice.service.impl;

import com.shopspace.shopspaceadminservice.client.BrandClient;
import com.shopspace.shopspaceadminservice.dto.BrandDTO;
import com.shopspace.shopspaceadminservice.dto.pageable.PageDTO;
import com.shopspace.shopspaceadminservice.enums.FirebaseFoldersEnum;
import com.shopspace.shopspaceadminservice.exception.DataNotFoundException;
import com.shopspace.shopspaceadminservice.service.BrandService;
import com.shopspace.shopspaceadminservice.service.FileService;
import com.shopspace.shopspaceadminservice.util.ShopSpaceAdminUtil;
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
    @Autowired
    FileService fileService;

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
        logger.info("processing file to create brand {}", brandDTO.getName());
        var identifier = fileService.upload(brandDTO.getImage(), FirebaseFoldersEnum.BRANDS);
        brandDTO.setImage(identifier);

        return brandClient.create(brandDTO);
    }

    @Override
    public BrandDTO update(BrandDTO brandDTO, Long id){
        Optional<BrandDTO> oldBrand = brandClient.getOneBrand(id);

        if (oldBrand.isEmpty()) throw new DataNotFoundException("Brand to updated doesn't exists.");

        brandDTO.setId(oldBrand.get().getId());

        logger.info("processing file to update brand {}", brandDTO.getName());
        if (brandDTO.getImage().contentEquals("default")) {
            logger.info("path from DB {}", oldBrand.get().getImage());
            brandDTO.setImage(oldBrand.get().getImage());
        } else {
            //removing old image
            logger.info("removing file to {}, because have base64", oldBrand.get().getImage());
            fileService.delete(ShopSpaceAdminUtil.encode(oldBrand.get().getImage()));

            //saving new image
            var identifier = fileService.upload(brandDTO.getImage(), FirebaseFoldersEnum.BRANDS);;
            brandDTO.setImage(identifier);
            logger.info("new file is {}", identifier);
        }

        return brandClient.create(brandDTO);
    }

    @Override
    public Boolean delete(Long id){
        Optional<BrandDTO> brand = brandClient.getOneBrand(id);
        if (brand.isEmpty()) throw new DataNotFoundException("Brand to removed doesn't exists.");

        return brandClient.delete(id);
    }
}
