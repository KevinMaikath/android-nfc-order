package com.example.android_nfc_order.catalogScreen;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_nfc_order.R;
import com.example.android_nfc_order.data.CatalogItem;

public class CatalogActivity
        extends AppCompatActivity implements CatalogContract.View {

    public static String TAG = CatalogActivity.class.getSimpleName();

    private CatalogContract.Presenter presenter;

    private RecyclerView recyclerView;
    private CatalogListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        listAdapter = new CatalogListAdapter(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CatalogItem item = (CatalogItem) view.getTag();
                presenter.onCatalogItemClicked(item);
            }
        });

        recyclerView = findViewById(R.id.catalog_recycler);
        recyclerView.setAdapter(listAdapter);

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
    public void displayData(final CatalogViewModel viewModel) {
        //Log.e(TAG, "displayData()");

        // deal with the data
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                listAdapter.setItems(viewModel.catalogItemList);
            }
        });
    }
}
