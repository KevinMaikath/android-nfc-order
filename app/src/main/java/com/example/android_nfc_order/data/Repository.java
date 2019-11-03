package com.example.android_nfc_order.data;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class Repository implements RepositoryContract {

    private static Repository instance = null;
    private Context context;

    private List<CatalogItem> catalogItemList;

    public static Repository getInstance(Context context) {
        if (instance == null) {
            instance = new Repository(context);
        }
        return instance;
    }

    private Repository(Context context) {
        this.context = context;
        this.catalogItemList = new ArrayList<>();
    }

    @Override
    public void loadCatalogItems(LoadCatalogItemsCallback callback) {
        // TODO loadCatalogItems
        if (callback != null) {
            callback.setCatalogItems(catalogItemList);
        }
    }
}
