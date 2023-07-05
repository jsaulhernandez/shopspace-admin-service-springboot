package com.shopspace.shopspaceadminservice.dto;

public class ClassificationCategoriesDTO {
    private Long id;
    private String name;
    private Integer status;
    private CategoriesDTO categories;

    public ClassificationCategoriesDTO() {
    }

    public ClassificationCategoriesDTO(Long id, String name, Integer status, CategoriesDTO categories) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.categories = categories;
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

    public CategoriesDTO getCategories() {
        return categories;
    }

    public void setCategories(CategoriesDTO categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "ClassificationCategoriesDTO{" + "id=" + id + ", name='" + name + '\'' + ", status=" + status + ", categories=" + categories + '}';
    }
}
