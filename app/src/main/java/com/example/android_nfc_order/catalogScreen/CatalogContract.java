package com.example.android_nfc_order.catalogScreen;

import com.example.android_nfc_order.categoryScreen.CategoryState;
import com.example.android_nfc_order.data.Category;
import com.example.android_nfc_order.data.RepositoryContract;

import java.lang.ref.WeakReference;

interface CatalogContract {

  interface View {
    void injectPresenter(Presenter presenter);

    void displayData(CatalogViewModel viewModel);
  }

  interface Presenter {
    void injectView(WeakReference<View> view);

    void injectModel(Model model);

    void injectRouter(Router router);

    void fetchData();

    /**
     * Pass the clicked category and go to CategoryScreen.
     *
     * @param item: category clicked.
     */
    void onCatalogItemClicked(Category item);

    /**
     * Go to ShoppingCartScreen.
     */
    void onCartButtonClicked();
  }

  interface Model {
    /**
     * Tell the repository to load all the categories.
     *
     * @param callback: returns the category list when the task is finished.
     */
    void getCatalogItems(RepositoryContract.LoadCatalogItemsCallback callback);
  }

  interface Router {
    void navigateToCategoryScreen();

    void passDataToCategoryScreen(CategoryState state);

    void navigateToShoppingCartScreen();
  }
}
