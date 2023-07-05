package com.shopspace.shopspaceadminservice.client.fallback;

import com.shopspace.shopspaceadminservice.client.CategoryClient;
import com.shopspace.shopspaceadminservice.dto.CategoryDTO;
import com.shopspace.shopspaceadminservice.dto.pageable.PageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CategoryFallback implements FallbackFactory<CategoryClient> {
    Logger logger = LoggerFactory.getLogger(CategoryFallback.class);

    @Override
    public CategoryClient create(Throwable cause) {
        logger.error("An exception occurred when calling the CategoryClient", cause);

        return new CategoryClient() {
            @Override
            public PageDTO<CategoryDTO[]> getAllPagedCategories(String search, Integer page, Integer size) {
                logger.error("[Fallback] not call getAllPagedCategories");
                return null;
            }

            @Override
            public Optional<CategoryDTO> getOneCategory(Long id) {
                logger.error("[Fallback] not call getOneCategory");
                return Optional.empty();
            }

            @Override
            public CategoryDTO create(CategoryDTO category) {
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
