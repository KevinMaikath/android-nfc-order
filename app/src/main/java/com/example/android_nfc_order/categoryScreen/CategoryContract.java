package com.example.android_nfc_order.categoryScreen;

import com.example.android_nfc_order.data.CategoryItem;
import com.example.android_nfc_order.data.RepositoryContract;

import java.lang.ref.WeakReference;

interface CategoryContract {

  interface View {
    void injectPresenter(Presenter presenter);

    void displayData(CategoryViewModel viewModel);
  }

  interface Presenter {
    void injectView(WeakReference<View> view);

    void injectModel(Model model);

    void injectRouter(Router router);

    void fetchData();

    void onCategoryItemClicked(CategoryItem item);
  }

  interface Model {
    void getCategoryItemList(String collectionRef,
                             RepositoryContract.LoadCategoryItemListCallback callback);
  }

  interface Router {
    void navigateToNextScreen();

    void passDataToNextScreen(CategoryState state);

    CategoryState getDataFromCatalogScreen();
  }
}
