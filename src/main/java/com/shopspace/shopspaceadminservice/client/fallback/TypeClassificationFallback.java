package com.shopspace.shopspaceadminservice.client.fallback;

import com.shopspace.shopspaceadminservice.client.TypeClassificationClient;
import com.shopspace.shopspaceadminservice.dto.TypeClassificationDTO;
import com.shopspace.shopspaceadminservice.dto.pageable.PageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TypeClassificationFallback implements FallbackFactory<TypeClassificationClient> {
    Logger logger = LoggerFactory.getLogger(TypeClassificationFallback.class);

    @Override
    public TypeClassificationClient create(Throwable cause) {
        logger.error("An exception occurred when calling the TypeClassificationClient", cause);
        return new TypeClassificationClient() {
            @Override
            public PageDTO<TypeClassificationDTO[]> getPagedTypesClassifications(String search, Integer page, Integer size) {
                logger.error("[Fallback] not call getPagedTypesClassifications");
                return null;
            }

            @Override
            public List<TypeClassificationDTO> getTypesClassificationsByStatus(Integer status) {
                logger.error("[Fallback] not call getTypesClassificationsByStatus");
                return null;
            }

            @Override
            public Optional<TypeClassificationDTO> getOneTypeClassification(Long id) {
                logger.error("[Fallback] not call getOneTypeClassification");
                return Optional.empty();
            }

            @Override
            public TypeClassificationDTO create(TypeClassificationDTO typeClassificationDTO) {
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
