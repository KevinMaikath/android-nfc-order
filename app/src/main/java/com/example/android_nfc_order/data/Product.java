package com.example.android_nfc_order.data;

import java.util.List;

public class Product {

  private String name;
  private String imgUrl;
  private Float price;
  private List<String> ingredients;

  public Product(){

  }

  public Product(String name, String imgUrl, Float price, List<String> ingredients) {
    this.name = name;
    this.imgUrl = imgUrl;
    this.price = price;
    this.ingredients = ingredients;
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

  public Float getPrice() {
    return price;
  }

  public void setPrice(Float price) {
    this.price = price;
  }

  public List<String> getIngredients() {
    return ingredients;
  }

  public void setIngredients(List<String> ingredients) {
    this.ingredients = ingredients;
  }
}
