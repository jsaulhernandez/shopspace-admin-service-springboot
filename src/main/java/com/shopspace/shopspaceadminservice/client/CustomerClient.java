package com.shopspace.shopspaceadminservice.client;

import com.shopspace.shopspaceadminservice.client.fallback.CustomerFallback;
import com.shopspace.shopspaceadminservice.dto.CustomerDTO;
import com.shopspace.shopspaceadminservice.dto.pageable.PageDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(contextId = "customer", value = "${shopspace.feign.config.name}", url = "${shopspace.feign.config.url}/customer", fallbackFactory = CustomerFallback.class)
public interface CustomerClient {
    @GetMapping("/paged")
    PageDTO<CustomerDTO[]> getPagedCustomers(@RequestParam(value = "search", defaultValue = "") String search, @RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size);
}
