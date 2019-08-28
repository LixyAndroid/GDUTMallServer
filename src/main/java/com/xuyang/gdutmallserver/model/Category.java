package com.xuyang.gdutmallserver.model;

public class Category {
    private Integer id;
    private String categoryName;
    private String categoryIcon;
    private Integer parentId;

    public Category() {
    }

    public Category(String categoryName, Integer parentId) {
        this.categoryName = categoryName;
        this.parentId = parentId;
    }

    public Category(String categoryName, String categoryIcon, Integer parentId) {
        this.categoryName = categoryName;
        this.categoryIcon = categoryIcon;
        this.parentId = parentId;
    }

    public Integer getId() {

        return this.id;
    }

    public void setId(Integer id) {

        this.id = id;
    }

    public String getCategoryName() {

        return this.categoryName;
    }

    public void setCategoryName(String categoryName) {

        this.categoryName = (categoryName == null ? null : categoryName.trim());
    }

    public String getCategoryIcon() {

        return this.categoryIcon;
    }

    public void setCategoryIcon(String categoryIcon) {
        this.categoryIcon = (categoryIcon == null ? null : categoryIcon.trim());
    }

    public Integer getParentId() {
        return this.parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
}

