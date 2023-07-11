package com.shopspace.shopspaceadminservice.service.impl;

import com.shopspace.shopspaceadminservice.client.UserAdminClient;
import com.shopspace.shopspaceadminservice.config.PasswordEncoder;
import com.shopspace.shopspaceadminservice.dto.UserAdminDTO;
import com.shopspace.shopspaceadminservice.dto.pageable.PageDTO;
import com.shopspace.shopspaceadminservice.exception.DataNotFoundException;
import com.shopspace.shopspaceadminservice.service.UserAdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class UserAdminServiceImpl implements UserAdminService {
    Logger logger = LoggerFactory.getLogger(UserAdminServiceImpl.class);
    @Autowired
    UserAdminClient userAdminClient;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public PageDTO<UserAdminDTO[]> getPagedUsersAdmins(String search, Integer page, Integer size) {
        return userAdminClient.getPagedUsersAdmins(search, page, size);
    }

    @Override
    public UserAdminDTO create(UserAdminDTO userAdminDTO) {
        String password = passwordEncoder.encoder().encode(userAdminDTO.getPassword());
        userAdminDTO.setPassword(password);
        return userAdminClient.create(userAdminDTO);
    }

    @Override
    public UserAdminDTO update(UserAdminDTO userAdminDTO, Long id) {
        Optional<UserAdminDTO> oldUserAdmin = userAdminClient.getOneUserAdmin(id);

        if (oldUserAdmin.isEmpty()) throw new DataNotFoundException("El usuario admin a modificar no existe.");

        userAdminDTO.setId(oldUserAdmin.get().getId());

        if(userAdminDTO.getPassword().contentEquals("default")) {
            userAdminDTO.setPassword(oldUserAdmin.get().getPassword());
        } else {
            String password = passwordEncoder.encoder().encode(userAdminDTO.getPassword());
            userAdminDTO.setPassword(password);
        }

        return userAdminClient.create(userAdminDTO);
    }

    @Override
    public Boolean delete(Long id) {
        Optional<UserAdminDTO> userAdmin = userAdminClient.getOneUserAdmin(id);
        if (userAdmin.isEmpty()) throw new DataNotFoundException("El usuario admin a eliminar no existe.");

        return userAdminClient.delete(id);
    }
}
