package com.example.android_nfc_order.detailScreen;

import android.util.Log;
import android.content.Intent;
import android.content.Context;

import com.example.android_nfc_order.app.AppMediator;
import com.example.android_nfc_order.shoppingCartScreen.ShoppingCartActivity;

public class DetailRouter implements DetailContract.Router {

  public static String TAG = DetailRouter.class.getSimpleName();

  private AppMediator mediator;

  public DetailRouter(AppMediator mediator) {
    this.mediator = mediator;
  }

  @Override
  public DetailState getDataFromPreviousScreen() {
    DetailState state = mediator.getDetailState();
    return state;
  }

  @Override
  public void navigateToShoppingCartScreen() {
    Context context = mediator.getApplicationContext();
    Intent intent = new Intent(context, ShoppingCartActivity.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    context.startActivity(intent);
  }
}
