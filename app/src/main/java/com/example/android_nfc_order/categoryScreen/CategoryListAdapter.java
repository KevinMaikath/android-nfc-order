package com.example.android_nfc_order.categoryScreen;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.example.android_nfc_order.R;
import com.example.android_nfc_order.data.Product;

import java.util.ArrayList;
import java.util.List;

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.ViewHolder> {

  private List<Product> productList;
  private final View.OnClickListener clickListener;
  private final ButtonClickedCallback buttonCallback;

  private RequestManager glide;

  public CategoryListAdapter(View.OnClickListener clickListener,
                             ButtonClickedCallback callback,
                             RequestManager glide) {
    this.productList = new ArrayList<>();
    this.clickListener = clickListener;
    this.buttonCallback = callback;
    this.glide = glide;
  }

  public void setItems(List<Product> items) {
    productList = items;
    notifyDataSetChanged();
  }


  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.category_recycler_item, parent, false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, final int position) {
    holder.itemView.setTag(productList.get(position));
    holder.itemView.setOnClickListener(clickListener);

    String name = productList.get(position).getName();
    holder.itemName.setText(name);

    Float price = productList.get(position).getPrice();
    String priceStr = "$" + price;
    holder.itemPrice.setText(priceStr);

    holder.button.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        buttonCallback.productClicked(productList.get(position));
      }
    });

    String imgUrl = productList.get(position).getImgUrl();
    RequestOptions options = new RequestOptions().fitCenter();
    glide.load(imgUrl).apply(options).into(holder.itemIcon);
  }

  @Override
  public int getItemCount() {
    return productList.size();
  }

  class ViewHolder extends RecyclerView.ViewHolder {
    final TextView itemName;
    final TextView itemPrice;
    final ImageView itemIcon;
    final ImageButton button;

    ViewHolder(View view) {
      super(view);
      itemName = view.findViewById(R.id.item_name);
      itemPrice = view.findViewById(R.id.item_price);
      itemIcon = view.findViewById(R.id.item_icon);
      button = view.findViewById(R.id.item_add_button);
    }
  }

}
