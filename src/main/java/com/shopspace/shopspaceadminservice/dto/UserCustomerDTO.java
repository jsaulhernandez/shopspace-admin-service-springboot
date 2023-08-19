package com.shopspace.shopspaceadminservice.dto;

import java.util.Date;

public class UserCustomerDTO {
    private Long id;
    private String userName;
    private String password;
    private Integer status;
    private Date createdAt;
    private Date updatedAt;
    private Integer verifiedEmail;
    private CustomerDTO customer;

    public UserCustomerDTO() {
    }

    public UserCustomerDTO(Long id, String userName, String password, Integer status, Date createdAt, Date updatedAt, Integer verifiedEmail, CustomerDTO customer) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.verifiedEmail = verifiedEmail;
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getVerifiedEmail() {
        return verifiedEmail;
    }

    public void setVerifiedEmail(Integer verifiedEmail) {
        this.verifiedEmail = verifiedEmail;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "UserCustomer{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", verifiedEmail=" + verifiedEmail +
                ", customer=" + customer +
                '}';
    }
}
