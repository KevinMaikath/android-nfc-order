package com.example.android_nfc_order.detailScreen;

import android.util.Log;
import android.content.Intent;
import android.content.Context;

import com.example.android_nfc_order.app.AppMediator;

public class DetailRouter implements DetailContract.Router {

  public static String TAG = DetailRouter.class.getSimpleName();

  private AppMediator mediator;

  public DetailRouter(AppMediator mediator) {
    this.mediator = mediator;
  }

  @Override
  public void navigateToNextScreen() {
    Context context = mediator.getApplicationContext();
    Intent intent = new Intent(context, DetailActivity.class);
    context.startActivity(intent);
  }

  @Override
  public void passDataToNextScreen(DetailState state) {
    mediator.setDetailState(state);
  }

  @Override
  public DetailState getDataFromPreviousScreen() {
    DetailState state = mediator.getDetailState();
    return state;
  }
}
