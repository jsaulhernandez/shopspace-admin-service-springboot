package com.shopspace.shopspaceadminservice.dto;

import java.math.BigDecimal;

public class SaleBodyDTO {
    private Long id;
    private ProductDTO product;
    private Integer quantity;
    private BigDecimal price;
    private Long saleHeaderId;

    public SaleBodyDTO() {
    }

    public SaleBodyDTO(Long id, ProductDTO product, Integer quantity, BigDecimal price, Long saleHeaderId) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.saleHeaderId = saleHeaderId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getSaleHeaderId() {
        return saleHeaderId;
    }

    public void setSaleHeaderId(Long saleHeaderId) {
        this.saleHeaderId = saleHeaderId;
    }

    @Override
    public String toString() {
        return "SaleBody{" +
                "id=" + id +
                ", product=" + product +
                ", quantity=" + quantity +
                ", price=" + price +
                ", saleHeaderId=" + saleHeaderId +
                '}';
    }
}
