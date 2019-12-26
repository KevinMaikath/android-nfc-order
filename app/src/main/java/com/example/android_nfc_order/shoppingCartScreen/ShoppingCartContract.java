package com.example.android_nfc_order.shoppingCartScreen;

import java.lang.ref.WeakReference;

interface ShoppingCartContract {

  interface View {
    void injectPresenter(Presenter presenter);

    void displayData(ShoppingCartViewModel viewModel);
  }

  interface Presenter {
    void injectView(WeakReference<View> view);

    void injectModel(Model model);

    void injectRouter(Router router);

    void fetchData();
  }

  interface Model {
    String fetchData();
  }

  interface Router {
    void navigateToNextScreen();

    void passDataToNextScreen(ShoppingCartState state);

    ShoppingCartState getDataFromPreviousScreen();
  }
}
