package com.shopspace.shopspaceadminservice.controller;

import com.shopspace.shopspaceadminservice.dto.CategoryDTO;
import com.shopspace.shopspaceadminservice.dto.PaymentMethodDTO;
import com.shopspace.shopspaceadminservice.dto.response.ResponseDTO;
import com.shopspace.shopspaceadminservice.service.PaymentMethod;
import com.shopspace.shopspaceadminservice.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment-method")
public class PaymentMethodController {
    @Autowired
    PaymentMethod paymentMethod;

    @GetMapping("/paged")
    public ResponseEntity<ResponseDTO> getAllPaymentMethods(@RequestParam(value = "search", defaultValue = "") String search, @RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size){
        return ResponseUtil.page(paymentMethod.getPagedPaymentsMethods(search, page, size));
    }

    @PostMapping()
    public ResponseEntity<ResponseDTO> create(@RequestBody PaymentMethodDTO paymentMethodDTO){
        return ResponseUtil.created(paymentMethod.create(paymentMethodDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> update(@RequestBody PaymentMethodDTO paymentMethodDTO, @PathVariable("id") Long id){
        return ResponseUtil.ok(paymentMethod.update(paymentMethodDTO, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable("id") Long id){
        return ResponseUtil.ok(paymentMethod.delete(id));
    }
}
