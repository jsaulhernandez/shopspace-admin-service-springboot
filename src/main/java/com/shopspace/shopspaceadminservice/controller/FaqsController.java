package com.shopspace.shopspaceadminservice.controller;

import com.shopspace.shopspaceadminservice.dto.FaqDTO;
import com.shopspace.shopspaceadminservice.dto.response.ResponseDTO;
import com.shopspace.shopspaceadminservice.service.FaqsService;
import com.shopspace.shopspaceadminservice.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/faq")
public class FaqsController {
    @Autowired
    FaqsService faqsService;

    @GetMapping("/paged")
    public ResponseEntity<ResponseDTO> getAllPagedFaqs(@RequestParam(value = "search", defaultValue = "") String search, @RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size){
        return ResponseUtil.page(faqsService.getPagedFaqs(search, page, size));
    }

    @PostMapping()
    public ResponseEntity<ResponseDTO> create(@RequestBody FaqDTO faqDTO){
        return ResponseUtil.created(faqsService.create(faqDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> update(@RequestBody FaqDTO faqDTO, @PathVariable("id") Long id){
        return ResponseUtil.ok(faqsService.update(faqDTO, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable("id") Long id){
        return ResponseUtil.ok(faqsService.delete(id));
    }
}
