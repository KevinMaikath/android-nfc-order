package com.example.android_nfc_order.catalogScreen;

import android.util.Log;

import java.lang.ref.WeakReference;


public class CatalogModel implements CatalogContract.Model {

    public static String TAG = CatalogModel.class.getSimpleName();

    public CatalogModel() {

    }

    @Override
    public String fetchData() {
        // Log.e(TAG, "fetchData()");
        return "Hello";
    }
}
