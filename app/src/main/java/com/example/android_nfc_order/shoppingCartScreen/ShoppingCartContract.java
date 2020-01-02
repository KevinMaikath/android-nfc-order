package com.example.android_nfc_order.shoppingCartScreen;

import com.example.android_nfc_order.data.ShopItem;
import com.example.android_nfc_order.data.ShoppingCartRepositoryContract;

import java.lang.ref.WeakReference;

interface ShoppingCartContract {

  interface View {
    void injectPresenter(Presenter presenter);

    void displayData(ShoppingCartViewModel viewModel);

    void setUpNFC();

    void successfulSubmitAlert(boolean success, String restaurant_name);
  }

  interface Presenter {
    void injectView(WeakReference<View> view);

    void injectModel(Model model);

    void injectRouter(Router router);

    void fetchData();

    void addItemClicked(ShopItem item);

    void removeItemClicked(ShopItem item);

    void onDoneClicked();

    void onNFCRead(String[] results);
  }

  interface Model {
    void loadShopItemList(ShoppingCartRepositoryContract.LoadShopItemListCallback callback);

    void addOneToItemCount(ShopItem item);

    void removeOneFromItemCount(ShopItem item);

    void submitOrder(ShoppingCartRepositoryContract.SubmitOrderCallback callback, String docRef);

    void loadTotalPrice(ShoppingCartRepositoryContract.LoadTotalPriceCallback callback);
  }

  interface Router {

  }
}
