package com.shopspace.shopspaceadminservice.client;

import com.shopspace.shopspaceadminservice.client.fallback.TypeClassificationFallback;
import com.shopspace.shopspaceadminservice.dto.TypeClassificationDTO;
import com.shopspace.shopspaceadminservice.dto.pageable.PageDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@FeignClient(contextId = "type-classification", value = "${shopspace.feign.config.name}", url = "${shopspace.feign.config.url}/type-classification", fallbackFactory = TypeClassificationFallback.class)
public interface TypeClassificationClient {
    @GetMapping("/paged")
    PageDTO<TypeClassificationDTO[]> getPagedTypesClassifications(@RequestParam String search, @RequestParam Integer page, @RequestParam Integer size);
    @GetMapping("/{id}")
    Optional<TypeClassificationDTO> getOneTypeClassification(@PathVariable("id") Long id);
    @PostMapping("/create")
    TypeClassificationDTO create(@RequestBody TypeClassificationDTO typeClassificationDTO);
    @DeleteMapping("/delete/{id}")
    Boolean delete(@PathVariable("id") Long id);
}
