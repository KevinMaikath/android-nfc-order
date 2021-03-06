package com.example.android_nfc_order.shoppingCartScreen;

import android.util.Log;

import com.example.android_nfc_order.data.ShopItem;
import com.example.android_nfc_order.data.ShoppingCartRepositoryContract;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

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

    if (viewModel.shopItemList == null) {
      viewModel.shopItemList = new ArrayList<>();
      viewModel.totalPrice = 0;
    }
    refreshData();
  }

  private void refreshData() {
    model.loadShopItemList(new ShoppingCartRepositoryContract.LoadShopItemListCallback() {
      @Override
      public void setShopItems(List<ShopItem> shopItemList, float totalPrice) {
        viewModel.shopItemList = shopItemList;
        viewModel.totalPrice = totalPrice;
        view.get().displayData(viewModel);
      }
    });
  }

  @Override
  public void addItemClicked(ShopItem item) {
    model.addOneToItemCount(item);
    refreshData();
  }

  @Override
  public void removeItemClicked(ShopItem item) {
    model.removeOneFromItemCount(item);
    refreshData();
  }

  @Override
  public void onDoneClicked() {
    if (viewModel.shopItemList.isEmpty()) {
      String text = "Your order is currently empty.";
      view.get().presentToast(text);
    }
    view.get().setUpNFC();
  }

  @Override
  public void onNFCRead(final String[] results) {
    model.submitOrder(
        new ShoppingCartRepositoryContract.SubmitOrderCallback() {
          @Override
          public void orderSubmitted(boolean successful) {
            view.get().successfulSubmitAlert(successful, results[0]);
          }
        },
        results[1]);
  }
}
