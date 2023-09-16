package com.shopspace.shopspaceadminservice.client.fallback;

import com.shopspace.shopspaceadminservice.client.CustomerClient;
import com.shopspace.shopspaceadminservice.dto.UserCustomerDTO;
import com.shopspace.shopspaceadminservice.dto.pageable.PageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;

import java.util.Optional;

public class CustomerFallback  implements FallbackFactory<CustomerClient> {
    Logger logger = LoggerFactory.getLogger(CustomerFallback.class);

    @Override
    public CustomerClient create(Throwable cause) {
        logger.error("An exception occurred when calling the CustomerClient", cause);
        return new CustomerClient() {
            @Override
            public PageDTO<UserCustomerDTO[]> getAllPagedUsersCustomers(String search, Integer page, Integer size) {
                logger.error("[Fallback] not call getAllPagedUsersCustomers");
                return null;
            }

            @Override
            public Optional<UserCustomerDTO> getOneUserCustomer(Long id) {
                logger.error("[Fallback] not call getOneUserCustomer");
                return Optional.empty();
            }

            @Override
            public UserCustomerDTO create(UserCustomerDTO userCustomerDTO) {
                logger.error("[Fallback] not call create");
                return null;
            }
        };
    }
}
