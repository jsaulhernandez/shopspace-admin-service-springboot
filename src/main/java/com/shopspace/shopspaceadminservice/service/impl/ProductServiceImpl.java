package com.shopspace.shopspaceadminservice.service.impl;

import com.shopspace.shopspaceadminservice.client.ProductClient;
import com.shopspace.shopspaceadminservice.dto.ProductDTO;
import com.shopspace.shopspaceadminservice.dto.ViewProductDTO;
import com.shopspace.shopspaceadminservice.dto.pageable.PageDTO;
import com.shopspace.shopspaceadminservice.enums.FirebaseFoldersEnum;
import com.shopspace.shopspaceadminservice.exception.DataNotFoundException;
import com.shopspace.shopspaceadminservice.service.FileService;
import com.shopspace.shopspaceadminservice.service.ProductService;
import com.shopspace.shopspaceadminservice.util.ShopSpaceAdminUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
    @Autowired
    ProductClient productClient;
    @Autowired
    FileService fileService;

    @Override
    public PageDTO<ProductDTO[]> getAllPagedProducts(String search, Integer page, Integer size) {
        return productClient.getPagedProducts(search, page, size);
    }

    @Override
    public ProductDTO create(ProductDTO productDTO){
        logger.info("processing file to create product {}", productDTO.getTitle());

        var views = productDTO.getViewProducts().stream().peek(vp -> {
            var identifier = fileService.upload(vp.getImage(), FirebaseFoldersEnum.PRODUCTS);;
            vp.setImage(identifier);
            logger.info("new file is {}", identifier);
        }).toList();

        productDTO.setViewProducts(views);

        return productClient.create(productDTO);
    }

    @Override
    public ProductDTO update(ProductDTO productDTO, Long id){
        var oldProduct = productClient.getOneProduct(id);

        if (oldProduct.isEmpty()) throw new DataNotFoundException("Product to updated doesn't exists");

        productDTO.setId(oldProduct.get().getId());

        logger.info("processing file to update product {}", productDTO.getTitle());

        //cleaning firebase storage
        this.cleanFirebaseStorage(oldProduct.get().getViewProducts(), productDTO.getViewProducts());

        //validation to images
        var views = productDTO.getViewProducts().stream().peek(vp -> {
            // sometime some views doesn't have id, for this case the var must have null
            ViewProductDTO oldView = null;
            if (vp.getId() != null) {
                oldView = oldProduct.get().getViewProducts().stream().filter(ovp -> ovp.getId().longValue() == vp.getId().longValue()).findFirst().orElse(null);
            }

            if (vp.getImage().contentEquals("default")) {
                //setting value of Data Base
               if (oldView != null) {
                   logger.info("path from DB to {}: {}", productDTO.getTitle(), oldView.getImage());
                   vp.setImage(oldView.getImage());
               }
            } else {
                if (oldView != null) {
                    //removing old image
                    logger.info("removing file to {}, because have base64", oldView.getImage());
                    fileService.delete(ShopSpaceAdminUtil.encode(oldView.getImage()));
                }

                //saving new image
                var identifier = fileService.upload(vp.getImage(), FirebaseFoldersEnum.PRODUCTS);;
                vp.setImage(identifier);
                logger.info("new file is {}", identifier);
            }
        }).toList();

        productDTO.setViewProducts(views);

        return productClient.create(productDTO);
    }

    @Override
    public Boolean delete(Long id){
        var product = productClient.getOneProduct(id);

        if (product.isEmpty()) throw new DataNotFoundException("Product to deleted doesn't exists.");

        //removing images
        logger.info("processing file to delete product {}", product.get().getTitle());
        product.get().getViewProducts().forEach(vp -> {
            logger.info("removing file {}", vp.getImage());
            fileService.delete(ShopSpaceAdminUtil.encode(vp.getImage()));
        });

        return productClient.delete(id);
    }

    //method to delete unnecessary files
    public void cleanFirebaseStorage(List<ViewProductDTO> oldData, List<ViewProductDTO> newData){
        //records with id
        var recordIds = newData.stream().filter(vp -> vp.getId() != null).toList();
        //getting records that exists in old data array to remove images
        var deleteFiles = oldData.stream().filter(ovp -> recordIds.stream().noneMatch(evp -> evp.getId().longValue() == ovp.getId().longValue())).toList();

        deleteFiles.forEach(dvp -> {
            logger.info("removing path {} to view with id {}", dvp.getImage(), dvp.getId());
            fileService.delete(ShopSpaceAdminUtil.encode(dvp.getImage()));
        });
    }
}
