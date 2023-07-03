package com.shopspace.shopspaceadminservice.dto.response;

import java.io.Serializable;

public class ResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private String status;
    private String statusCode;
    private String statusMessage;
    private Object response;

    public ResponseDTO() {
    }

    public ResponseDTO(Object response) {
        this.response = response;
    }

    public ResponseDTO(String status, String statusCode, String statusMessage, Object response) {
        this.status = status;
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
        this.response = response;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }
}
