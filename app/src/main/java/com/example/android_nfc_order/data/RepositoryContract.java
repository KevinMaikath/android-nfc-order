package com.example.android_nfc_order.data;

import java.util.List;

public interface RepositoryContract {

    interface LoadCatalogItemsCallback {
        void setCatalogItems(final List<CatalogItem> catalogItemList);
    }

    void loadCatalogItems(final LoadCatalogItemsCallback callback);
}
