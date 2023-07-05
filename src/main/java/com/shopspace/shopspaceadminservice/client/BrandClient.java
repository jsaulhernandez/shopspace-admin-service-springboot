package com.shopspace.shopspaceadminservice.client;

import com.shopspace.shopspaceadminservice.client.fallback.BrandFallback;
import com.shopspace.shopspaceadminservice.dto.BrandDTO;
import com.shopspace.shopspaceadminservice.dto.pageable.PageDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@FeignClient(contextId = "brand", value = "${shopspace.feign.config.name}", url = "${shopspace.feign.config.url}/brand", fallbackFactory = BrandFallback.class)
public interface BrandClient {
    @GetMapping("/paged")
    PageDTO<BrandDTO[]> getPagedBrands(@RequestParam(value = "search", defaultValue = "") String search, @RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size);
    @GetMapping("/{id}")
    Optional<BrandDTO> getOneBrand(@PathVariable("id") Long id);
    @PostMapping("/create")
    BrandDTO create(@RequestBody BrandDTO brandDTO);
    @DeleteMapping("/delete/{id}")
    Boolean delete(@PathVariable("id") Long id);
}
