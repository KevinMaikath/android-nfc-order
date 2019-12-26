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

}
