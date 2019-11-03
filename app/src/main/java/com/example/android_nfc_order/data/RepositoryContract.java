package com.example.android_nfc_order.data;

import java.util.List;

public interface RepositoryContract {

    interface LoadCatalogItemsCallback {
        void setCatalogItems(List<CatalogItem> catalogItemList);
    }

    void loadCatalogItems(LoadCatalogItemsCallback callback);
}
