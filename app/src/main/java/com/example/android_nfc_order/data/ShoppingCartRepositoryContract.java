package com.example.android_nfc_order.data;

import java.util.List;

public interface ShoppingCartRepositoryContract {

  interface LoadShopItemListCallback {
    void setShopItems(final List<ShopItem> shopItemList);
  }

  interface LoadTotalPriceCallback {
    void setTotalPrice(final Float totalPrice);
  }

  interface SubmitOrderCallback {
    void orderSubmitted(boolean successful);
  }

  void loadTotalPrice(LoadTotalPriceCallback callback);

  void loadShopItems(final LoadShopItemListCallback callback);

  void addOneToItemCount(ShopItem item);

  void removeOneFromItemCount(ShopItem item);

  void submitOrder(final SubmitOrderCallback callback, String documentReference);

}
