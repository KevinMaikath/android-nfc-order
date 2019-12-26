package com.example.android_nfc_order.shoppingCartScreen;

import android.util.Log;

import java.lang.ref.WeakReference;

public class ShoppingCartPresenter implements ShoppingCartContract.Presenter {

  public static String TAG = ShoppingCartPresenter.class.getSimpleName();

  private WeakReference<ShoppingCartContract.View> view;
  private ShoppingCartViewModel viewModel;
  private ShoppingCartContract.Model model;
  private ShoppingCartContract.Router router;

  public ShoppingCartPresenter(ShoppingCartState state) {
    viewModel = state;
  }

  @Override
  public void injectView(WeakReference<ShoppingCartContract.View> view) {
    this.view = view;
  }

  @Override
  public void injectModel(ShoppingCartContract.Model model) {
    this.model = model;
  }

  @Override
  public void injectRouter(ShoppingCartContract.Router router) {
    this.router = router;
  }

  @Override
  public void fetchData() {
    // Log.e(TAG, "fetchData()");

    // set passed state
    ShoppingCartState state = router.getDataFromPreviousScreen();
    if (state != null) {
      viewModel.data = state.data;
    }

    if (viewModel.data == null) {
      // call the model
      String data = model.fetchData();

      // set initial state
      viewModel.data = data;
    }

    // update the view
    view.get().displayData(viewModel);

  }


}
