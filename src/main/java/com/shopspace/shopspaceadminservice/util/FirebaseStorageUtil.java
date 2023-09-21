package com.shopspace.shopspaceadminservice.util;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class FirebaseStorageUtil {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    FirebaseProperties firebaseProperties;
    @Autowired
    private FileUtil fileUtil;
    @Value("${files.validations.fileSize.min}")
    private Integer minFileSize;
    @Value("${files.validations.fileSize.max}")
    private Integer maxFileSize;

    @EventListener
    public void init(ApplicationReadyEvent event) {
        // initialize Firebase
        try {
            ClassPathResource serviceAccount = new ClassPathResource("firebase.json");

            FirebaseOptions options = new FirebaseOptions.Builder().setCredentials(GoogleCredentials.fromStream(serviceAccount.getInputStream())).setStorageBucket(firebaseProperties.getBucketName()).build();
            FirebaseApp.initializeApp(options);

            logger.info("Is Firebase Started: " + FirebaseApp.getInstance().getName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     *
     * @return instance of bucket
     */
    private Bucket getBucket() {
        return StorageClient.getInstance().bucket();
    }

    /**
     *
     * @param path folder and file name join
     * @return file
     */
    private Blob getBlobFile(String path) {
        try {
            Bucket bucket = this.getBucket();
            logger.info("instantiating the bucket: {}", bucket.getName());

            return bucket.get(path);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     *
     * @param name file name
     * @param folder folder where be the file
     * @return file
     */
    private Blob getBlobFile(String name, String folder) {
        String path = folder + "/" + name;
        logger.info("showing path: {}", path);
        return this.getBlobFile(path);
    }

    public String upload(String base64, String folder) {
        File file = null;
        Blob blob;

        try {
            if (StringUtils.isEmpty(base64) || (base64.trim().isEmpty()) || (StringUtils.isEmpty(folder) || (folder.trim().isEmpty())))
                return null;

            file = fileUtil.base64ToFile(base64);
            blob = this.upload(file, folder);

            return blob.getName();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            fileUtil.safeCloseAndDelete(file);
        }
    }

    public Blob upload(File file, String folder) throws IOException {
        boolean validate = fileUtil.validateFile(file, minFileSize, maxFileSize);
        if (!validate) return null;

        byte[] fileData = FileUtils.readFileToByteArray(file);

        String path = folder + "/" + file.getName();
        Bucket bucket = this.getBucket();

        return bucket.create(path, fileData);
    }

    public boolean exists(String name, String folder) {
        try {
            Blob blob = this.getBlobFile(name, folder);

            return blob != null && blob.exists();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Blob download(String path) {
        logger.info("main route {}", path);
        return this.getBlobFile(path);
    }

    public String update(String base64, String oldPath, String folder) {
        try {
            String oldName = fileUtil.getNameFromPath(oldPath);

            if (base64 == null && oldName != null) this.delete(oldName, folder);
            else if (StringUtils.isNotEmpty(base64) && base64.contentEquals("default")) return oldPath;

            File file = fileUtil.base64ToFile(base64, oldName);
            Blob blob = this.update(file, folder);

            return blob.getName();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public Blob update(File file, String folder) {
        try {
            boolean delete = this.delete(file.getName(), folder);

            if (!delete) logger.error("An error occurred while deleting the file because it does not exist");

            return this.upload(file, folder);
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

    public boolean deleteWithPath(String path) {
        String name = fileUtil.getNameFromPath(path);
        String folder = fileUtil.getFolderFromPath(path);

        return this.delete(name, folder);
    }

    public boolean delete(String name, String folder) {
        try {
            Blob exists = this.getBlobFile(name, folder);

            if (exists == null) return false;

            return exists.delete();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
