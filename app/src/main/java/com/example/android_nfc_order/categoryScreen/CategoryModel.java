package com.example.android_nfc_order.categoryScreen;

import android.util.Log;

import com.example.android_nfc_order.data.Repository;
import com.example.android_nfc_order.data.RepositoryContract;

import java.lang.ref.WeakReference;

public class CategoryModel implements CategoryContract.Model {

  public static String TAG = CategoryModel.class.getSimpleName();

  private Repository repository;

  public CategoryModel(Repository repository) {
    this.repository = repository;
  }

  @Override
  public void getCategoryItemList(String collectionRef,
                                  RepositoryContract.LoadCategoryItemListCallback callback) {
    repository.loadCategoryItemList(collectionRef, callback);
  }
}
