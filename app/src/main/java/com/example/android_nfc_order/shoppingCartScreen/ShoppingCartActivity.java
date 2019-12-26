package com.example.android_nfc_order.shoppingCartScreen;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android_nfc_order.R;


public class ShoppingCartActivity
    extends AppCompatActivity implements ShoppingCartContract.View {

  public static String TAG = ShoppingCartActivity.class.getSimpleName();

  private ShoppingCartContract.Presenter presenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_shopping_cart);

    // do the setup
    ShoppingCartScreen.configure(this);
  }

  @Override
  protected void onResume() {
    super.onResume();

    // do some work
    presenter.fetchData();
  }

  @Override
  public void injectPresenter(ShoppingCartContract.Presenter presenter) {
    this.presenter = presenter;
  }

  @Override
  public void displayData(ShoppingCartViewModel viewModel) {
    //Log.e(TAG, "displayData()");

    // deal with the data
    ((TextView) findViewById(R.id.data)).setText(viewModel.data);
  }
}
