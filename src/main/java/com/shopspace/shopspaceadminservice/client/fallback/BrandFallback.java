package com.shopspace.shopspaceadminservice.client.fallback;

import com.shopspace.shopspaceadminservice.client.BrandClient;
import com.shopspace.shopspaceadminservice.dto.BrandDTO;
import com.shopspace.shopspaceadminservice.dto.pageable.PageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BrandFallback implements FallbackFactory<BrandClient> {
    Logger logger = LoggerFactory.getLogger(BrandFallback.class);
    @Override
    public BrandClient create(Throwable cause) {
        logger.error("An exception occurred when calling the BrandClient", cause);
        return new BrandClient() {
            @Override
            public PageDTO<BrandDTO> getPagedBrands(String search, Integer page, Integer size) {
                logger.error("[Fallback] not call getPagedBrands");
                return null;
            }

            @Override
            public Optional<BrandDTO> getOneBrand(Long id) {
                logger.error("[Fallback] not call getOneBrand");
                return Optional.empty();
            }

            @Override
            public BrandDTO create(BrandDTO brandDTO) {
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
