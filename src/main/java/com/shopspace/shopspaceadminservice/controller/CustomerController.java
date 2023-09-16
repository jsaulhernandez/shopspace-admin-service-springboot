package com.shopspace.shopspaceadminservice.controller;

import com.shopspace.shopspaceadminservice.dto.BrandDTO;
import com.shopspace.shopspaceadminservice.dto.UserCustomerDTO;
import com.shopspace.shopspaceadminservice.dto.response.ResponseDTO;
import com.shopspace.shopspaceadminservice.service.CustomerService;
import com.shopspace.shopspaceadminservice.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping("/paged")
    public ResponseEntity<ResponseDTO> getAllPagedUsersCustomers(@RequestParam(value = "search", defaultValue = "") String search, @RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size){
        return ResponseUtil.page(customerService.getAllPagedUsersCustomers(search, page, size));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> update(@RequestBody UserCustomerDTO userCustomerDTO, @PathVariable("id") Long id){
        return ResponseUtil.ok(customerService.updateUserCustomerStatus(userCustomerDTO, id));
    }
}
