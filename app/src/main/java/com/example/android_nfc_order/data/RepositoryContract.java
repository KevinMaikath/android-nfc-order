package com.example.android_nfc_order.data;

import com.google.firebase.firestore.DocumentReference;

import java.util.List;

public interface RepositoryContract {

  interface LoadCatalogItemsCallback {
    void setCatalogItems(final List<Category> categoryList);
  }

  interface LoadCategoryItemListCallback {
    void setCategoryItemList(final List<Product> productList);
  }

  /**
   * Set the list of Category inside a given callback.
   * If the list is empty, get the data from Firebase and then return it
   *
   * @param callback: called when the task is completed.
   */
  void loadCatalogItems(final LoadCatalogItemsCallback callback);

  /**
   * Set the list of Product inside a given callback.
   * If the list is empty, get the data from Firebase and then return it
   * If the requested category is the same as the last one we loaded from Firebase,
   * just return the current list
   *
   * @param categoryToLoad: category document reference where the data will be loaded from.
   * @param itemsRef:       list of documents where the data will be loaded from.
   * @param callback:       called when the task is completed.
   */
  void loadCategoryItemList(String categoryToLoad,
                            List<DocumentReference> itemsRef,
                            final LoadCategoryItemListCallback callback);
}
