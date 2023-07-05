package com.shopspace.shopspaceadminservice.util;

import com.shopspace.shopspaceadminservice.dto.response.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public class ResponseUtil {
    private ResponseUtil() {
    }

    public static ResponseEntity<ResponseDTO> ok(Object response) {
        return page(Map.of("content", response));
    }

    public static ResponseEntity<ResponseDTO> page(Object response) {
        var responseDTO = new ResponseDTO(HttpStatus.OK.name(), "200", "Operation carried out successfully!", response);
        return ResponseEntity.ok(responseDTO);
    }

    public static ResponseEntity<ResponseDTO> created(Object response) {
        var responseDTO = new ResponseDTO(HttpStatus.CREATED.name(), "201", "Resource created successfully!", response);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    public static ResponseEntity<ResponseDTO> notContent(Object response) {
        var responseDTO = new ResponseDTO(HttpStatus.NOT_FOUND.name(), "404", "Resource not found!", response);
        return new ResponseEntity<>(responseDTO, HttpStatus.NOT_FOUND);
    }

    public static ResponseEntity<ResponseDTO> conflict(Object response) {
        var responseDTO = new ResponseDTO(HttpStatus.CONFLICT.name(), "409", "The operation cannot be performed due to a conflict!", response);
        return new ResponseEntity<>(responseDTO, HttpStatus.CONFLICT);
    }

    public static ResponseEntity<ResponseDTO> unauthorized(Object response) {
        var responseDTO = new ResponseDTO(HttpStatus.UNAUTHORIZED.name(), "401", "You do not have valid authorization!", response);
        return new ResponseEntity<>(responseDTO, HttpStatus.UNAUTHORIZED);
    }

    public static ResponseEntity<ResponseDTO> badRequest(Object response) {
        var responseDTO = new ResponseDTO(HttpStatus.BAD_REQUEST.name(), "400", "Invalid information!", response);
        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity<ResponseDTO> internal(Object response) {
        var responseDTO = new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.name(), "500", "Internal server error!", response);
        return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static ResponseEntity<ResponseDTO> clientUnavailable(Object response, String service) {
        ResponseDTO responseDTO = new ResponseDTO(HttpStatus.SERVICE_UNAVAILABLE.name(), "503", "The client's service "+service+" is not available!!!", response);
        return new ResponseEntity<>(responseDTO, HttpStatus.SERVICE_UNAVAILABLE);
    }
}
