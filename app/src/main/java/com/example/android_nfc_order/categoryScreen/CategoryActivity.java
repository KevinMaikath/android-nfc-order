package com.example.android_nfc_order.categoryScreen;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.android_nfc_order.R;
import com.example.android_nfc_order.data.Product;

interface ButtonClickedCallback {
  void productClicked(Product item);
}

public class CategoryActivity
    extends AppCompatActivity implements CategoryContract.View {

  public static String TAG = CategoryActivity.class.getSimpleName();

  private CategoryContract.Presenter presenter;

  private RecyclerView recyclerView;
  private CategoryListAdapter listAdapter;

  private Toolbar toolbar;
  private ImageButton backButton;
  private TextView toolbar_title;
  private ImageButton cartButton;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_category);

    listAdapter = new CategoryListAdapter(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Product item = (Product) view.getTag();
        presenter.onCategoryItemClicked(item);
      }
    },
        new ButtonClickedCallback() {
          @Override
          public void productClicked(Product item) {
            presenter.onProductAddClicked(item);
          }
        },
        Glide.with(this));

    recyclerView = findViewById(R.id.category_recycler);
    recyclerView.setAdapter(listAdapter);

    toolbar = findViewById(R.id.toolbar);

    toolbar_title = findViewById(R.id.toolbar_title);

    backButton = findViewById(R.id.toolbar_backButton);
    backButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        onBackPressed();
      }
    });

    cartButton = findViewById(R.id.toolbar_cartButton);
    cartButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        presenter.onCartButtonClicked();
      }
    });

    setSupportActionBar(toolbar);


    // do the setup
    CategoryScreen.configure(this);
  }

  @Override
  protected void onResume() {
    super.onResume();

    // do some work
    presenter.fetchData();
  }

  @Override
  public void injectPresenter(CategoryContract.Presenter presenter) {
    this.presenter = presenter;
  }

  @Override
  public void displayData(final CategoryViewModel viewModel) {
//    Log.e(TAG, "displayData()");

    toolbar_title.setText(viewModel.currentCategory.getName());

    // deal with the data
    runOnUiThread(new Runnable() {
      @Override
      public void run() {
        listAdapter.setItems(viewModel.productList);
      }
    });
  }

  @Override
  public void onBackPressed() {
    this.finish();
  }

  @Override
  public void presentToast(String text) {
    Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
  }
}
