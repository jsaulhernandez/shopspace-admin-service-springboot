package com.shopspace.shopspaceadminservice.service.impl;

import com.google.cloud.storage.Blob;
import com.shopspace.shopspaceadminservice.exception.DataNotFoundException;
import com.shopspace.shopspaceadminservice.service.FileService;
import com.shopspace.shopspaceadminservice.util.FileUtil;
import com.shopspace.shopspaceadminservice.util.FirebaseStorageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    FirebaseStorageUtil firebaseStorageUtil;
    @Autowired
    FileUtil fileUtil;

    @Override
    public String upload(String base64, String folder) {
        String identifier = null;

        if (StringUtils.hasText(base64) && StringUtils.hasText(folder))
            identifier = firebaseStorageUtil.upload(base64, folder);

        return identifier;
    }

    @Override
    public boolean delete(String path) {
        return  firebaseStorageUtil.deleteWithPath(path);
    }

    @Override
    public ResponseEntity<byte[]> download(String path) {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        String name = fileUtil.getNameFromPath(path);
        String mimetype = fileUtil.mediaTypeFromName(name);
        System.out.println("info => " + name + " - " +mimetype);
        Blob blob = firebaseStorageUtil.download(path);

        if (blob == null) throw new DataNotFoundException("The requested file doesn't exist");

        byte[] content = blob.getContent();

        headers.add("Content-Description", "File Transfer");
        headers.add("Content-Type", mimetype);
        headers.add("Content-Disposition", "attachment; filename=" + name);
        headers.add("Content-Transfer-Encoding", "binary");
        headers.add("Connection", "Keep-Alive");
        headers.add("Expires", "0");
        headers.add("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
        headers.add("Pragma", "public");
        headers.add("Content-Length", String.valueOf(content.length));

        return new ResponseEntity<>(content, headers, HttpStatus.OK);
    }
}
