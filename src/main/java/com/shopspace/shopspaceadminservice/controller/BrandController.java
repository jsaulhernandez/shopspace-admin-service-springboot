package com.shopspace.shopspaceadminservice.controller;

import com.shopspace.shopspaceadminservice.dto.BrandDTO;
import com.shopspace.shopspaceadminservice.dto.response.ResponseDTO;
import com.shopspace.shopspaceadminservice.service.BrandService;
import com.shopspace.shopspaceadminservice.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/brand")
public class BrandController {
    @Autowired
    BrandService brandService;

    @GetMapping("/paged")
    public ResponseEntity<ResponseDTO> getAllPagedBrands(@RequestParam(value = "search", defaultValue = "") String search, @RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size){
        return ResponseUtil.page(brandService.getAllPagedBrands(search, page, size));
    }

    @PostMapping()
    public ResponseEntity<ResponseDTO> create(@RequestBody BrandDTO brandDTO){
        return ResponseUtil.created(brandService.create(brandDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> update(@RequestBody BrandDTO brandDTO, @PathVariable("id") Long id){
        return ResponseUtil.ok(brandService.update(brandDTO, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable("id") Long id){
        return ResponseUtil.ok(brandService.delete(id));
    }
}
