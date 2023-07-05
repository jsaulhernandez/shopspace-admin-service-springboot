package com.shopspace.shopspaceadminservice.client.fallback;

import com.shopspace.shopspaceadminservice.client.ProductClient;
import com.shopspace.shopspaceadminservice.dto.ProductDTO;
import com.shopspace.shopspaceadminservice.dto.pageable.PageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProductFallback implements FallbackFactory<ProductClient> {
    Logger logger = LoggerFactory.getLogger(ProductFallback.class);

    @Override
    public ProductClient create(Throwable cause) {
        logger.error("An exception occurred when calling the ProductClient", cause);
        return new ProductClient() {
            @Override
            public PageDTO<ProductDTO[]> getPagedProducts(String search, Integer page, Integer size) {
                logger.error("[Fallback] not call getPagedProducts");
                return null;
            }

            @Override
            public Optional<ProductDTO> getOneProduct(Long id) {
                logger.error("[Fallback] not call getOneProduct");
                return Optional.empty();
            }

            @Override
            public ProductDTO create(ProductDTO productDTO) {
                logger.error("[Fallback] not call create");
                return null;
            }

            @Override
            public Boolean delete(Long id) {
                logger.error("[Fallback] not call delete");
                return null;
            }
        };
    }
}
