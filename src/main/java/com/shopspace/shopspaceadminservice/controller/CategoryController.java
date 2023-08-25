package com.shopspace.shopspaceadminservice.controller;

import com.shopspace.shopspaceadminservice.dto.CategoryDTO;
import com.shopspace.shopspaceadminservice.dto.response.ResponseDTO;
import com.shopspace.shopspaceadminservice.service.CategoryService;
import com.shopspace.shopspaceadminservice.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("/paged")
    public ResponseEntity<ResponseDTO> getAllCategories(@RequestParam(value = "search", defaultValue = "") String search, @RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size){
        return ResponseUtil.page(categoryService.getAllPagedCategories(search, page, size));
    }

    @GetMapping("/active")
    public ResponseEntity<ResponseDTO> getAllActiveCategories(){
        return ResponseUtil.ok(categoryService.getAllActiveCategories());
    }

    @PostMapping()
    public ResponseEntity<ResponseDTO> create(@RequestBody CategoryDTO categoryDTO){
        return ResponseUtil.created(categoryService.create(categoryDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> update(@RequestBody CategoryDTO categoryDTO, @PathVariable("id") Long id){
        return ResponseUtil.ok(categoryService.update(categoryDTO, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable("id") Long id){
        return ResponseUtil.ok(categoryService.delete(id));
    }
}
