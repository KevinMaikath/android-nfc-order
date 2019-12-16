package com.example.android_nfc_order.catalogScreen;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.example.android_nfc_order.R;
import com.example.android_nfc_order.data.Category;


import java.util.ArrayList;
import java.util.List;

public class CatalogListAdapter extends RecyclerView.Adapter<CatalogListAdapter.ViewHolder> {

  private List<Category> categories;
  private final View.OnClickListener clickListener;

  private RequestManager glide;

  public CatalogListAdapter(View.OnClickListener clickListener, RequestManager glide) {
    this.categories = new ArrayList<>();
    this.clickListener = clickListener;
    this.glide = glide;
  }

  public void setItems(List<Category> items) {
    categories = items;
    notifyDataSetChanged();
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.catalog_recycler_item, parent, false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {
    holder.itemView.setTag(categories.get(position));
    holder.itemView.setOnClickListener(clickListener);

    String name = categories.get(position).getName();
    holder.itemName.setText(name);

    String imgUrl = categories.get(position).getImgUrl();
    RequestOptions options = new RequestOptions().fitCenter();
    glide.load(imgUrl).apply(options).into(holder.itemIcon);
  }


  @Override
  public int getItemCount() {
    return categories.size();
  }

  class ViewHolder extends RecyclerView.ViewHolder {
    final TextView itemName;
    final ImageView itemIcon;

    ViewHolder(View view) {
      super(view);
      itemName = view.findViewById(R.id.item_name);
      itemIcon = view.findViewById(R.id.item_icon);
    }
  }

}
