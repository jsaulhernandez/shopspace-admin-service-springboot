package com.shopspace.shopspaceadminservice.client;

import com.shopspace.shopspaceadminservice.client.fallback.FaqsFallback;
import com.shopspace.shopspaceadminservice.dto.FaqDTO;
import com.shopspace.shopspaceadminservice.dto.pageable.PageDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@FeignClient(contextId = "faq", value = "${shopspace.feign.config.name}", url = "${shopspace.feign.config.url}/faq", fallbackFactory = FaqsFallback.class)
public interface FaqsClient {
    @GetMapping("/paged")
    PageDTO<FaqDTO[]> getPagedFaqs(@RequestParam(value = "search", defaultValue = "") String search, @RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size);
    @GetMapping("/{id}")
    Optional<FaqDTO> getOneFaq(@PathVariable("id") Long id);
    @PostMapping("/create")
    FaqDTO create(@RequestBody FaqDTO faqDTO);
    @DeleteMapping("/delete/{id}")
    Boolean delete(@PathVariable("id") Long id);
}
