package com.shopspace.shopspaceadminservice.dto;

public class CategoriesDTO {
    private Long id;
    private String name;
    private Integer status;
    private CategoryDTO categoryDTO;

    public CategoriesDTO() {
    }

    public CategoriesDTO(Long id, String name, Integer status, CategoryDTO categoryDTO) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.categoryDTO = categoryDTO;
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

    public CategoryDTO getCategoryDTO() {
        return categoryDTO;
    }

    public void setCategoryDTO(CategoryDTO categoryDTO) {
        this.categoryDTO = categoryDTO;
    }

    @Override
    public String toString() {
        return "CategoriesDTO{" + "id=" + id + ", name='" + name + '\'' + ", status=" + status + ", categoryDTO=" + categoryDTO + '}';
    }
}
