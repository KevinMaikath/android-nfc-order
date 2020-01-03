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
     * Pass the clicked product and go to ProductDetailScreen.
     *
     * @param item: product clicked.
     */
    void onCategoryItemClicked(Product item);

    /**
     * Tell the model to add a product to the cartList.
     *
     * @param item: clicked product.
     */
    void onProductAddClicked(Product item);

    /**
     * Go to ShoppingCartScreen.
     */
    void onCartButtonClicked();
  }

  interface Model {
    /**
     * Tell the repository to load all the products from the current category.
     *
     * @param callback: returns the product list when the task is finished.
     */
    void getCategoryItemList(String categoryName,
                             List<DocumentReference> itemsRef,
                             RepositoryContract.LoadCategoryItemListCallback callback);

    /**
     * Tell the repository to add a product to the cartList.
     *
     * @param product: clicked product.
     */
    void addProductToShoppingCart(Product product);
  }

  interface Router {
    void navigateToDetailScreen();

    void passDataToDetailScreen(DetailState state);

    CategoryState getDataFromCatalogScreen();

    void navigateToShoppingCartScreen();
  }
}
