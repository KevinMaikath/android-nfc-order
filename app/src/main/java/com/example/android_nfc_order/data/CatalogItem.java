package com.example.android_nfc_order.data;

public class CatalogItem {

  private String name;
  private String imgUrl;
  private String itemsRef;

    public CatalogItem(String name, String imgUrl, String itemsRef) {
        this.name = name;
        this.imgUrl = imgUrl;
        this.itemsRef = itemsRef;
    }

  public CatalogItem() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getImgUrl() {
    return imgUrl;
  }

  public void setImgUrl(String imgUrl) {
    this.imgUrl = imgUrl;
  }

  public String getItemsRef() {
    return itemsRef;
  }

  public void setItemsRef(String itemsRef) {
    this.itemsRef = itemsRef;
  }
}
