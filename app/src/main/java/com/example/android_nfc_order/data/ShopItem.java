package com.example.android_nfc_order.data;

public class ShopItem {

  private String name;
  private int quantity;

  public ShopItem(String name, int quantity) {
    this.name = name;
    this.quantity = quantity;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public void addOne() {
    this.quantity += 1;
  }

  public void removeOne() {
    this.quantity -= 1;
  }
}
