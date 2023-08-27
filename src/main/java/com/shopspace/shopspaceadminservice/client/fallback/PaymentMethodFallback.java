package com.shopspace.shopspaceadminservice.client.fallback;

import com.shopspace.shopspaceadminservice.client.PaymentMethodClient;
import com.shopspace.shopspaceadminservice.dto.PaymentMethodDTO;
import com.shopspace.shopspaceadminservice.dto.pageable.PageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;

import java.util.List;
import java.util.Optional;

public class PaymentMethodFallback  implements FallbackFactory<PaymentMethodClient> {
    Logger logger = LoggerFactory.getLogger(PaymentMethodFallback.class);

    @Override
    public PaymentMethodClient create(Throwable cause) {
        logger.error("An exception occurred when calling the PaymentMethodClient", cause);
        return new PaymentMethodClient() {
            @Override
            public PageDTO<PaymentMethodDTO[]> getPagedPaymentsMethods(String search, Integer page, Integer size) {
                logger.error("[Fallback] not call getPagedPaymentsMethods");
                return null;
            }

            @Override
            public Optional<PaymentMethodDTO> getOnePaymentMethod(Long id) {
                logger.error("[Fallback] not call getOnePaymentMethod");
                return Optional.empty();
            }

            @Override
            public PaymentMethodDTO create(PaymentMethodDTO paymentMethodDTO) {
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
