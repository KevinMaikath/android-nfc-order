package com.example.android_nfc_order.categoryScreen;

import android.util.Log;
import android.content.Intent;
import android.content.Context;

import com.example.android_nfc_order.app.AppMediator;

public class CategoryRouter implements CategoryContract.Router {

  public static String TAG = CategoryRouter.class.getSimpleName();

  private AppMediator mediator;

  public CategoryRouter(AppMediator mediator) {
    this.mediator = mediator;
  }

  @Override
  public void navigateToNextScreen() {
    Context context = mediator.getApplicationContext();
    Intent intent = new Intent(context, CategoryActivity.class);
    context.startActivity(intent);
  }

  @Override
  public void passDataToNextScreen(CategoryState state) {
    mediator.setCategoryState(state);
  }

  @Override
  public CategoryState getDataFromCatalogScreen() {
    CategoryState state = mediator.getCategoryState();
    return state;
  }
}
