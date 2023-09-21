package com.shopspace.shopspaceadminservice.service;

public interface FileService {
    String upload(String base64, String folder);

    boolean delete(String path);
}
