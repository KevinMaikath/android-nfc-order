package com.example.android_nfc_order.shoppingCartScreen;

import com.example.android_nfc_order.data.ShoppingCartRepositoryContract;
import com.example.android_nfc_order.data.ShoppingCartRepositoryContract.LoadShopItemListCallback;
import com.example.android_nfc_order.data.ShoppingCartRepositoryContract.SubmitOrderCallback;

import com.example.android_nfc_order.data.ShopItem;
import com.example.android_nfc_order.data.ShoppingCartRepository;

public class ShoppingCartModel implements ShoppingCartContract.Model {

  public static String TAG = ShoppingCartModel.class.getSimpleName();

  private ShoppingCartRepository shoppingCart;

  public ShoppingCartModel(ShoppingCartRepository shoppingCart) {
    this.shoppingCart = shoppingCart;
  }

  @Override
  public void loadShopItemList(LoadShopItemListCallback callback) {
    shoppingCart.loadShopItems(callback);
  }

  @Override
  public void addOneToItemCount(ShopItem item) {
    shoppingCart.addOneToItemCount(item);
  }

  @Override
  public void removeOneFromItemCount(ShopItem item) {
    shoppingCart.removeOneFromItemCount(item);
  }

  @Override
  public void submitOrder(SubmitOrderCallback callback, String docRef) {
    shoppingCart.submitOrder(callback, docRef);
  }

}
