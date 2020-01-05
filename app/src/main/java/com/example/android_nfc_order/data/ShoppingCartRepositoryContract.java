package com.example.android_nfc_order.data;

import java.util.List;

public interface ShoppingCartRepositoryContract {

  interface LoadShopItemListCallback {
    void setShopItems(final List<ShopItem> shopItemList, final float totalPrice);
  }

  interface SubmitOrderCallback {
    void orderSubmitted(boolean successful);
  }

  /**
   * Return a list with the shopItems denoting the current order.
   *
   * @param callback: returns the item list when the task is completed.
   */
  void loadShopItems(final LoadShopItemListCallback callback);

  /**
   * Increase a shopItem's quantity by one, or create it if it doesn't exist in the current cartList.
   *
   * @param item: item to add.
   */
  void addOneToItemCount(ShopItem item);

  /**
   * Decrease a shopItem's quantity by one, or remove it if it's quantity will be decreased to 0.
   *
   * @param item: item to remove.
   */
  void removeOneFromItemCount(ShopItem item);

  /**
   * Submit the current order's data to Firebase.
   *
   * @param callback:          called when the task is completed.
   * @param documentReference: Firestore document where the data will be stored.
   */
  void submitOrder(final SubmitOrderCallback callback, String documentReference);

}
