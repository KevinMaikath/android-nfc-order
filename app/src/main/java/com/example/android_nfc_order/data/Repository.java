package com.example.android_nfc_order.data;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Repository implements RepositoryContract {

  private static Repository instance = null;
  private Context context;
  private FirebaseFirestore firestore;

  private List<Category> categoryList;

  private List<Product> productList;
  private String lastLoadedCategory;

  public static Repository getInstance(Context context) {
    if (instance == null) {
      instance = new Repository(context);
    }
    return instance;
  }

  private Repository(Context context) {
    this.context = context;
    this.categoryList = new ArrayList<>();
    this.productList = new ArrayList<>();
    this.lastLoadedCategory = "";
    this.firestore = FirebaseFirestore.getInstance();
  }

  /**
   * Set the list of Category inside a given callback.
   * If the list is empty, get the data from Firebase and then return it
   *
   * @param callback:
   */
  @Override
  public void loadCatalogItems(final LoadCatalogItemsCallback callback) {
    if (callback != null) {

      // Check if the list has already been loaded from Firebase
      if (categoryList.size() < 1) {

        // Get the data from Firebase and parse it into an ArrayList<Category>
        firestore.collection("categories")
            .get()
            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
              @Override
              public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                  for (QueryDocumentSnapshot document : task.getResult()) {
                    Category item = document.toObject(Category.class);
                    categoryList.add(item);
                  }
                  callback.setCatalogItems(categoryList);
                } else {
                  Log.e("REPOSITORY", "ERROR LOADING DATA");
                }
              }
            });

        // Return the empty list anyway, so that we avoid errors
        // The correct list will be set once we get it from Firebase (inside onCompleteListener)
        callback.setCatalogItems(categoryList);

      } else {
        // If the list has already been set from Firebase, just return it
        callback.setCatalogItems(categoryList);
      }
    } else {
      Log.e("REPOSITORY", "ERROR AT CALLBACK");
    }
  }


  /**
   * Set the list of Product inside a given callback.
   * If the list is empty, get the data from Firebase and then return it
   * If the requested category is the same as the last one we loaded from Firebase,
   * just return the current list
   *
   * @param callback:
   */
  @Override
  public void loadCategoryItemList(String categoryToLoad,
                                   List<DocumentReference> itemsRef,
                                   final LoadCategoryItemListCallback callback) {
    if (callback != null) {

      // Get the data from Firebase if the list is empty or if the last loaded collection
      // is different from the current one
      if (productList.size() < 1 || !lastLoadedCategory.equals(categoryToLoad)) {

        // reset current items before adding more
        lastLoadedCategory = categoryToLoad;
        productList.clear();
        for (DocumentReference docRef : itemsRef) {
          docRef.get()
              .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                  if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                      Product item = document.toObject(Product.class);
                      productList.add(item);
                      callback.setCategoryItemList(productList);
                    } else {
                      Log.e("REPOSITORY", "ERROR LOADING DATA");
                    }
                  }
                }
              });
        }

//        firestore.collection(collectionRef)
//            .get()
//            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//              @Override
//              public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                if (task.isSuccessful()) {
//                  for (QueryDocumentSnapshot document : task.getResult()) {
//                    Product item = document.toObject(Product.class);
//                    productList.add(item);
//                  }
//                  callback.setCategoryItemList(productList);
//                } else {
//                  Log.e("REPOSITORY", "ERROR LOADING DATA");
//                }
//              }
//            });
      } else {

        // Return the empty list anyway, so that we avoid errors
        // The correct list will be set once we get it from Firebase (inside onCompleteListener)
        callback.setCategoryItemList(productList);
      }
      } else {
        Log.e("REPOSITORY", "ERROR AT CALLBACK");
      }
    }
  }
