package com.example.android_nfc_order.shoppingCartScreen;

import android.util.Log;
import android.content.Intent;
import android.content.Context;

import com.example.android_nfc_order.app.AppMediator;

public class ShoppingCartRouter implements ShoppingCartContract.Router {

  public static String TAG = ShoppingCartRouter.class.getSimpleName();

  private AppMediator mediator;

  public ShoppingCartRouter(AppMediator mediator) {
    this.mediator = mediator;
  }

  @Override
  public void navigateToNextScreen() {
    Context context = mediator.getApplicationContext();
    Intent intent = new Intent(context, ShoppingCartActivity.class);
    context.startActivity(intent);
  }

  @Override
  public void passDataToNextScreen(ShoppingCartState state) {
    mediator.setShoppingCartState(state);
  }

  @Override
  public ShoppingCartState getDataFromPreviousScreen() {
    ShoppingCartState state = mediator.getShoppingCartState();
    return state;
  }
}
