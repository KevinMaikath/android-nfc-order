package com.example.android_nfc_order.categoryScreen;

import android.util.Log;

import com.example.android_nfc_order.data.CategoryItem;
import com.example.android_nfc_order.data.RepositoryContract;

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

//    if (viewModel.data == null) {
//      // call the model
//      String data = model.fetchData();
//
//      // set initial state
//      viewModel.data = data;
//    }

    if (viewModel.itemList == null) {
      model.getCategoryItemList(viewModel.currentCategory,
          new RepositoryContract.LoadCategoryItemListCallback() {
            @Override
            public void setCategoryItemList(List<CategoryItem> categoryItemList) {
              Log.e(TAG, "Reatrieving data from firebase");
              viewModel.itemList = categoryItemList;
              view.get().displayData(viewModel);
            }
          });
    } else {
      view.get().displayData(viewModel);
    }

    Log.e(TAG, "category: " + viewModel.currentCategory);

  }

  @Override
  public void onCategoryItemClicked(CategoryItem item) {
    // TODO onCategoryItemClicked
  }
}
