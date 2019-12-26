package com.example.android_nfc_order.categoryScreen;

import android.util.Log;

import com.example.android_nfc_order.data.Product;
import com.example.android_nfc_order.data.RepositoryContract;
import com.example.android_nfc_order.detailScreen.DetailState;

import java.lang.ref.WeakReference;
import java.util.List;

public class CategoryPresenter implements CategoryContract.Presenter {

  public static String TAG = CategoryPresenter.class.getSimpleName();

  private WeakReference<CategoryContract.View> view;
  private CategoryViewModel viewModel;
  private CategoryContract.Model model;
  private CategoryContract.Router router;

  public CategoryPresenter(CategoryState state) {
    viewModel = state;
  }

  @Override
  public void injectView(WeakReference<CategoryContract.View> view) {
    this.view = view;
  }

  @Override
  public void injectModel(CategoryContract.Model model) {
    this.model = model;
  }

  @Override
  public void injectRouter(CategoryContract.Router router) {
    this.router = router;
  }

  @Override
  public void fetchData() {
    // Log.e(TAG, "fetchData()");

    // set passed state
    CategoryState state = router.getDataFromCatalogScreen();
    if (state != null) {
      viewModel.currentCategory = state.currentCategory;
    }

    Log.e(TAG, "category: " + viewModel.currentCategory.getName());
    if (viewModel.productList == null) {
      model.getCategoryItemList(viewModel.currentCategory.getName(),
          viewModel.currentCategory.getItems(),
          new RepositoryContract.LoadCategoryItemListCallback() {
            @Override
            public void setCategoryItemList(List<Product> productList) {
              Log.e(TAG, "Retrieving data from firebase");
              viewModel.productList = productList;
              view.get().displayData(viewModel);
            }
          });
    } else {
      view.get().displayData(viewModel);
    }

  }

  @Override
  public void onCategoryItemClicked(Product item) {
    DetailState state = new DetailState();
    state.currentProduct = item;
    router.passDataToDetailScreen(state);
    router.navigateToDetailScreen();
  }

  @Override
  public void onProductAddClicked(Product item) {
    model.addProductToShoppingCart(item);
  }

  @Override
  public void onCartButtonClicked() {
    router.navigateToShoppingCartScreen();
  }
}
