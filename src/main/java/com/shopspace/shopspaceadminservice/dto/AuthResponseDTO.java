package com.shopspace.shopspaceadminservice.dto;

public class AuthResponseDTO {
    private UserAdminDTO user;
    private String token;
    private String expirationToken;

    public AuthResponseDTO() {
    }

    public AuthResponseDTO(UserAdminDTO user, String token, String expirationToken) {
        this.user = user;
        this.token = token;
        this.expirationToken = expirationToken;
    }

    public UserAdminDTO getUser() {
        return user;
    }

    public void setUser(UserAdminDTO user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getExpirationToken() {
        return expirationToken;
    }

    public void setExpirationToken(String expirationToken) {
        this.expirationToken = expirationToken;
    }

    @Override
    public String toString() {
        return "AuthResponseDTO{" +
                "user=" + user +
                ", token='" + token + '\'' +
                ", expirationToken='" + expirationToken + '\'' +
                '}';
    }
}
