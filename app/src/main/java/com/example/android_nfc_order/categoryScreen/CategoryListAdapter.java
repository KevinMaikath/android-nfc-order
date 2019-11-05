package com.example.android_nfc_order.categoryScreen;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.example.android_nfc_order.R;
import com.example.android_nfc_order.data.CategoryItem;

import java.util.ArrayList;
import java.util.List;

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.ViewHolder> {

  private List<CategoryItem> categoryItemList;
  private final View.OnClickListener clickListener;
  private final View.OnClickListener buttonListener;

  private RequestManager glide;

  public CategoryListAdapter(View.OnClickListener clickListener,
                             View.OnClickListener buttonListener,
                             RequestManager glide){
    this.categoryItemList = new ArrayList<>();
    this.clickListener = clickListener;
    this.buttonListener = buttonListener;
    this.glide = glide;
  }

  public void setItems(List<CategoryItem> items) {
    categoryItemList = items;
    notifyDataSetChanged();
  }


  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.category_recycler_item, parent, false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {
    holder.itemView.setTag(categoryItemList.get(position));
    holder.itemView.setOnClickListener(clickListener);

    String name = categoryItemList.get(position).getName();
    holder.itemName.setText(name);

    holder.button.setOnClickListener(buttonListener);

    String imgUrl = categoryItemList.get(position).getImgUrl();
    RequestOptions options = new RequestOptions().fitCenter();
    glide.load(imgUrl).apply(options).into(holder.itemIcon);
  }

  @Override
  public int getItemCount() {
    return categoryItemList.size();
  }

  class ViewHolder extends RecyclerView.ViewHolder {
    final TextView itemName;
    final ImageView itemIcon;
    final ImageButton button;

    ViewHolder(View view) {
      super(view);
      itemName = view.findViewById(R.id.item_name);
      itemIcon = view.findViewById(R.id.item_icon);
      button = view.findViewById(R.id.item_add_button);
    }
  }

}
