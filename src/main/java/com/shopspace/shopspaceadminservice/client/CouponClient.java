package com.shopspace.shopspaceadminservice.client;

import com.shopspace.shopspaceadminservice.client.fallback.CouponFallback;
import com.shopspace.shopspaceadminservice.dto.CouponDTO;
import com.shopspace.shopspaceadminservice.dto.pageable.PageDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@FeignClient(contextId = "coupon", value = "${shopspace.feign.config.name}", url = "${shopspace.feign.config.url}/coupon", fallbackFactory = CouponFallback.class)
public interface CouponClient {
    @GetMapping("/paged")
    PageDTO<CouponDTO[]> getAllCoupons(@RequestParam(value = "search", defaultValue = "") String search, @RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size);
    @GetMapping("/{id}")
    Optional<CouponDTO> getOneCoupon(@PathVariable("id") Long id);
    @GetMapping("/code/{code}")
    Optional<CouponDTO> getCouponByCode(@PathVariable("code") String code);
    @PostMapping("/create")
    CouponDTO create(@RequestBody CouponDTO couponDTO);
    @DeleteMapping("/delete/{id}")
    Boolean delete(@PathVariable("id") Long id);
}
