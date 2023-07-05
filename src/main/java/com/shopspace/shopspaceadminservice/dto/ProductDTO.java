package com.shopspace.shopspaceadminservice.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class ProductDTO {
    private Long id;
    private String name;
    private String title;
    private String description;
    private BigDecimal price;
    private Integer stock;
    private String model;
    private String modelNumber;
    private Date releaseDate;
    private List<ProductDetailDTO> productDetails;
    private BrandDTO brand;
    List<ColorDTO> colors;

    public ProductDTO() {
    }

    public ProductDTO(Long id, String name, String title, String description, BigDecimal price, Integer stock, String model, String modelNumber, Date releaseDate, List<ProductDetailDTO> productDetails, BrandDTO brand, List<ColorDTO> colors) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.model = model;
        this.modelNumber = modelNumber;
        this.releaseDate = releaseDate;
        this.productDetails = productDetails;
        this.brand = brand;
        this.colors = colors;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
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

    public List<ProductDetailDTO> getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(List<ProductDetailDTO> productDetails) {
        this.productDetails = productDetails;
    }

    public BrandDTO getBrand() {
        return brand;
    }

    public void setBrand(BrandDTO brand) {
        this.brand = brand;
    }

    public List<ColorDTO> getColors() {
        return colors;
    }

    public void setColors(List<ColorDTO> colors) {
        this.colors = colors;
    }

    @Override
    public String toString() {
        return "ProductDTO{" + "id=" + id + ", name='" + name + '\'' + ", title='" + title + '\'' + ", description='" + description + '\'' + ", price=" + price + ", stock=" + stock + ", model='" + model + '\'' + ", modelNumber='" + modelNumber + '\'' + ", releaseDate=" + releaseDate + ", productDetails=" + productDetails + ", brand=" + brand + ", colors=" + colors + '}';
    }
}
