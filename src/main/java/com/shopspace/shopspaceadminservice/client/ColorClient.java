package com.shopspace.shopspaceadminservice.client;

import com.shopspace.shopspaceadminservice.client.fallback.ColorFallback;
import com.shopspace.shopspaceadminservice.dto.ColorDTO;
import com.shopspace.shopspaceadminservice.dto.pageable.PageDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@FeignClient(contextId = "color", value = "${shopspace.feign.config.name}", url = "${shopspace.feign.config.url}/color", fallbackFactory = ColorFallback.class)
public interface ColorClient {
    @GetMapping("/paged")
    PageDTO<ColorDTO> getPagedColors(@RequestParam String search, @RequestParam Integer page, @RequestParam Integer size);
    @GetMapping("/{id}")
     Optional<ColorDTO> getOneColor(@PathVariable("id") Long id);
    @PostMapping("/create")
    ColorDTO create(@RequestBody ColorDTO colorDTO);
    @DeleteMapping("/delete/{id}")
    Boolean delete(@PathVariable("id") Long id);
}
