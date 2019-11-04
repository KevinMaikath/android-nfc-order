package com.example.android_nfc_order.data;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Repository implements RepositoryContract {

  private static Repository instance = null;
  private Context context;
  private FirebaseFirestore firestore;

  private List<CatalogItem> catalogItemList;

  public static Repository getInstance(Context context) {
    if (instance == null) {
      instance = new Repository(context);
    }
    return instance;
  }

  private Repository(Context context) {
    this.context = context;
    this.catalogItemList = new ArrayList<>();
    this.firestore = FirebaseFirestore.getInstance();
  }

  /**
   * Set the list of CatalogItem inside a given callback.
   * If the list is empty, get the data from Firebase and then return it
   * @param callback:
   */
  @Override
  public void loadCatalogItems(final LoadCatalogItemsCallback callback) {
    if (callback != null) {

      // Check if the list has already been loaded from Firebase
      if (catalogItemList.size() < 1) {

        // Get the data from Firebase and parse it into an ArrayList<CatalogItem>
        firestore.collection("categories")
            .get()
            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
              @Override
              public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                  for (QueryDocumentSnapshot document : task.getResult()) {
                    CatalogItem item = document.toObject(CatalogItem.class);
                    catalogItemList.add(item);
                  }
                  callback.setCatalogItems(catalogItemList);
                } else {
                  Log.e("REPOSITORY", "ERROR LOADING DATA");
                }
              }
            });

        // Return the empty list anyway, so that we avoid errors
        // The correct list will be set once we get it from Firebase (inside onCompleteListener)
        callback.setCatalogItems(catalogItemList);

      } else {
        // If the list has already been set from Firebase, just return it
        callback.setCatalogItems(catalogItemList);
      }
    } else {
      Log.e("REPOSITORY", "ERROR AT CALLBACK");
    }
  }

}