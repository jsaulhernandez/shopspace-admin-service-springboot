package com.shopspace.shopspaceadminservice.service;

import com.shopspace.shopspaceadminservice.dto.CouponDTO;
import com.shopspace.shopspaceadminservice.dto.pageable.PageDTO;

public interface CouponService {
    PageDTO<CouponDTO[]> getAllPagedCoupons(String search, Integer page, Integer size);

    CouponDTO create(CouponDTO couponDTO);

    CouponDTO update(CouponDTO couponDTO, Long id);

    Boolean delete(Long id);
}
