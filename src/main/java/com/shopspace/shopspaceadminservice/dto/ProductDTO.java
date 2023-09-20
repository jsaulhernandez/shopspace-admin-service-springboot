package com.shopspace.shopspaceadminservice.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class ProductDTO {
    private Long id;
    private String title;
    private String name;
    private String description;
    private BigDecimal price;
    private String model;
    private String modelNumber;
    private Date releaseDate;
    private Integer status;
    private List<ViewProductDTO> viewProducts;
    private List<ProductDetailDTO> productDetails;
    private TypeClassificationDTO typeClassification;
    private BrandDTO brand;

    public ProductDTO() {
    }

    public ProductDTO(Long id, String title, String name, String description, BigDecimal price, String model, String modelNumber, Date releaseDate, Integer status, List<ViewProductDTO> viewProducts, List<ProductDetailDTO> productDetails, TypeClassificationDTO typeClassification, BrandDTO brand) {
        this.id = id;
        this.title = title;
        this.name = name;
        this.description = description;
        this.price = price;
        this.model = model;
        this.modelNumber = modelNumber;
        this.releaseDate = releaseDate;
        this.status = status;
        this.viewProducts = viewProducts;
        this.productDetails = productDetails;
        this.typeClassification = typeClassification;
        this.brand = brand;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getModelNumber() {
        return modelNumber;
    }

    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<ViewProductDTO> getViewProducts() {
        return viewProducts;
    }

    public void setViewProducts(List<ViewProductDTO> viewProducts) {
        this.viewProducts = viewProducts;
    }

    public List<ProductDetailDTO> getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(List<ProductDetailDTO> productDetails) {
        this.productDetails = productDetails;
    }

    public TypeClassificationDTO getTypeClassification() {
        return typeClassification;
    }

    public void setTypeClassification(TypeClassificationDTO typeClassification) {
        this.typeClassification = typeClassification;
    }

    public BrandDTO getBrand() {
        return brand;
    }

    public void setBrand(BrandDTO brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", model='" + model + '\'' +
                ", modelNumber='" + modelNumber + '\'' +
                ", releaseDate=" + releaseDate +
                ", status=" + status +
                ", viewProducts=" + viewProducts +
                ", productDetails=" + productDetails +
                ", typeClassification=" + typeClassification +
                ", brand=" + brand +
                '}';
    }
}
