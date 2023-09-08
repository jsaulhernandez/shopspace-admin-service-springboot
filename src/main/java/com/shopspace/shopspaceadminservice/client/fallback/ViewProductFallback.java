package com.shopspace.shopspaceadminservice.client.fallback;

import com.shopspace.shopspaceadminservice.client.ViewProductClient;
import com.shopspace.shopspaceadminservice.dto.ViewProductDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ViewProductFallback implements FallbackFactory<ViewProductClient> {
    Logger logger = LoggerFactory.getLogger(ViewProductFallback.class);

    @Override
    public ViewProductClient create(Throwable cause) {
        logger.error("An exception occurred when calling the ViewProductClient", cause);
        return new ViewProductClient() {
            @Override
            public ViewProductDTO create(ViewProductDTO viewProductDTO) {
                logger.error("[Fallback] not call ViewProductDTO");
                return null;
            }

            @Override
            public Optional<ViewProductDTO> getOneViewProduct(Long id) {
                logger.error("[Fallback] not call getOneViewProduct");
                return Optional.empty();
            }
        };
    }
}
