package com.shopspace.shopspaceadminservice.controller;

import com.shopspace.shopspaceadminservice.dto.UserAdminDTO;
import com.shopspace.shopspaceadminservice.dto.response.ResponseDTO;
import com.shopspace.shopspaceadminservice.service.UserAdminService;
import com.shopspace.shopspaceadminservice.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user-admin")
public class UserAdminController {
    @Autowired
    UserAdminService userAdminService;

    @GetMapping("/paged")
    public ResponseEntity<ResponseDTO> getPagedUsersAdmins(@RequestParam(value = "search", defaultValue = "") String search, @RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size){
        return ResponseUtil.page(userAdminService.getPagedUsersAdmins(search, page, size));
    }

    @PostMapping()
    public ResponseEntity<ResponseDTO> create(@RequestBody UserAdminDTO userAdminDTO){
        return ResponseUtil.created(userAdminService.create(userAdminDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> update(@RequestBody UserAdminDTO userAdminDTO, @PathVariable("id") Long id){
        return ResponseUtil.ok(userAdminService.update(userAdminDTO, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable("id") Long id){
        return ResponseUtil.ok(userAdminService.delete(id));
    }
}
