package com.example.android_nfc_order.data;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartRepository implements ShoppingCartRepositoryContract {

  private static ShoppingCartRepository instance = null;
  private Context context;

  private List<ShopItem> cartList;
  private float totalPrice;

  private ShoppingCartRepository(Context context) {
    this.context = context;
    this.cartList = new ArrayList<>();
    this.totalPrice = 0;
  }

  public static ShoppingCartRepository getInstance(Context context) {
    if (instance == null) {
      instance = new ShoppingCartRepository(context);
    }
    return instance;
  }

  @Override
  public void loadShopItems(LoadShopItemListCallback callback) {
    callback.setShopItems(this.cartList);
  }

  @Override
  public void addOneToItemCount(ShopItem item) {
    int index = -1;
    for (ShopItem cart_item : cartList) {
      if (cart_item.getName().equals(item.getName())) {
        index = cartList.indexOf(cart_item);
        cart_item.addOne();
        cartList.set(index, cart_item);
        break;
      }
    }

    if (index < 0) {
      ShopItem cart_item = new ShopItem(item.getName(), 1, item.getPrice());
      cartList.add(cart_item);
    }

    totalPrice += item.getPrice();
  }

  @Override
  public void removeOneFromItemCount(ShopItem item) {
    for (ShopItem cart_item : cartList) {
      if (cart_item.getName().equals(item.getName())) {
        if (cart_item.getQuantity() <= 1) {
          cartList.remove(cart_item);
          break;
        } else {
          int index = cartList.indexOf(cart_item);
          cart_item.removeOne();
          cartList.set(index, cart_item);
          break;
        }
      }
    }

    totalPrice -= item.getPrice();
  }

  @Override
  public void submitOrder(SubmitOrderCallback callback, String documentReference) {
    // TODO submitOrder
  }

  @Override
  public void loadTotalPrice(LoadTotalPriceCallback callback) {
    callback.setTotalPrice(totalPrice);
  }

}
