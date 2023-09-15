package com.shopspace.shopspaceadminservice.client.fallback;

import com.shopspace.shopspaceadminservice.client.FaqsClient;
import com.shopspace.shopspaceadminservice.dto.FaqDTO;
import com.shopspace.shopspaceadminservice.dto.pageable.PageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class FaqsFallback implements FallbackFactory<FaqsClient> {
    Logger logger = LoggerFactory.getLogger(FaqsFallback.class);

    @Override
    public FaqsClient create(Throwable cause) {
        logger.error("An exception occurred when calling the FaqsClient", cause);
        return new FaqsClient() {
            @Override
            public PageDTO<FaqDTO[]> getPagedFaqs(String search, Integer page, Integer size) {
                logger.error("[Fallback] not call getPagedFaqs");
                return null;
            }

            @Override
            public Optional<FaqDTO> getOneFaq(Long id) {
                logger.error("[Fallback] not call getOneFaq");
                return Optional.empty();
            }

            @Override
            public FaqDTO create(FaqDTO faqDTO) {
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
