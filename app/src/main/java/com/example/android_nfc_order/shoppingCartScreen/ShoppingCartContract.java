package com.example.android_nfc_order.shoppingCartScreen;

import com.example.android_nfc_order.data.ShopItem;
import com.example.android_nfc_order.data.ShoppingCartRepositoryContract;

import java.lang.ref.WeakReference;

interface ShoppingCartContract {

  interface View {
    void injectPresenter(Presenter presenter);

    void displayData(ShoppingCartViewModel viewModel);

    /**
     * Set-up the device's NFC to read a tag.
     */
    void setUpNFC();

    /**
     * Present an alert telling the user whether the operation was successful or no.t
     *
     * @param success:         true if the operation was successful.
     * @param restaurant_name: name of the restaurant where the order has been submited.
     */
    void successfulSubmitAlert(boolean success, String restaurant_name);

    /**
     * Present a toast with an specified message.
     *
     * @param text: message to show.
     */
    void presentToast(String text);
  }

  interface Presenter {
    void injectView(WeakReference<View> view);

    void injectModel(Model model);

    void injectRouter(Router router);

    void fetchData();

    /**
     * Tell the model to increase by one an specified product.
     *
     * @param item: clicked item.
     */
    void addItemClicked(ShopItem item);

    /**
     * Tell the model to decrease by one an specified product.
     *
     * @param item: clicked item.
     */
    void removeItemClicked(ShopItem item);

    /**
     * Set the view ready for NFC reading if the current order isn't empty.
     */
    void onDoneClicked();

    /**
     * Tell the model to submit the current order to the document reference given by NFC.
     * Present an alert in the view denoting if the operation has been successful.
     *
     * @param results: data read from NFC.
     */
    void onNFCRead(String[] results);
  }

  interface Model {
    /**
     * Tell the repository to load all the cartList.
     *
     * @param callback: returns the cartList when the task is completed.
     */
    void loadShopItemList(ShoppingCartRepositoryContract.LoadShopItemListCallback callback);

    /**
     * Tell the repository increase by one an specified item.
     *
     * @param item: clicked item.
     */
    void addOneToItemCount(ShopItem item);

    /**
     * Tell the repository decrease by one an specified item.
     *
     * @param item: clicked item.
     */
    void removeOneFromItemCount(ShopItem item);

    /**
     * Tell the repository to submit the current cartList to Firebase.
     *
     * @param callback: returns whether the operation has been successful or not.
     * @param docRef:   document where the data will be stored.
     */
    void submitOrder(ShoppingCartRepositoryContract.SubmitOrderCallback callback, String docRef);

    /**
     * Tell the repository to load the total price of the current order.
     *
     * @param callback: returns the total price when the task is completed.
     */
    void loadTotalPrice(ShoppingCartRepositoryContract.LoadTotalPriceCallback callback);
  }

  interface Router {

  }
}
