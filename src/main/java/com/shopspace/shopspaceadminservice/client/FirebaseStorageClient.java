package com.shopspace.shopspaceadminservice.client;

import com.shopspace.shopspaceadminservice.client.fallback.FirebaseStorageFallback;
import com.shopspace.shopspaceadminservice.dto.FileDTO;
import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(contextId = "storage", value = "${shopspace-storage.feign.config.name}", url = "${shopspace-storage.feign.config.url}/file", fallbackFactory = FirebaseStorageFallback.class)
public interface FirebaseStorageClient {
    @PostMapping("/upload")
    String upload(@RequestBody FileDTO request);

    @GetMapping("/delete")
    Boolean delete(@RequestParam String path);

    @GetMapping("/download")
    Response download(@RequestParam String path);
}
