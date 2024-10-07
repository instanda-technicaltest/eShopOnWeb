package org.example.api.payload;

public class CreateCatalogItemPayload {
    private int catalogBrandId;
    private int catalogTypeId;
    private String description;
    private String name;
    private String pictureUri;
    private String pictureBase64;
    private String pictureName;
    private double price;
    public CreateCatalogItemPayload(int catalogBrandId, int catalogTypeId, String description, String name, String pictureUri, String pictureBase64, String pictureName, double price) {
        this.catalogBrandId = catalogBrandId;
        this.catalogTypeId = catalogTypeId;
        this.description = description;
        this.name = name;
        this.pictureUri = pictureUri;
        this.pictureBase64 = pictureBase64;
        this.pictureName = pictureName;
        this.price = price;
    }

    // Getters and setters
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

    public void setPictureBase64(String pictureBase64) {
        this.pictureBase64 = pictureBase64;
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
