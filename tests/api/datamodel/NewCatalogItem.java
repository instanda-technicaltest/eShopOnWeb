package api.datamodel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NewCatalogItem {
    @JsonProperty("catalogBrandId")
    private int catalogBrandId;
    @JsonProperty("catalogTypeId")
    private int catalogTypeId;
    @JsonProperty("description")
    private String description;
    @JsonProperty("name")
    private String name;
    @JsonProperty("pictureUri")
    private String pictureUri;
    @JsonProperty("pictureBase64")
    private String pictureBase64;
    @JsonProperty("pictureName")
    private String pictureName;
    @JsonProperty("price")
    private int price;

    public NewCatalogItem(int catalogBrandId) {
        this.catalogBrandId = catalogBrandId;
        this.catalogTypeId = catalogBrandId;
        this.description = "description" + catalogBrandId;
        this.name = "Name" + catalogBrandId;
        this.pictureUri = "PicURI" + catalogBrandId;
        this.pictureBase64 = "PictureBase" + catalogBrandId;
        this.pictureName = "PictureName" + catalogBrandId;
        this.price = catalogBrandId;
    }

    public void setCatalogBrandId(int catalogBrandId) {
        this.catalogBrandId = catalogBrandId;
    }

    public void setCatalogTypeId(int catalogTypeId) {
        this.catalogTypeId = catalogTypeId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPictureUri(String pictureUri) {
        this.pictureUri = pictureUri;
    }

    public void setPictureBase64(String pictureBase64) {
        this.pictureBase64 = pictureBase64;
    }

    public void setPictureName(String pictureName) {
        this.pictureName = pictureName;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
