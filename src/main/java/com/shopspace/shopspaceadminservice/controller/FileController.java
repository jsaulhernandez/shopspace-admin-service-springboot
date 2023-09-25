package com.shopspace.shopspaceadminservice.controller;

import com.shopspace.shopspaceadminservice.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/file")
public class FileController {
    @Autowired
    FileService fileService;

    @GetMapping("/download")
    public ResponseEntity<byte[]> download(@RequestParam String path) {
        return fileService.getFile(path);
    }
}
