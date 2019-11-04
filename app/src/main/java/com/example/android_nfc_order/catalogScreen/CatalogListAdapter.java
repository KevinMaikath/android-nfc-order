package com.example.android_nfc_order.catalogScreen;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.example.android_nfc_order.R;
import com.example.android_nfc_order.data.CatalogItem;


import java.util.ArrayList;
import java.util.List;

public class CatalogListAdapter extends RecyclerView.Adapter<CatalogListAdapter.ViewHolder> {

  private List<CatalogItem> catalogItems;
  private final View.OnClickListener clickListener;

  private RequestManager glide;

  public CatalogListAdapter(View.OnClickListener clickListener, RequestManager glide) {
    this.catalogItems = new ArrayList<>();
    this.clickListener = clickListener;
    this.glide = glide;
  }

  public void addItem(CatalogItem item) {
    catalogItems.add(item);
    notifyDataSetChanged();
  }

  public void addItems(List<CatalogItem> items) {
    catalogItems.addAll(items);
    notifyDataSetChanged();
  }

  public void setItems(List<CatalogItem> items) {
    catalogItems = items;
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
    holder.itemView.setTag(catalogItems.get(position));
    holder.itemView.setOnClickListener(clickListener);

    String name = catalogItems.get(position).getName();
    holder.itemName.setText(name);

    String imgUrl = catalogItems.get(position).getImgUrl();
    RequestOptions options = new RequestOptions().fitCenter();
    glide.load(imgUrl).apply(options).into(holder.itemIcon);
  }


  @Override
  public int getItemCount() {
    return catalogItems.size();
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
