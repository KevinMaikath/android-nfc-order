package com.example.android_nfc_order.data;

import java.util.List;

public interface ShoppingCartContract {

  interface LoadShopItemListCallback {
    void setShopItems(final List<ShopItem> shopItemList);
  }

  interface SubmitOrderCallback {
    void orderSubmitted(boolean successful);
  }

  void loadShopItems(final LoadShopItemListCallback callback);

  void addOneToItemCount(Product product);

  void removeOneFromItemCount(Product product);

  void submitOrder(final SubmitOrderCallback callback, String documentReference);

}
