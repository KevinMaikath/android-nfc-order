package com.example.android_nfc_order.catalogScreen;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android_nfc_order.R;

public class CatalogActivity
        extends AppCompatActivity implements CatalogContract.View {

    public static String TAG = CatalogActivity.class.getSimpleName();

    private CatalogContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        // do the setup
        CatalogScreen.configure(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // do some work
        presenter.fetchData();
    }

    @Override
    public void injectPresenter(CatalogContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void displayData(CatalogViewModel viewModel) {
        //Log.e(TAG, "displayData()");

        // deal with the data
        ((TextView) findViewById(R.id.data)).setText(viewModel.data);
    }
}
