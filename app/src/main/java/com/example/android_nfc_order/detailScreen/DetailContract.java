package com.example.android_nfc_order.detailScreen;

import com.example.android_nfc_order.data.Product;

import java.lang.ref.WeakReference;

interface DetailContract {

  interface View {
    void injectPresenter(Presenter presenter);

    void displayData(DetailViewModel viewModel);
  }

  interface Presenter {
    void injectView(WeakReference<View> view);

    void injectModel(Model model);

    void injectRouter(Router router);

    void fetchData();

    void onAddButtonClicked();
  }

  interface Model {
    void addProductToShoppingCart(Product product);
  }

  interface Router {
    void navigateToNextScreen();

    void passDataToNextScreen(DetailState state);

    DetailState getDataFromPreviousScreen();
  }
}
