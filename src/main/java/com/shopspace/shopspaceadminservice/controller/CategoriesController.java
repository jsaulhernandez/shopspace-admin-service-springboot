package com.shopspace.shopspaceadminservice.controller;

import com.shopspace.shopspaceadminservice.dto.CategoriesDTO;
import com.shopspace.shopspaceadminservice.dto.response.ResponseDTO;
import com.shopspace.shopspaceadminservice.service.CategoriesService;
import com.shopspace.shopspaceadminservice.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoriesController {
    @Autowired
    CategoriesService categoriesService;

    @GetMapping("/paged")
    public ResponseEntity<ResponseDTO> getAllPagedCategories(@RequestParam(value = "search", defaultValue = "") String search, @RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size){
        return ResponseUtil.page(categoriesService.getAllPagedCategories(search, page, size));
    }

    @GetMapping("/active")
    public ResponseEntity<ResponseDTO> getAllActiveCategories(){
        return ResponseUtil.ok(categoriesService.getAllActiveCategories());
    }

    @PostMapping()
    public ResponseEntity<ResponseDTO> create(@RequestBody CategoriesDTO categoriesDTO){
        return ResponseUtil.created(categoriesService.create(categoriesDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> update(@RequestBody CategoriesDTO categoriesDTO, @PathVariable("id") Long id){
        return ResponseUtil.ok(categoriesService.update(categoriesDTO, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable("id") Long id){
        return ResponseUtil.ok(categoriesService.delete(id));
    }
}
