package com.shopspace.shopspaceadminservice.service.impl;

import com.shopspace.shopspaceadminservice.service.FileService;
import com.shopspace.shopspaceadminservice.util.FileUtil;
import com.shopspace.shopspaceadminservice.util.FirebaseStorageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
}
