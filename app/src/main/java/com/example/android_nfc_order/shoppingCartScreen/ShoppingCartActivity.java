package com.example.android_nfc_order.shoppingCartScreen;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_nfc_order.R;
import com.example.android_nfc_order.data.ShopItem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

interface AddButtonClickedCallback {
  void addItemClicked(ShopItem item);
}

interface RemoveButtonClickedCallback {
  void removeItemClicked(ShopItem item);
}

public class ShoppingCartActivity
    extends AppCompatActivity implements ShoppingCartContract.View {

  public static String TAG = ShoppingCartActivity.class.getSimpleName();

  private ShoppingCartContract.Presenter presenter;

  private RecyclerView recyclerView;
  private ShoppingCartListAdapter listAdapter;

  private Toolbar toolbar;
  private FloatingActionButton doneButton;
  private ImageButton backButton;
  private TextView toolbar_title, total_price_label;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_shopping_cart);

    total_price_label = findViewById(R.id.shop_total_price);

    listAdapter = new ShoppingCartListAdapter(
        new AddButtonClickedCallback() {
          @Override
          public void addItemClicked(ShopItem item) {
            presenter.addItemClicked(item);
          }
        },
        new RemoveButtonClickedCallback() {
          @Override
          public void removeItemClicked(ShopItem item) {
            presenter.removeItemClicked(item);
          }
        });

    recyclerView = findViewById(R.id.shoppingcart_recycler);
    recyclerView.setAdapter(listAdapter);

    toolbar = findViewById(R.id.toolbar);

    toolbar_title = findViewById(R.id.toolbar_title);
    toolbar_title.setText(R.string.shopping_cart_title);

    backButton = findViewById(R.id.toolbar_backButton);
    backButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        onBackPressed();
      }
    });

    setSupportActionBar(toolbar);

    doneButton = findViewById(R.id.floatingDoneButton);
    doneButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        presenter.onDoneClicked();
      }
    });

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
  public void displayData(final ShoppingCartViewModel viewModel) {
    String totalPrice = "Total: $" + viewModel.totalPrice;
    total_price_label.setText(totalPrice);

    runOnUiThread(new Runnable() {
      @Override
      public void run() {
        listAdapter.setItems(viewModel.shopItemList);
      }
    });
  }

  @Override
  public void onBackPressed() {
    this.finish();
  }
}
