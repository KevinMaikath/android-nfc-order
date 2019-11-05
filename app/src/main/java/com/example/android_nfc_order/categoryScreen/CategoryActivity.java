package com.example.android_nfc_order.categoryScreen;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.android_nfc_order.R;
import com.example.android_nfc_order.data.CategoryItem;

public class CategoryActivity
    extends AppCompatActivity implements CategoryContract.View {

  public static String TAG = CategoryActivity.class.getSimpleName();

  private CategoryContract.Presenter presenter;

  private RecyclerView recyclerView;
  private CategoryListAdapter listAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_category);

    listAdapter = new CategoryListAdapter(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        CategoryItem item = (CategoryItem) view.getTag();
        presenter.onCategoryItemClicked(item);
//        Toast.makeText(getApplicationContext(), "Item Clicked", Toast.LENGTH_LONG).show();
      }
    },
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
//            Toast.makeText(getApplicationContext(), "Button Clicked", Toast.LENGTH_SHORT).show();
          }
        },
        Glide.with(this));

    recyclerView = findViewById(R.id.category_recycler);
    recyclerView.setAdapter(listAdapter);

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
    Log.e(TAG, "displayData()");

    // deal with the data
    runOnUiThread(new Runnable() {
      @Override
      public void run() {
        listAdapter.setItems(viewModel.itemList);
      }
    });
  }

  @Override
  public void onBackPressed() {
    this.finish();
  }
}
