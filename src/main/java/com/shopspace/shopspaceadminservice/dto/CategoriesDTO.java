package com.shopspace.shopspaceadminservice.dto;

public class CategoriesDTO {
    private Long id;
    private String name;
    private Integer status;
    private CategoryDTO category;

    public CategoriesDTO() {
    }

    public CategoriesDTO(Long id, String name, Integer status, CategoryDTO category) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.category = category;
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

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "CategoriesDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", category=" + category +
                '}';
    }
}
