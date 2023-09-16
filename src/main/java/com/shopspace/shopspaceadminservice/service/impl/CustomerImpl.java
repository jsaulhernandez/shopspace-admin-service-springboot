package com.shopspace.shopspaceadminservice.service.impl;

import com.shopspace.shopspaceadminservice.client.CustomerClient;
import com.shopspace.shopspaceadminservice.dto.CustomerDTO;
import com.shopspace.shopspaceadminservice.dto.pageable.PageDTO;
import com.shopspace.shopspaceadminservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerImpl implements CustomerService {
    @Autowired
    private CustomerClient customerClient;

    @Override
    public PageDTO<CustomerDTO[]> getAllPagedCustomers(String search, Integer page, Integer size) {
        return customerClient.getPagedCustomers(search, page, size);
    }
}
