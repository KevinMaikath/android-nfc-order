package com.example.android_nfc_order.categoryScreen;

import android.content.Intent;
import android.content.Context;
import android.util.Log;

import com.example.android_nfc_order.app.AppMediator;
import com.example.android_nfc_order.detailScreen.DetailActivity;
import com.example.android_nfc_order.detailScreen.DetailState;
import com.example.android_nfc_order.shoppingCartScreen.ShoppingCartActivity;

public class CategoryRouter implements CategoryContract.Router {

  public static String TAG = CategoryRouter.class.getSimpleName();

  private AppMediator mediator;

  public CategoryRouter(AppMediator mediator) {
    this.mediator = mediator;
  }

  @Override
  public void navigateToDetailScreen() {
    Context context = mediator.getApplicationContext();
    Intent intent = new Intent(context, DetailActivity.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    context.startActivity(intent);
  }

  @Override
  public CategoryState getDataFromCatalogScreen() {
    return mediator.getCategoryState();
  }

  @Override
  public void passDataToDetailScreen(DetailState state) {
    mediator.setDetailState(state);
  }

  @Override
  public void navigateToShoppingCartScreen() {
    Context context = mediator.getApplicationContext();
    Intent intent = new Intent(context, ShoppingCartActivity.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    context.startActivity(intent);
  }
}
