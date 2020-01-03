package com.example.android_nfc_order.detailScreen;

import com.example.android_nfc_order.data.Product;

import java.lang.ref.WeakReference;

interface DetailContract {

  interface View {
    void injectPresenter(Presenter presenter);

    void displayData(DetailViewModel viewModel);

    /**
     * Present a toast with an specified message.
     *
     * @param text: message to show.
     */
    void presentToast(String text);
  }

  interface Presenter {
    void injectView(WeakReference<View> view);

    void injectModel(Model model);

    void injectRouter(Router router);

    void fetchData();

    /**
     * Tell the model to add the current product to the cartList.
     */
    void onAddButtonClicked();

    /**
     * Go to ShoppingCartScreen.
     */
    void onCartButtonClicked();
  }

  interface Model {
    /**
     * Tell the repository to add a product to the cartList.
     *
     * @param product: clicked product.
     */
    void addProductToShoppingCart(Product product);
  }

  interface Router {
    DetailState getDataFromPreviousScreen();

    void navigateToShoppingCartScreen();
  }
}
