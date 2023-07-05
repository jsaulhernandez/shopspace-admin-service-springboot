package com.shopspace.shopspaceadminservice.client.fallback;

import com.shopspace.shopspaceadminservice.client.CategoryClient;
import com.shopspace.shopspaceadminservice.dto.CategoryDTO;
import com.shopspace.shopspaceadminservice.dto.pageable.PageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

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
        };
    }
}
