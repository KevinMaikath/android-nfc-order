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

  @Override
  public void loadCatalogItems(final LoadCatalogItemsCallback callback) {
    // TODO loadCatalogItems
    if (callback != null) {
      firestore.collection("categories")
          .get()
          .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
              if (task.isSuccessful()) {
                for (QueryDocumentSnapshot document : task.getResult()) {
                  Log.e("REPOSITORY", document.getId() + " => " + document.get("name"));
                  CatalogItem item = document.toObject(CatalogItem.class);
                  catalogItemList.add(item);
                }
                callback.setCatalogItems(catalogItemList);
              } else {
//                Log.e("REPOSITORY", "ERROR LOADING DATA");
              }
            }
          });
      callback.setCatalogItems(catalogItemList);
    }
  }

}
