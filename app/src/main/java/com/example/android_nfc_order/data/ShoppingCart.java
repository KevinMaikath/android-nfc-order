package com.example.android_nfc_order.data;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart implements ShoppingCartContract {

  private static ShoppingCart instance = null;
  private Context context;

  private List<ShopItem> cartList;

  private ShoppingCart(Context context) {
    this.context = context;
    this.cartList = new ArrayList<>();
  }

  public static ShoppingCart getInstance(Context context) {
    if (instance == null) {
      instance = new ShoppingCart(context);
    }
    return instance;
  }

  @Override
  public void loadShopItems(LoadShopItemListCallback callback) {
    callback.setShopItems(this.cartList);
  }

  @Override
  public void addOneToItemCount(Product product) {
    int index = -1;
    for (ShopItem item : cartList) {
      if (item.getName().equals(product.getName())) {
        index = cartList.indexOf(item);
        item.addOne();
        cartList.set(index, item);
        break;
      }
    }

    if (index < 0) {
      ShopItem item = new ShopItem(product.getName(), 1);
      cartList.add(item);
    }

  }

  @Override
  public void removeOneFromItemCount(Product product) {
    for (ShopItem item : cartList) {
      if (item.getName().equals(product.getName())) {
        if (item.getQuantity() <= 1) {
          cartList.remove(item);
          break;
        } else {
          int index = cartList.indexOf(item);
          item.removeOne();
          cartList.set(index, item);
          break;
        }
      }
    }
  }

  @Override
  public void submitOrder(SubmitOrderCallback callback, String documentReference) {
    // TODO submitOrder
  }
}
