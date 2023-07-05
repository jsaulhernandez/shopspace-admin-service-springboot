package com.shopspace.shopspaceadminservice.dto;

public class TypeClassificationDTO {
    private Long id;
    private String name;
    private Integer status;
    private ClassificationCategoriesDTO classificationCategories;

    public TypeClassificationDTO() {
    }

    public TypeClassificationDTO(Long id, String name, Integer status, ClassificationCategoriesDTO classificationCategories) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.classificationCategories = classificationCategories;
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

    public ClassificationCategoriesDTO getClassificationCategories() {
        return classificationCategories;
    }

    public void setClassificationCategories(ClassificationCategoriesDTO classificationCategories) {
        this.classificationCategories = classificationCategories;
    }

    @Override
    public String toString() {
        return "TypeClassificationDTO{" + "id=" + id + ", name='" + name + '\'' + ", status=" + status + ", classificationCategories=" + classificationCategories + '}';
    }
}
