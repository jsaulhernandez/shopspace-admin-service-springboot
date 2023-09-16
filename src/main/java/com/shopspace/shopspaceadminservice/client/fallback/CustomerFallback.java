package com.shopspace.shopspaceadminservice.client.fallback;

import com.shopspace.shopspaceadminservice.client.CustomerClient;
import com.shopspace.shopspaceadminservice.dto.CustomerDTO;
import com.shopspace.shopspaceadminservice.dto.pageable.PageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;

public class CustomerFallback  implements FallbackFactory<CustomerClient> {
    Logger logger = LoggerFactory.getLogger(CustomerFallback.class);

    @Override
    public CustomerClient create(Throwable cause) {
        logger.error("An exception occurred when calling the CustomerClient", cause);
        return new CustomerClient() {
            @Override
            public PageDTO<CustomerDTO[]> getPagedCustomers(String search, Integer page, Integer size) {
                logger.error("[Fallback] not call getPagedCustomers");
                return null;
            }
        };
    }
}
