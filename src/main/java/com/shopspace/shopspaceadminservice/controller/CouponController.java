package com.shopspace.shopspaceadminservice.controller;

import com.shopspace.shopspaceadminservice.dto.CouponDTO;
import com.shopspace.shopspaceadminservice.dto.response.ResponseDTO;
import com.shopspace.shopspaceadminservice.service.CouponService;
import com.shopspace.shopspaceadminservice.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/coupon")
public class CouponController {
    @Autowired
    CouponService couponService;

    @GetMapping("/paged")
    public ResponseEntity<ResponseDTO> getAllPagedCoupons(@RequestParam(value = "search", defaultValue = "") String search, @RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size){
        return ResponseUtil.page(couponService.getAllPagedCoupons(search, page, size));
    }

    @PostMapping()
    public ResponseEntity<ResponseDTO> create(@RequestBody CouponDTO couponDTO){
        return ResponseUtil.created(couponService.create(couponDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> update(@RequestBody CouponDTO couponDTO, @PathVariable("id") Long id){
        return ResponseUtil.ok(couponService.update(couponDTO, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable("id") Long id){
        return ResponseUtil.ok(couponService.delete(id));
    }
}
