package com.example.android_nfc_order.catalogScreen;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.android_nfc_order.R;
import com.example.android_nfc_order.data.CatalogItem;


public class CatalogActivity
    extends AppCompatActivity implements CatalogContract.View {

  public static String TAG = CatalogActivity.class.getSimpleName();

  private CatalogContract.Presenter presenter;

  private RecyclerView recyclerView;
  private CatalogListAdapter listAdapter;

  private Toolbar toolbar;
  private ImageButton backButton;
  private TextView toolbar_title;
  private ImageButton cartButton;


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
    }, Glide.with(this));

    recyclerView = findViewById(R.id.catalog_recycler);
    recyclerView.setAdapter(listAdapter);

    toolbar = findViewById(R.id.toolbar);

    toolbar_title = findViewById(R.id.toolbar_title);
    toolbar_title.setText(R.string.catalog_title);

    backButton = findViewById(R.id.toolbar_backButton);
    backButton.setVisibility(View.INVISIBLE);

    cartButton = findViewById(R.id.toolbar_cartButton);
    cartButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        // TODO cartButtonClick
      }
    });

    setSupportActionBar(toolbar);

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
