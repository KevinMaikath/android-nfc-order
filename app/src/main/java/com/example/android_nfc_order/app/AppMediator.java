package com.example.android_nfc_order.app;

import android.app.Application;

import com.example.android_nfc_order.catalogScreen.CatalogState;
import com.example.android_nfc_order.categoryScreen.CategoryState;
import com.example.android_nfc_order.detailScreen.DetailState;
import com.example.android_nfc_order.shoppingCartScreen.ShoppingCartState;

public class AppMediator extends Application {

    private CatalogState catalogState;
    private CategoryState categoryState;
    private DetailState detailState;
    private ShoppingCartState shoppingCartState;

    public AppMediator() {
        this.catalogState = new CatalogState();
        this.categoryState = new CategoryState();
        this.detailState = new DetailState();
        this.shoppingCartState = new ShoppingCartState();
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

    public DetailState getDetailState() {
        return detailState;
    }

    public void setDetailState(DetailState detailState) {
        this.detailState = detailState;
    }

    public ShoppingCartState getShoppingCartState() {
        return shoppingCartState;
    }

    public void setShoppingCartState(ShoppingCartState shoppingCartState) {
        this.shoppingCartState = shoppingCartState;
    }
}
