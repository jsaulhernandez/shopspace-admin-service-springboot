package com.shopspace.shopspaceadminservice.client;

import com.shopspace.shopspaceadminservice.client.fallback.CategoryFallback;
import com.shopspace.shopspaceadminservice.dto.CategoryDTO;
import com.shopspace.shopspaceadminservice.dto.pageable.PageDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@FeignClient(contextId = "category", value = "${shopspace.feign.config.name}", url = "${shopspace.feign.config.url}/category", fallbackFactory = CategoryFallback.class)
public interface CategoryClient {
    @GetMapping("/paged")
    PageDTO<CategoryDTO[]> getAllPagedCategories(@RequestParam String search, @RequestParam Integer page, @RequestParam Integer size);
    @GetMapping("/{id}")
    Optional<CategoryDTO> getOneCategory(@PathVariable("id") Long id);
    @PostMapping("/create")
    CategoryDTO create(@RequestBody CategoryDTO category);
    @DeleteMapping("/delete/{id}")
    Boolean delete(@PathVariable("id") Long id);
}
