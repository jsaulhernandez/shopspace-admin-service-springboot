package com.shopspace.shopspaceadminservice.service.impl;

import com.shopspace.shopspaceadminservice.client.CustomerClient;
import com.shopspace.shopspaceadminservice.dto.UserCustomerDTO;
import com.shopspace.shopspaceadminservice.dto.pageable.PageDTO;
import com.shopspace.shopspaceadminservice.exception.DataNotFoundException;
import com.shopspace.shopspaceadminservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerImpl implements CustomerService {
    @Autowired
    private CustomerClient customerClient;

    @Override
    public PageDTO<UserCustomerDTO[]> getAllPagedUsersCustomers(String search, Integer page, Integer size) {
        PageDTO<UserCustomerDTO[]> response = customerClient.getAllPagedUsersCustomers(search, page, size);

        UserCustomerDTO[] data = response.getContent();
        List<UserCustomerDTO> newData = Arrays.stream(data).peek(uc -> {
            uc.setPassword(null);
        }).toList();

        //convert List to Array
        response.setContent(newData.toArray(new UserCustomerDTO[0]));

        return response;
    }

    @Override
    public UserCustomerDTO updateUserCustomerStatus(UserCustomerDTO userCustomerDTO, Long id){
        Optional<UserCustomerDTO> oldUserCustomer = customerClient.getOneUserCustomer(id);

        if (oldUserCustomer.isEmpty()) throw new DataNotFoundException("Customer doesn't exists.");

        userCustomerDTO.setId(oldUserCustomer.get().getId());

        return customerClient.create(userCustomerDTO);
    }
}
