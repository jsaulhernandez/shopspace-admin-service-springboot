package com.shopspace.shopspaceadminservice.service;

import com.shopspace.shopspaceadminservice.dto.CustomerDTO;
import com.shopspace.shopspaceadminservice.dto.pageable.PageDTO;

public interface CustomerService {
    PageDTO<CustomerDTO[]> getAllPagedCustomers(String search, Integer page, Integer size);
}
