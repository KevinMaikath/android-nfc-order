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

    void loadCatalogItems(final LoadCatalogItemsCallback callback);

    void loadCategoryItemList(String categoryToLoad,
                              List<DocumentReference> itemsRef,
                              final LoadCategoryItemListCallback callback);
}
