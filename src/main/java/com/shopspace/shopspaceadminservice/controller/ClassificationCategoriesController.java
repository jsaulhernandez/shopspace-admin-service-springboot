package com.shopspace.shopspaceadminservice.controller;

import com.shopspace.shopspaceadminservice.dto.ClassificationCategoriesDTO;
import com.shopspace.shopspaceadminservice.dto.response.ResponseDTO;
import com.shopspace.shopspaceadminservice.service.ClassificationCategoriesService;
import com.shopspace.shopspaceadminservice.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/classification-categories")
public class ClassificationCategoriesController {
    @Autowired
    ClassificationCategoriesService classificationCategoriesService;

    @GetMapping("/paged")
    public ResponseEntity<ResponseDTO> getAllPagedClassificationCategories(@RequestParam(value = "search", defaultValue = "") String search, @RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size){
        return ResponseUtil.page(classificationCategoriesService.getAllPagedClassificationCategories(search, page, size));
    }

    @GetMapping("/active")
    public ResponseEntity<ResponseDTO> getAllActiveClassificationCategories(){
        return ResponseUtil.ok(classificationCategoriesService.getAllActiveClassificationCategories());
    }

    @PostMapping()
    public ResponseEntity<ResponseDTO> create(@RequestBody ClassificationCategoriesDTO classificationCategoriesDTO){
        return ResponseUtil.created(classificationCategoriesService.create(classificationCategoriesDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> update(@RequestBody ClassificationCategoriesDTO classificationCategoriesDTO, @PathVariable("id") Long id){
        return ResponseUtil.ok(classificationCategoriesService.update(classificationCategoriesDTO, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable("id") Long id){
        return ResponseUtil.ok(classificationCategoriesService.delete(id));
    }
}
