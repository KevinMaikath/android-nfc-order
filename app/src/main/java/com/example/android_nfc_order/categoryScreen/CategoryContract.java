package com.example.android_nfc_order.categoryScreen;

import com.example.android_nfc_order.data.Product;
import com.example.android_nfc_order.data.RepositoryContract;
import com.example.android_nfc_order.detailScreen.DetailState;
import com.google.firebase.firestore.DocumentReference;

import java.lang.ref.WeakReference;
import java.util.List;

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

    void onCategoryItemClicked(Product item);
  }

  interface Model {
    void getCategoryItemList(String categoryName,
                             List<DocumentReference> itemsRef,
                             RepositoryContract.LoadCategoryItemListCallback callback);
  }

  interface Router {
    void navigateToDetailScreen();

    void passDataToDetailScreen(DetailState state);

    CategoryState getDataFromCatalogScreen();
  }
}
