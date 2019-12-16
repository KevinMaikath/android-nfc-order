package com.example.android_nfc_order.data;

import com.google.firebase.firestore.DocumentReference;

import java.util.List;

public class Category {

  private String name;
  private String imgUrl;
  private List<DocumentReference> items;

    public Category(String name, String imgUrl, List<DocumentReference> items) {
        this.name = name;
        this.imgUrl = imgUrl;
        this.items = items;
    }

  public Category() {
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

  public List<DocumentReference> getItems() {
    return items;
  }

  public void setItems(List<DocumentReference> items) {
    this.items = items;
  }
}
