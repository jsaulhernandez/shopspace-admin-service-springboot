package com.shopspace.shopspaceadminservice.dto;

public class UserAdminDTO {
    private Long id;
    private String email;
    private String password;
    private Integer status;
    private String fullName;
    private String code;

    public UserAdminDTO() {
    }

    public UserAdminDTO(Long id, String email, String password, Integer status, String fullName, String code) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.status = status;
        this.fullName = fullName;
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                ", fullName='" + fullName + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
