package com.example.android_nfc_order.detailScreen;


import com.example.android_nfc_order.data.Product;
import com.example.android_nfc_order.data.ShoppingCart;

public class DetailModel implements DetailContract.Model {

  public static String TAG = DetailModel.class.getSimpleName();

  private ShoppingCart shoppingCart;

  public DetailModel(ShoppingCart shoppingCart) {
    this.shoppingCart = shoppingCart;
  }

  @Override
  public void addProductToShoppingCart(Product product) {
    shoppingCart.addOneToItemCount(product);
  }
}
