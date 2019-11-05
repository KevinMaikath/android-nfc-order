package com.example.android_nfc_order.catalogScreen;

import android.content.Intent;
import android.content.Context;

import com.example.android_nfc_order.app.AppMediator;
import com.example.android_nfc_order.categoryScreen.CategoryActivity;
import com.example.android_nfc_order.categoryScreen.CategoryState;

public class CatalogRouter implements CatalogContract.Router {

    public static String TAG = CatalogRouter.class.getSimpleName();

    private AppMediator mediator;

    public CatalogRouter(AppMediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void navigateToCategoryScreen() {
        Context context = mediator.getApplicationContext();
        Intent intent = new Intent(context, CategoryActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    public void passDataToCategoryScreen(CategoryState state) {
        mediator.setCategoryState(state);
    }

//    @Override
//    public CatalogState getDataFromPreviousScreen() {
//        CatalogState state = mediator.getCatalogState();
//        return state;
//    }
}
