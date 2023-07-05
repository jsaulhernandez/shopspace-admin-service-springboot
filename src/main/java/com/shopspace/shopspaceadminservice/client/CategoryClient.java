package com.shopspace.shopspaceadminservice.client;

import com.shopspace.shopspaceadminservice.client.fallback.CategoryFallback;
import com.shopspace.shopspaceadminservice.dto.CategoryDTO;
import com.shopspace.shopspaceadminservice.dto.pageable.PageDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@FeignClient(value = "${shopspace.feign.config.name}", url = "${shopspace.feign.config.url}", fallbackFactory = CategoryFallback.class)
public interface CategoryClient {
    @GetMapping("/category/paged")
    PageDTO<CategoryDTO[]> getAllPagedCategories(@RequestParam String search, @RequestParam Integer page, @RequestParam Integer size);
    @GetMapping("/category/{id}")
    Optional<CategoryDTO> getOneCategory(@PathVariable("id") Long id);
    @PostMapping("/category/create")
    CategoryDTO create(@RequestBody CategoryDTO category);
    @DeleteMapping("/category/delete/{id}")
    Boolean delete(@PathVariable("id") Long id);
}
