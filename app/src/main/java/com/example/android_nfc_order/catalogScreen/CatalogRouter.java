package com.example.android_nfc_order.catalogScreen;

import android.util.Log;
import android.content.Intent;
import android.content.Context;

import com.example.android_nfc_order.app.AppMediator;

public class CatalogRouter implements CatalogContract.Router {

    public static String TAG = CatalogRouter.class.getSimpleName();

    private AppMediator mediator;

    public CatalogRouter(AppMediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void navigateToNextScreen() {
        Context context = mediator.getApplicationContext();
        Intent intent = new Intent(context, CatalogActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void passDataToNextScreen(CatalogState state) {
        mediator.setCatalogState(state);
    }

    @Override
    public CatalogState getDataFromPreviousScreen() {
        CatalogState state = mediator.getCatalogState();
        return state;
    }
}
