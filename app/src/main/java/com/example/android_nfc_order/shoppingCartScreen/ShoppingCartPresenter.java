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
      refreshData();
    }

    // update the view
    view.get().displayData(viewModel);
  }

  private void refreshData() {
    model.loadShopItemList(new ShoppingCartRepositoryContract.LoadShopItemListCallback() {
      @Override
      public void setShopItems(List<ShopItem> shopItemList) {
        viewModel.shopItemList = shopItemList;
        view.get().displayData(viewModel);
      }
    });

    model.loadTotalPrice(new ShoppingCartRepositoryContract.LoadTotalPriceCallback() {
      @Override
      public void setTotalPrice(Float totalPrice) {
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
    model.submitOrder(
        new ShoppingCartRepositoryContract.SubmitOrderCallback() {
          @Override
          public void orderSubmitted(boolean successful) {
            // TODO submit order callback function
          }
        },
        "docRef");
  }
}
