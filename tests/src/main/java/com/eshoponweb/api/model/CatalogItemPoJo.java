package com.eshoponweb.api.model;

/**
 * Created by Hiral Yagnik
 * Project Name: eShopOnWeb_AutomationFramework
 */
public class CatalogItemPoJo {
    private int catalogBrandId;
    private int catalogTypeId;
    private String description;
    private String name;
    private String pictureUri;
    private String pictureBase64;
    private String pictureName;
    private double price;

    public int getCatalogBrandId() {
        return catalogBrandId;
    }

    public void setCatalogBrandId(int catalogBrandId) {
        this.catalogBrandId = catalogBrandId;
    }

    public int getCatalogTypeId() {
        return catalogTypeId;
    }

    public void setCatalogTypeId(int catalogTypeId) {
        this.catalogTypeId = catalogTypeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getPictureUri() {
        return pictureUri;
    }

    public void setPictureUri(String pictureUri) {
        this.pictureUri = pictureUri;
    }

    public String getPictureBase64() {
        return pictureBase64;
    }

    public void setPictureBase64(String pictureUri) {
        this.pictureUri = pictureUri;
    }

    public String getPictureName() {
        return pictureName;
    }

    public void setPictureName(String pictureName) {
        this.pictureName = pictureName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
