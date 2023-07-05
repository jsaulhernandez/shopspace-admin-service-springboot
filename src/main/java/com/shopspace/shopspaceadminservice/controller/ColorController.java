package com.shopspace.shopspaceadminservice.controller;

import com.shopspace.shopspaceadminservice.dto.ColorDTO;
import com.shopspace.shopspaceadminservice.dto.response.ResponseDTO;
import com.shopspace.shopspaceadminservice.service.ColorService;
import com.shopspace.shopspaceadminservice.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/color")
public class ColorController {
    @Autowired
    ColorService colorService;

    @GetMapping("/paged")
    public ResponseEntity<ResponseDTO> getAllPagedColors(@RequestParam(value = "search", defaultValue = "") String search, @RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size){
        return ResponseUtil.page(colorService.getAllPagedColors(search, page, size));
    }

    @PostMapping()
    public ResponseEntity<ResponseDTO> create(@RequestBody ColorDTO colorDTO){
        return ResponseUtil.created(colorService.create(colorDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> update(@RequestBody ColorDTO colorDTO, @PathVariable("id") Long id){
        return ResponseUtil.ok(colorService.update(colorDTO, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable("id") Long id){
        return ResponseUtil.ok(colorService.delete(id));
    }
}
