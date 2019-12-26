package com.example.android_nfc_order.categoryScreen;

import android.util.Log;

import com.example.android_nfc_order.data.Product;
import com.example.android_nfc_order.data.Repository;
import com.example.android_nfc_order.data.RepositoryContract;
import com.example.android_nfc_order.data.ShoppingCart;
import com.google.firebase.firestore.DocumentReference;

import java.lang.ref.WeakReference;
import java.util.List;

public class CategoryModel implements CategoryContract.Model {

  public static String TAG = CategoryModel.class.getSimpleName();

  private Repository repository;
  private ShoppingCart shoppingCart;

  public CategoryModel(Repository repository, ShoppingCart shoppingCart) {
    this.repository = repository;
    this.shoppingCart = shoppingCart;
  }

  @Override
  public void getCategoryItemList(String categoryName,
                                  List<DocumentReference> itemsRef,
                                  RepositoryContract.LoadCategoryItemListCallback callback) {
    repository.loadCategoryItemList(categoryName, itemsRef, callback);
  }

  @Override
  public void addProductToShoppingCart(Product product) {
    shoppingCart.addOneToItemCount(product);
  }


}
