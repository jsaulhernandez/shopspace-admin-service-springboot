package com.shopspace.shopspaceadminservice.client;

import com.shopspace.shopspaceadminservice.client.fallback.PaymentMethodFallback;
import com.shopspace.shopspaceadminservice.dto.PaymentMethodDTO;
import com.shopspace.shopspaceadminservice.dto.pageable.PageDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@FeignClient(contextId = "payment-method", value = "${shopspace.feign.config.name}", url = "${shopspace.feign.config.url}/payment-method", fallbackFactory = PaymentMethodFallback.class)
public interface PaymentMethodClient {
    @GetMapping("/paged")
    PageDTO<PaymentMethodDTO[]> getPagedPaymentsMethods(@RequestParam String search, @RequestParam Integer page, @RequestParam Integer size);
    @GetMapping("/{id}")
    Optional<PaymentMethodDTO> getOnePaymentMethod(@PathVariable("id") Long id);
    @PostMapping("/create")
    PaymentMethodDTO create(@RequestBody PaymentMethodDTO paymentMethodDTO);
    @DeleteMapping("/delete/{id}")
    Boolean delete(@PathVariable("id") Long id);
}
