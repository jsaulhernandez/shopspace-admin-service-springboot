package com.shopspace.shopspaceadminservice.service;

import com.shopspace.shopspaceadminservice.dto.UserCustomerDTO;
import com.shopspace.shopspaceadminservice.dto.pageable.PageDTO;

public interface CustomerService {
    PageDTO<UserCustomerDTO[]> getAllPagedUsersCustomers(String search, Integer page, Integer size);

    UserCustomerDTO updateUserCustomerStatus(UserCustomerDTO userCustomerDTO, Long id);
}
