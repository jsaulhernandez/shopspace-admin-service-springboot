package com.shopspace.shopspaceadminservice.client.fallback;

import com.shopspace.shopspaceadminservice.client.ClassificationCategoriesClient;
import com.shopspace.shopspaceadminservice.dto.ClassificationCategoriesDTO;
import com.shopspace.shopspaceadminservice.dto.pageable.PageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ClassificationCategoriesFallback implements FallbackFactory<ClassificationCategoriesClient> {
    Logger logger = LoggerFactory.getLogger(ClassificationCategoriesFallback.class);

    @Override
    public ClassificationCategoriesClient create(Throwable cause) {
        logger.error("An exception occurred when calling the ClassificationCategoriesClient", cause);
        return new ClassificationCategoriesClient() {
            @Override
            public PageDTO<ClassificationCategoriesDTO[]> getPagedClassificationCategories(String search, Integer page, Integer size) {
                logger.error("[Fallback] not call getPagedClassificationCategories");
                return null;
            }

            @Override
            public List<ClassificationCategoriesDTO> getClassificationCategoriesByStatus(Integer status) {
                logger.error("[Fallback] not call getClassificationCategoriesByStatus");
                return null;
            }

            @Override
            public Optional<ClassificationCategoriesDTO> getOneClassificationCategories(Long id) {
                logger.error("[Fallback] not call getOneClassificationCategories");
                return Optional.empty();
            }

            @Override
            public ClassificationCategoriesDTO create(ClassificationCategoriesDTO classificationCategoriesDTO) {
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
