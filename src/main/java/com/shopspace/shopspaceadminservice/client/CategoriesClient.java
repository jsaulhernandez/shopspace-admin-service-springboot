package com.shopspace.shopspaceadminservice.client;

import com.shopspace.shopspaceadminservice.client.fallback.CategoriesFallback;
import com.shopspace.shopspaceadminservice.dto.CategoriesDTO;
import com.shopspace.shopspaceadminservice.dto.pageable.PageDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@FeignClient(contextId = "categories", value = "${shopspace.feign.config.name}", url = "${shopspace.feign.config.url}/categories", fallbackFactory = CategoriesFallback.class)
public interface CategoriesClient {
    @GetMapping("/paged")
    PageDTO<CategoriesDTO[]> getPagedCategories(@RequestParam String search, @RequestParam Integer page, @RequestParam Integer size);
    @GetMapping("/by-status")
    List<CategoriesDTO> getCategoriesByStatus(@RequestParam Integer status);
    @GetMapping("/{id}")
    Optional<CategoriesDTO> getOneCategories(@PathVariable("id") Long id);
    @PostMapping("/create")
    CategoriesDTO create(@RequestBody CategoriesDTO categoriesDTO);
    @DeleteMapping("/delete/{id}")
    Boolean delete(@PathVariable("id") Long id);
}
