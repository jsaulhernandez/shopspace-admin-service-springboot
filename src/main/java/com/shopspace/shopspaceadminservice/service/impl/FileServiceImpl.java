package com.shopspace.shopspaceadminservice.service.impl;

import com.shopspace.shopspaceadminservice.client.FirebaseStorageClient;
import com.shopspace.shopspaceadminservice.dto.FileDTO;
import com.shopspace.shopspaceadminservice.exception.ConflictException;
import com.shopspace.shopspaceadminservice.service.FileService;
import com.shopspace.shopspaceadminservice.util.ShopSpaceAdminUtil;
import feign.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    FirebaseStorageClient storageClient;

    @Override
    public String upload(String base64, String folder) {
        FileDTO file = new FileDTO();
        file.setBase64(base64);
        file.setFolder(folder);

        String response = storageClient.upload(file);

        if (response.contentEquals("FileExtensionException"))
            throw new ConflictException("The file format is wrong");
         else if (response.contentEquals("FileSizeException"))
             throw new ConflictException("The file size is wrong");

        return response;
    }

    @Override
    public boolean delete(String path) {
        return storageClient.delete(path);
    }

    @Override
    public ResponseEntity<byte[]> getFile(String path){
        byte[] content;
        Response response = storageClient.download(path);
        Response.Body body = response.body();

        try {
            content = body.asInputStream().readAllBytes();
            String contentAsString = new String(content);

            if (contentAsString.contentEquals("ResourceNotFoundException"))
                throw new ConflictException("The file doesn't exist");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new ResponseEntity<>(content, ShopSpaceAdminUtil.getHeaders(response.headers()), HttpStatus.OK);
    }
}
