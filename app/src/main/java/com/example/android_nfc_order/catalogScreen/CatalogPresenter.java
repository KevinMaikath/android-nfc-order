package com.example.android_nfc_order.catalogScreen;

import android.util.Log;

import com.example.android_nfc_order.data.CatalogItem;
import com.example.android_nfc_order.data.RepositoryContract;

import java.lang.ref.WeakReference;
import java.util.List;

public class CatalogPresenter implements CatalogContract.Presenter {

    public static String TAG = CatalogPresenter.class.getSimpleName();

    private WeakReference<CatalogContract.View> view;
    private CatalogViewModel viewModel;
    private CatalogContract.Model model;
    private CatalogContract.Router router;

    public CatalogPresenter(CatalogState state) {
        viewModel = state;
    }

    @Override
    public void injectView(WeakReference<CatalogContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(CatalogContract.Model model) {
        this.model = model;
    }

    @Override
    public void injectRouter(CatalogContract.Router router) {
        this.router = router;
    }

    @Override
    public void fetchData() {
        // Log.e(TAG, "fetchData()");

        // set passed state
//        CatalogState state = router.getDataFromPreviousScreen();
//        if (state != null) {
//            viewModel.data = state.data;
//        }
//
//        if (viewModel.data == null) {
//            // call the model
//            String data = model.fetchData();
//
//            // set initial state
//            viewModel.data = data;
//        }

        if (viewModel.catalogItemList == null) {
            model.getCatalogItems(new RepositoryContract.LoadCatalogItemsCallback() {
                @Override
                public void setCatalogItems(List<CatalogItem> catalogItemList) {
                    viewModel.catalogItemList = catalogItemList;
                }
            });
        }

        // update the view
        view.get().displayData(viewModel);

    }

    @Override
    public void onCatalogItemClicked(CatalogItem item) {
        // TODO onCatalogItemClicked
    }
}
