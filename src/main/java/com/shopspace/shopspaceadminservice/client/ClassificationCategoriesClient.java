package com.shopspace.shopspaceadminservice.client;

import com.shopspace.shopspaceadminservice.client.fallback.ClassificationCategoriesFallback;
import com.shopspace.shopspaceadminservice.dto.ClassificationCategoriesDTO;
import com.shopspace.shopspaceadminservice.dto.pageable.PageDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@FeignClient(contextId = "classification-categories", value = "${shopspace.feign.config.name}", url = "${shopspace.feign.config.url}/classification-categories", fallbackFactory = ClassificationCategoriesFallback.class)
public interface ClassificationCategoriesClient {
    @GetMapping("/paged")
    PageDTO<ClassificationCategoriesDTO> getPagedClassificationCategories(@RequestParam String search, @RequestParam Integer page, @RequestParam Integer size);
    @GetMapping("/{id}")
    Optional<ClassificationCategoriesDTO> getOneClassificationCategories(@PathVariable("id") Long id);
    @PostMapping("/create")
    ClassificationCategoriesDTO create(@RequestBody ClassificationCategoriesDTO classificationCategoriesDTO);
    @DeleteMapping("/delete/{id}")
    Boolean delete(@PathVariable("id") Long id);
}
