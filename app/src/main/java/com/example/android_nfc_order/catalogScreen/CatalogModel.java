package com.example.android_nfc_order.catalogScreen;

import com.example.android_nfc_order.data.Repository;
import com.example.android_nfc_order.data.RepositoryContract;


public class CatalogModel implements CatalogContract.Model {

    public static String TAG = CatalogModel.class.getSimpleName();

    private Repository repository;

    public CatalogModel(Repository repository) {
        this.repository =  repository;
    }

    @Override
    public String fetchData() {
        // Log.e(TAG, "fetchData()");
        return "Hello";
    }

    @Override
    public void getCatalogItems(final RepositoryContract.LoadCatalogItemsCallback callback) {
        repository.loadCatalogItems(callback);
    }
}
