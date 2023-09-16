package com.shopspace.shopspaceadminservice.client;

import com.shopspace.shopspaceadminservice.client.fallback.CustomerFallback;
import com.shopspace.shopspaceadminservice.dto.UserCustomerDTO;
import com.shopspace.shopspaceadminservice.dto.pageable.PageDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@FeignClient(contextId = "customer", value = "${shopspace.feign.config.name}", url = "${shopspace.feign.config.url}/customer", fallbackFactory = CustomerFallback.class)
public interface CustomerClient {
    @GetMapping("/paged")
    PageDTO<UserCustomerDTO[]> getAllPagedUsersCustomers(@RequestParam(value = "search", defaultValue = "") String search, @RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size);
    @GetMapping("/{id}")
    Optional<UserCustomerDTO> getOneUserCustomer(@PathVariable("id") Long id);
    @PostMapping("/create")
    UserCustomerDTO create(@RequestBody UserCustomerDTO userCustomerDTO);
}
