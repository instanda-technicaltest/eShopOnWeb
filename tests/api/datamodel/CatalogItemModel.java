package api.datamodel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CatalogItemModel {
    @JsonProperty("catalogItem")
    private CatalogItem catalogItem;

    public CatalogItem getCatalogItem() {
        return catalogItem;
    }

    public void setCatalogItem(CatalogItem catalogItem) {
        this.catalogItem = catalogItem;
    }
}
