package com.shopspace.shopspaceadminservice.client.fallback;

import com.shopspace.shopspaceadminservice.client.CouponClient;
import com.shopspace.shopspaceadminservice.dto.CouponDTO;
import com.shopspace.shopspaceadminservice.dto.pageable.PageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CouponFallback implements FallbackFactory<CouponClient> {
    Logger logger = LoggerFactory.getLogger(CouponFallback.class);

    @Override
    public CouponClient create(Throwable cause) {
        logger.error("An exception occurred when calling the CouponClient", cause);
        return new CouponClient() {
            @Override
            public PageDTO<CouponDTO[]> getAllCoupons(String search, Integer page, Integer size) {
                logger.error("[Fallback] not call getAllCoupons");
                return null;
            }

            @Override
            public Optional<CouponDTO> getOneCoupon(Long id) {
                logger.error("[Fallback] not call getOneCoupon");
                return Optional.empty();
            }

            @Override
            public Optional<CouponDTO> getCouponByCode(String code) {
                logger.error("[Fallback] not call getCouponByCode");
                return Optional.empty();
            }

            @Override
            public CouponDTO create(CouponDTO couponDTO) {
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
