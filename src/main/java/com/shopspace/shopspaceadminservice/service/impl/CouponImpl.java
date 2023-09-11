package com.shopspace.shopspaceadminservice.service.impl;

import com.shopspace.shopspaceadminservice.client.CouponClient;
import com.shopspace.shopspaceadminservice.dto.CouponDTO;
import com.shopspace.shopspaceadminservice.dto.pageable.PageDTO;
import com.shopspace.shopspaceadminservice.exception.ConflictException;
import com.shopspace.shopspaceadminservice.exception.DataNotFoundException;
import com.shopspace.shopspaceadminservice.service.CouponService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CouponImpl implements CouponService {
    Logger logger = LoggerFactory.getLogger(CouponImpl.class);
    @Autowired
    private CouponClient couponClient;

    @Override
    public PageDTO<CouponDTO[]> getAllPagedCoupons(String search, Integer page, Integer size) {
        return couponClient.getAllCoupons(search, page, size);
    }

    @Override
    public CouponDTO create(CouponDTO couponDTO){

        Optional<CouponDTO> coupon = couponClient.getCouponByCode(couponDTO.getCode());

        if (coupon.isPresent()) {
            if (coupon.get().getCode().contentEquals(couponDTO.getCode()))
                throw new ConflictException("Coupon already exists.");
        }

        return couponClient.create(couponDTO);
    }

    @Override
    public CouponDTO update(CouponDTO couponDTO, Long id){
        Optional<CouponDTO> oldCoupon = couponClient.getOneCoupon(id);

        if (oldCoupon.isEmpty()) throw new DataNotFoundException("Coupon to update doesn't exists.");

        if (!oldCoupon.get().getCode().contentEquals(couponDTO.getCode())) {
            Optional<CouponDTO> coupon = couponClient.getCouponByCode(couponDTO.getCode());

            if (coupon.isPresent()) {
                if (coupon.get().getCode().contentEquals(couponDTO.getCode()))
                    throw new ConflictException("Coupon already exists.");
            }
        }

        couponDTO.setId(oldCoupon.get().getId());

        return couponClient.create(couponDTO);
    }

    @Override
    public Boolean delete(Long id){
        Optional<CouponDTO> coupon = couponClient.getOneCoupon(id);
        if (coupon.isEmpty()) throw new DataNotFoundException("Coupon to remove doesn't exists.");

        return couponClient.delete(id);
    }
}
