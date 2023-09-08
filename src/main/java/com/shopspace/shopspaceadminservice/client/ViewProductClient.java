package com.shopspace.shopspaceadminservice.client;

import com.shopspace.shopspaceadminservice.client.fallback.ViewProductFallback;
import com.shopspace.shopspaceadminservice.dto.ProductDTO;
import com.shopspace.shopspaceadminservice.dto.ViewProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@FeignClient(contextId = "view-product", value = "${shopspace.feign.config.name}", url = "${shopspace.feign.config.url}/view-product", fallbackFactory = ViewProductFallback.class)
public interface ViewProductClient {
    @PostMapping("/create")
    ViewProductDTO create(@RequestBody ViewProductDTO viewProductDTO);
    @GetMapping("/{id}")
    public Optional<ViewProductDTO> getOneViewProduct(@PathVariable("id") Long id);
}
