package com.shopspace.shopspaceadminservice.controller;

import com.shopspace.shopspaceadminservice.dto.TypeClassificationDTO;
import com.shopspace.shopspaceadminservice.dto.response.ResponseDTO;
import com.shopspace.shopspaceadminservice.service.TypeClassificationService;
import com.shopspace.shopspaceadminservice.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/type-classification")
public class TypeClassificationController {
    @Autowired
    TypeClassificationService typeClassificationService;

    @GetMapping("/paged")
    public ResponseEntity<ResponseDTO> getAllPagedTypesClassifications(@RequestParam(value = "search", defaultValue = "") String search, @RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size){
        return ResponseUtil.page(typeClassificationService.getAllPagedTypesClassifications(search, page, size));
    }

    @GetMapping("/active")
    public ResponseEntity<ResponseDTO> getAllActiveTypesClassifications(){
        return ResponseUtil.ok(typeClassificationService.getActiveTypesClassifications());
    }

    @PostMapping()
    public ResponseEntity<ResponseDTO> create(@RequestBody TypeClassificationDTO typeClassificationDTO){
        return ResponseUtil.created(typeClassificationService.create(typeClassificationDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> update(@RequestBody TypeClassificationDTO typeClassificationDTO, @PathVariable("id") Long id){
        return ResponseUtil.ok(typeClassificationService.update(typeClassificationDTO, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable("id") Long id){
        return ResponseUtil.ok(typeClassificationService.delete(id));
    }
}
