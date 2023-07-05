package com.shopspace.shopspaceadminservice.client.fallback;

import com.shopspace.shopspaceadminservice.client.ColorClient;
import com.shopspace.shopspaceadminservice.dto.ColorDTO;
import com.shopspace.shopspaceadminservice.dto.pageable.PageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ColorFallback implements FallbackFactory<ColorClient> {
    Logger logger = LoggerFactory.getLogger(ColorFallback.class);

    @Override
    public ColorClient create(Throwable cause) {
        logger.error("An exception occurred when calling the ColorClient", cause);
        return new ColorClient() {
            @Override
            public PageDTO<ColorDTO> getPagedColors(String search, Integer page, Integer size) {
                logger.error("[Fallback] not call getPagedColors");
                return null;
            }

            @Override
            public Optional<ColorDTO> getOneColor(Long id) {
                logger.error("[Fallback] not call getOneColor");
                return Optional.empty();
            }

            @Override
            public ColorDTO create(ColorDTO colorDTO) {
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
