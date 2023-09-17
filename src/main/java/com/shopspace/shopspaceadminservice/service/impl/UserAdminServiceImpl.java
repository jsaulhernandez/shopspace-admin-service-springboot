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
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserAdminServiceImpl implements UserAdminService {
    Logger logger = LoggerFactory.getLogger(UserAdminServiceImpl.class);
    @Autowired
    UserAdminClient userAdminClient;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public PageDTO<UserAdminDTO[]> getPagedUsersAdmins(String search, Integer page, Integer size) {
        PageDTO<UserAdminDTO[]> response = userAdminClient.getPagedUsersAdmins(search, page, size);
        UserAdminDTO[] data = response.getContent();

        // setting null to password field
        List<UserAdminDTO> newData = Arrays.stream(data).peek(userAdminDTO -> userAdminDTO.setPassword(null)).toList();

        // convert list to array
        response.setContent(newData.toArray(new UserAdminDTO[0]));

        return response;
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

        if (oldUserAdmin.isEmpty()) throw new DataNotFoundException("User to update doesn't exists.");

        userAdminDTO.setId(oldUserAdmin.get().getId());

        if(userAdminDTO.getPassword().contentEquals("default")) {
            userAdminDTO.setPassword(oldUserAdmin.get().getPassword());
        } else {
            if (userAdminDTO.getPassword().contentEquals("") || userAdminDTO.getPassword() == null) throw new DataNotFoundException("The password is empty or null.");

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
