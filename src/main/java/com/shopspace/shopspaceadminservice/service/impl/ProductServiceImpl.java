package com.shopspace.shopspaceadminservice.service.impl;

import com.shopspace.shopspaceadminservice.client.ProductClient;
import com.shopspace.shopspaceadminservice.dto.ProductDTO;
import com.shopspace.shopspaceadminservice.dto.ViewProductDTO;
import com.shopspace.shopspaceadminservice.dto.pageable.PageDTO;
import com.shopspace.shopspaceadminservice.enums.FirebaseFoldersEnum;
import com.shopspace.shopspaceadminservice.exception.ConflictException;
import com.shopspace.shopspaceadminservice.exception.DataNotFoundException;
import com.shopspace.shopspaceadminservice.service.FileService;
import com.shopspace.shopspaceadminservice.service.ProductService;
import com.shopspace.shopspaceadminservice.util.ShopSpaceAdminUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        var views = productDTO.getViewProducts().stream().peek(vp -> {
            logger.info("upload base64 to {}", productDTO.getTitle());
            var identifier = fileService.upload(vp.getImage(), FirebaseFoldersEnum.PRODUCTS);;
            vp.setImage(identifier);
            logger.info("identifier to product {} is {}", productDTO.getTitle(), identifier);
        }).toList();

        productDTO.setViewProducts(views);

        return productClient.create(productDTO);
    }

    @Override
    public ProductDTO update(ProductDTO productDTO, Long id){
        var oldProduct = productClient.getOneProduct(id);

        if (oldProduct.isEmpty()) throw new DataNotFoundException("Product to updated doesn't exists");

        productDTO.setId(oldProduct.get().getId());

        //validation to images
        var oldViews = oldProduct.get().getViewProducts();
        var views = productDTO.getViewProducts().stream().peek(vp -> {
            // sometime some views doesn't have id, for this case the var must have null
            ViewProductDTO oldView = null;
            if (vp.getId() != null) oldView = oldViews.stream().filter(ovp -> ovp.getId().longValue() == vp.getId().longValue()).findFirst().orElse(null);

            if (vp.getImage().contentEquals("default")) {
                //setting value of Data Base
               if (oldView != null) {
                   logger.info("path from DB to {}: {}", productDTO.getTitle(), oldView.getImage());
                   vp.setImage(oldView.getImage());
               }
            } else {
                if (oldView != null) {
                    //removing old image
                    logger.info("removing file to {}: {}", productDTO.getTitle(), oldView.getImage());
                    fileService.delete(ShopSpaceAdminUtil.encode(oldView.getImage()));
                }

                //saving new image
                logger.info("upload base64 to {}", productDTO.getTitle());
                var identifier = fileService.upload(vp.getImage(), FirebaseFoldersEnum.PRODUCTS);;
                vp.setImage(identifier);
                logger.info("identifier to product {} is {}", productDTO.getTitle(), identifier);
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
        product.get().getViewProducts().forEach(vp -> {
            logger.info("removing file to {}: {}", product.get().getTitle(), vp.getImage());
            fileService.delete(ShopSpaceAdminUtil.encode(vp.getImage()));
        });

        return productClient.delete(id);
    }
}
