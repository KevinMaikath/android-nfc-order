package com.example.android_nfc_order.shoppingCartScreen;


public class ShoppingCartModel implements ShoppingCartContract.Model {

  public static String TAG = ShoppingCartModel.class.getSimpleName();

  public ShoppingCartModel() {

  }

  @Override
  public String fetchData() {
    // Log.e(TAG, "fetchData()");
    return "Hello";
  }
}
