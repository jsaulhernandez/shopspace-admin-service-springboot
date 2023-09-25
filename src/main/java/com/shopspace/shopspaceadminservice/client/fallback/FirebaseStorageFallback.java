package com.shopspace.shopspaceadminservice.client.fallback;

import com.shopspace.shopspaceadminservice.client.FirebaseStorageClient;
import com.shopspace.shopspaceadminservice.dto.FileDTO;
import feign.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;

public class FirebaseStorageFallback implements FallbackFactory<FirebaseStorageClient> {
    Logger logger = LoggerFactory.getLogger(FirebaseStorageFallback.class);

    @Override
    public FirebaseStorageClient create(Throwable cause) {
        logger.error("An exception occurred when calling the StorageClient", cause);
        return new FirebaseStorageClient() {
            @Override
            public String upload(FileDTO request) {
                logger.error("[Fallback] not call upload");
                return null;
            }

            @Override
            public Boolean delete(String path) {
                logger.error("[Fallback] not call delete");
                return null;
            }

            @Override
            public Response download(String path) {
                logger.error("[Fallback] not call download");
                return null;
            }
        };
    }
}
