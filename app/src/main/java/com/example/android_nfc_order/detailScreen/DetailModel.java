package com.example.android_nfc_order.detailScreen;


import com.example.android_nfc_order.data.Product;
import com.example.android_nfc_order.data.ShopItem;
import com.example.android_nfc_order.data.ShoppingCartRepository;

public class DetailModel implements DetailContract.Model {

  public static String TAG = DetailModel.class.getSimpleName();

  private ShoppingCartRepository shoppingCart;

  public DetailModel(ShoppingCartRepository shoppingCart) {
    this.shoppingCart = shoppingCart;
  }

  @Override
  public void addProductToShoppingCart(Product product) {
    ShopItem item = new ShopItem(product.getName(), 1, product.getPrice());
    shoppingCart.addOneToItemCount(item);
  }
}
