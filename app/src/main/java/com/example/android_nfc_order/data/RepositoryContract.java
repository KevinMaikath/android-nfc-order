package com.example.android_nfc_order.data;

import java.util.List;

public interface RepositoryContract {

    interface LoadCatalogItemsCallback {
        void setCatalogItems(final List<CatalogItem> catalogItemList);
    }

    interface LoadCategoryItemListCallback {
        void setCategoryItemList(final List<CategoryItem> categoryItemList);
    }

    void loadCatalogItems(final LoadCatalogItemsCallback callback);

    void loadCategoryItemList( String collectionRef, final LoadCategoryItemListCallback callback);
}
