package com.shopspace.shopspaceadminservice.client;

import com.shopspace.shopspaceadminservice.client.fallback.ProductFallback;
import com.shopspace.shopspaceadminservice.dto.ProductDTO;
import com.shopspace.shopspaceadminservice.dto.pageable.PageDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@FeignClient(contextId = "product", value = "${shopspace.feign.config.name}", url = "${shopspace.feign.config.url}/product", fallbackFactory = ProductFallback.class)
public interface ProductClient {
    @GetMapping("/paged")
    public PageDTO<ProductDTO> getPagedProducts(@RequestParam String search, @RequestParam Integer page, @RequestParam Integer size);
    @GetMapping("/{id}")
    public Optional<ProductDTO> getOneProduct(@PathVariable("id") Long id);
    @PostMapping("/create")
    ProductDTO create(@RequestBody ProductDTO productDTO);
    @DeleteMapping("/delete/{id}")
    Boolean delete(@PathVariable("id") Long id);
}
