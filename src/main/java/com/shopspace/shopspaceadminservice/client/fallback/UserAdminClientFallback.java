package com.shopspace.shopspaceadminservice.client.fallback;

import com.shopspace.shopspaceadminservice.client.UserAdminClient;
import com.shopspace.shopspaceadminservice.dto.UserAdminDTO;
import com.shopspace.shopspaceadminservice.dto.pageable.PageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserAdminClientFallback implements FallbackFactory<UserAdminClient> {
    Logger logger = LoggerFactory.getLogger(UserAdminClientFallback.class);
    @Override
    public UserAdminClient create(Throwable cause) {
        logger.error("An exception occurred when calling the UserClient", cause);
        return new UserAdminClient() {
            @Override
            public PageDTO<UserAdminDTO[]> getPagedUsersAdmins(String search, Integer page, Integer size) {
                logger.error("[Fallback] not call getPagedUsersAdmins");
                return null;
            }

            @Override
            public Optional<UserAdminDTO> getOneUserAdmin(Long id) {
                logger.error("[Fallback] not call getOneUserAdmin");
                return Optional.empty();
            }

            @Override
            public UserAdminDTO create(UserAdminDTO userAdminDTO) {
                logger.error("[Fallback] not call create");
                return null;
            }

            @Override
            public UserAdminDTO delete(Long id) {
                logger.error("[Fallback] not call delete");
                return null;
            }

            @Override
            public Optional<UserAdminDTO> getOneUserAdminByEmail(String email) {
                logger.error("[Fallback] not call getOneUserAdminByEmail");
                return Optional.empty();
            }
        };
    }
}
