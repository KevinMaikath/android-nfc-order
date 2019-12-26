package com.example.android_nfc_order.detailScreen;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.example.android_nfc_order.R;

public class DetailActivity
    extends AppCompatActivity implements DetailContract.View {

  private Toolbar toolbar;
  private ImageButton backButton;
  private TextView toolbar_title, product_price;
  private ImageButton cartButton;
  private ImageView productImage;
  private ListView ingredientsList;

  private ArrayAdapter<String> ingredientsAdapter;

  private RequestManager glide;

  public static String TAG = DetailActivity.class.getSimpleName();

  private DetailContract.Presenter presenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_detail);

    glide = Glide.with(this);

    productImage = findViewById(R.id.product_image);
    product_price = findViewById(R.id.product_price_label);
    ingredientsList = findViewById(R.id.ingredients_list);

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
        // TODO cartButtonClick
      }
    });

    setSupportActionBar(toolbar);

    ingredientsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
    ingredientsList.setAdapter(ingredientsAdapter);

    // do the setup
    DetailScreen.configure(this);
  }

  @Override
  protected void onResume() {
    super.onResume();

    // do some work
    presenter.fetchData();
  }

  @Override
  public void injectPresenter(DetailContract.Presenter presenter) {
    this.presenter = presenter;
  }

  @Override
  public void displayData(final DetailViewModel viewModel) {
    //Log.e(TAG, "displayData()");

    // deal with the data
    toolbar_title.setText(viewModel.currentProduct.getName());
    product_price.setText(String.valueOf(viewModel.currentProduct.getPrice()));

    String imgUrl = viewModel.currentProduct.getImgUrl();
    RequestOptions options = new RequestOptions().fitCenter();
    glide.load(imgUrl).apply(options).into(productImage);

    runOnUiThread(new Runnable() {
      @Override
      public void run() {
        ingredientsAdapter.clear();
        ingredientsAdapter.addAll(viewModel.currentProduct.getIngredients());
      }
    });
  }
}