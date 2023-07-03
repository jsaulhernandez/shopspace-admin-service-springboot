package com.shopspace.shopspaceadminservice.client;

import com.shopspace.shopspaceadminservice.dto.CategoryDTO;
import com.shopspace.shopspaceadminservice.dto.pageable.PageDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "${shopspace.feign.config.name}", url = "${shopspace.feign.config.url}")
public interface CategoryClient {
    @GetMapping("/category/paged")
    PageDTO<CategoryDTO[]> getAllPagedCategories(@RequestParam String search, @RequestParam Integer page,
            @RequestParam Integer size);
}
