package com.example.android_nfc_order.data;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCartRepository implements ShoppingCartRepositoryContract {

  private static ShoppingCartRepository instance = null;
  private Context context;

  private FirebaseFirestore firestore;

  private List<ShopItem> cartList;
  private float totalPrice;

  private ShoppingCartRepository(Context context) {
    this.context = context;
    this.cartList = new ArrayList<>();
    this.totalPrice = 0;
    this.firestore = FirebaseFirestore.getInstance();
  }

  public static ShoppingCartRepository getInstance(Context context) {
    if (instance == null) {
      instance = new ShoppingCartRepository(context);
    }
    return instance;
  }

  @Override
  public void loadShopItems(LoadShopItemListCallback callback) {
    callback.setShopItems(this.cartList, this.totalPrice);
  }

  @Override
  public void addOneToItemCount(ShopItem item) {
    int index = -1;
    for (ShopItem cart_item : cartList) {
      if (cart_item.getName().equals(item.getName())) {
        index = cartList.indexOf(cart_item);
        cart_item.addOne();
        cartList.set(index, cart_item);
        break;
      }
    }

    if (index < 0) {
      ShopItem cart_item = new ShopItem(item.getName(), 1, item.getPrice());
      cartList.add(cart_item);
    }

    totalPrice += item.getPrice();
  }

  @Override
  public void removeOneFromItemCount(ShopItem item) {
    for (ShopItem cart_item : cartList) {
      if (cart_item.getName().equals(item.getName())) {
        if (cart_item.getQuantity() <= 1) {
          cartList.remove(cart_item);
          break;
        } else {
          int index = cartList.indexOf(cart_item);
          cart_item.removeOne();
          cartList.set(index, cart_item);
          break;
        }
      }
    }

    totalPrice -= item.getPrice();
  }

  @Override
  public void submitOrder(final SubmitOrderCallback callback, String documentReference) {
    if (callback != null) {
      ArrayList<OrderElement> elements = new ArrayList<>();
      for (ShopItem item : cartList) {
        OrderElement elm = new OrderElement(item.getName(), item.getQuantity());
        elements.add(elm);
      }

      Map<String, Object> docData = new HashMap<>();
      docData.put("elements", elements);
      docData.put("totalPrice", totalPrice);

      firestore.collection("orders")
          .document(documentReference)
          .set(docData)
          .addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
              callback.orderSubmitted(true);
            }
          })
          .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
              callback.orderSubmitted(false);
            }
          });
    }
  }


  /**
   * Data class for Firestore.
   */
  private class OrderElement {

    String name;
    int quantity;

    OrderElement(String name, int quantity) {
      this.name = name;
      this.quantity = quantity;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public int getQuantity() {
      return quantity;
    }

    public void setQuantity(int quantity) {
      this.quantity = quantity;
    }
  }

}
