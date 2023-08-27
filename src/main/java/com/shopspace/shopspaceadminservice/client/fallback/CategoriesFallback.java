package com.shopspace.shopspaceadminservice.client.fallback;

import com.shopspace.shopspaceadminservice.client.CategoriesClient;
import com.shopspace.shopspaceadminservice.dto.CategoriesDTO;
import com.shopspace.shopspaceadminservice.dto.pageable.PageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CategoriesFallback implements FallbackFactory<CategoriesClient> {
    Logger logger = LoggerFactory.getLogger(CategoriesFallback.class);

    @Override
    public CategoriesClient create(Throwable cause) {
        logger.error("An exception occurred when calling the CategoriesClient", cause);
        return new CategoriesClient() {
            @Override
            public PageDTO<CategoriesDTO[]> getPagedCategories(String search, Integer page, Integer size) {
                logger.error("[Fallback] not call getPagedCategories");
                return null;
            }

            @Override
            public List<CategoriesDTO> getCategoriesByStatus(Integer status) {
                logger.error("[Fallback] not call getCategoriesByStatus");
                return null;
            }

            @Override
            public Optional<CategoriesDTO> getOneCategories(Long id) {
                logger.error("[Fallback] not call getOneCategories");
                return Optional.empty();
            }

            @Override
            public CategoriesDTO create(CategoriesDTO categoriesDTO) {
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
