package com.example.android_nfc_order.app;

import android.app.Application;

import com.example.android_nfc_order.catalogScreen.CatalogState;

public class AppMediator extends Application {

    private CatalogState catalogState;

    public CatalogState getCatalogState() {
        return catalogState;
    }

    public void setCatalogState(CatalogState catalogState) {
        this.catalogState = catalogState;
    }
}
