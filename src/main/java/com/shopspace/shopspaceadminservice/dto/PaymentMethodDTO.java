package com.shopspace.shopspaceadminservice.dto;

public class PaymentMethodDTO {
    private Long id;
    private String name;
    private Integer status;
    private Integer isCreditDebitCard;

    public PaymentMethodDTO() {
    }

    public PaymentMethodDTO(Long id, String name, Integer status, Integer isCreditDebitCard) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.isCreditDebitCard = isCreditDebitCard;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsCreditDebitCard() {
        return isCreditDebitCard;
    }

    public void setIsCreditDebitCard(Integer isCreditDebitCard) {
        this.isCreditDebitCard = isCreditDebitCard;
    }

    @Override
    public String toString() {
        return "PaymentMethod{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", isCreditDebitCard=" + isCreditDebitCard +
                '}';
    }
}
