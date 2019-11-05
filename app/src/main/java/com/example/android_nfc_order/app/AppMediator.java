package com.example.android_nfc_order.app;

import android.app.Application;

import com.example.android_nfc_order.catalogScreen.CatalogState;
import com.example.android_nfc_order.categoryScreen.CategoryState;

public class AppMediator extends Application {

    private CatalogState catalogState;
    private CategoryState categoryState;

    public AppMediator() {
        this.catalogState = new CatalogState();
        this.categoryState = new CategoryState();
    }

    public CatalogState getCatalogState() {
        return catalogState;
    }

    public void setCatalogState(CatalogState catalogState) {
        this.catalogState = catalogState;
    }

    public CategoryState getCategoryState() {
        return categoryState;
    }

    public void setCategoryState(CategoryState categoryState) {
        this.categoryState = categoryState;
    }
}
