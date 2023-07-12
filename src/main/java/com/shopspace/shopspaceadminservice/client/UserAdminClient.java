package com.shopspace.shopspaceadminservice.client;

import com.shopspace.shopspaceadminservice.client.fallback.UserAdminClientFallback;
import com.shopspace.shopspaceadminservice.dto.UserAdminDTO;
import com.shopspace.shopspaceadminservice.dto.pageable.PageDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@FeignClient(contextId = "user-admin", value = "${shopspace.feign.config.name}", url = "${shopspace.feign.config.url}/user-admin", fallbackFactory = UserAdminClientFallback.class)
public interface UserAdminClient {
    @GetMapping("/paged")
    PageDTO<UserAdminDTO[]> getPagedUsersAdmins(@RequestParam(value = "search", defaultValue = "") String search, @RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size);
    @GetMapping("/{id}")
    Optional<UserAdminDTO> getOneUserAdmin(@PathVariable("id") Long id);
    @PostMapping("/create")
    UserAdminDTO create(@RequestBody UserAdminDTO userAdminDTO);
    @DeleteMapping("/delete/{id}")
    Boolean delete(@PathVariable("id") Long id);
    @GetMapping(path = "/email/{email}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    Optional<UserAdminDTO> getOneUserAdminByEmail(@PathVariable String email);
}
