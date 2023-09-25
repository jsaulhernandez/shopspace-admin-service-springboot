package com.shopspace.shopspaceadminservice.service;

import org.springframework.http.ResponseEntity;

public interface FileService {
    String upload(String base64, String folder);

    boolean delete(String path);

    ResponseEntity<byte[]> getFile(String path);
}
