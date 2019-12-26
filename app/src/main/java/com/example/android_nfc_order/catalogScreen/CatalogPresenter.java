package com.example.android_nfc_order.catalogScreen;

import com.example.android_nfc_order.categoryScreen.CategoryState;
import com.example.android_nfc_order.data.Category;
import com.example.android_nfc_order.data.RepositoryContract;

import java.lang.ref.WeakReference;
import java.util.List;

public class CatalogPresenter implements CatalogContract.Presenter {

  public static String TAG = CatalogPresenter.class.getSimpleName();

  private WeakReference<CatalogContract.View> view;
  private CatalogViewModel viewModel;
  private CatalogContract.Model model;
  private CatalogContract.Router router;

  public CatalogPresenter(CatalogState state) {
    viewModel = state;
  }

  @Override
  public void injectView(WeakReference<CatalogContract.View> view) {
    this.view = view;
  }

  @Override
  public void injectModel(CatalogContract.Model model) {
    this.model = model;
  }

  @Override
  public void injectRouter(CatalogContract.Router router) {
    this.router = router;
  }

  @Override
  public void fetchData() {

    if (viewModel.categoryList == null) {
      model.getCatalogItems(new RepositoryContract.LoadCatalogItemsCallback() {
        @Override
        public void setCatalogItems(List<Category> categoryList) {
          viewModel.categoryList = categoryList;
          view.get().displayData(viewModel);
        }
      });
    } else {
      view.get().displayData(viewModel);
    }


  }

  @Override
  public void onCatalogItemClicked(Category item) {
    CategoryState state = new CategoryState();
    state.currentCategory = item;
    router.passDataToCategoryScreen(state);
    router.navigateToCategoryScreen();
  }

  @Override
  public void onCartButtonClicked() {
    router.navigateToShoppingCartScreen();
  }
}
