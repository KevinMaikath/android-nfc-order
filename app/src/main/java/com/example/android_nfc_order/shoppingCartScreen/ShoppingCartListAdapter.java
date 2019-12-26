package com.example.android_nfc_order.shoppingCartScreen;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.android_nfc_order.R;
import com.example.android_nfc_order.data.ShopItem;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartListAdapter extends RecyclerView.Adapter<ShoppingCartListAdapter.ViewHolder> {

  private List<ShopItem> shopItemList;
  private final AddButtonClickedCallback addButtonCallback;
  private final RemoveButtonClickedCallback removeButtonCallback;

  public ShoppingCartListAdapter(AddButtonClickedCallback addButtonCallback, RemoveButtonClickedCallback removeButtonCallback) {
    this.shopItemList = new ArrayList<>();
    this.addButtonCallback = addButtonCallback;
    this.removeButtonCallback = removeButtonCallback;
  }

  public void setItems(List<ShopItem> items) {
    shopItemList = items;
    notifyDataSetChanged();
  }


  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.shoppingcart_recycler_item, parent, false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(final ViewHolder holder, final int position) {
    holder.itemView.setTag(shopItemList.get(position));

    String name = shopItemList.get(position).getName();
    holder.shopItemName.setText(name);

    String price = "$" + shopItemList.get(position).getPrice();
    holder.shopItemPrice.setText(price);

    String quantity = String.valueOf(shopItemList.get(position).getQuantity());
    holder.shopItemQuantity.setText(quantity);

    holder.addButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        addButtonCallback.addItemClicked(shopItemList.get(position));
      }
    });

    holder.removeButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        removeButtonCallback.removeItemClicked(shopItemList.get(position));
      }
    });
  }

  @Override
  public int getItemCount() {
    return shopItemList.size();
  }

  class ViewHolder extends RecyclerView.ViewHolder {
    final TextView shopItemName, shopItemPrice, shopItemQuantity;
    final ImageButton addButton, removeButton;

    ViewHolder(View view) {
      super(view);
      shopItemName = view.findViewById(R.id.shop_item_name);
      shopItemPrice = view.findViewById(R.id.shop_item_price);
      shopItemQuantity = view.findViewById(R.id.shop_item_quantity);
      addButton = view.findViewById(R.id.shop_item_add_button);
      removeButton = view.findViewById(R.id.shop_item_remove_button);
    }
  }

}
