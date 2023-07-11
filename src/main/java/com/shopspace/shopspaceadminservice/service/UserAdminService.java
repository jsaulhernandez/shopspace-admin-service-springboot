package com.shopspace.shopspaceadminservice.service;

import com.shopspace.shopspaceadminservice.dto.UserAdminDTO;
import com.shopspace.shopspaceadminservice.dto.pageable.PageDTO;

public interface UserAdminService {
    PageDTO<UserAdminDTO[]> getPagedUsersAdmins(String search, Integer page, Integer size);

    UserAdminDTO create(UserAdminDTO userAdminDTO);

    UserAdminDTO update(UserAdminDTO userAdminDTO, Long id);

    Boolean delete(Long id);
}
