package com.example.android_nfc_order.catalogScreen;

import com.example.android_nfc_order.categoryScreen.CategoryState;
import com.example.android_nfc_order.data.Category;
import com.example.android_nfc_order.data.RepositoryContract;

import java.lang.ref.WeakReference;

interface CatalogContract {

    interface View {
        void injectPresenter(Presenter presenter);

        void displayData(CatalogViewModel viewModel);
    }

    interface Presenter {
        void injectView(WeakReference<View> view);

        void injectModel(Model model);

        void injectRouter(Router router);

        void fetchData();

        void onCatalogItemClicked(Category item);
    }

    interface Model {
        void getCatalogItems(RepositoryContract.LoadCatalogItemsCallback callback);
    }

    interface Router {
        void navigateToCategoryScreen();

        void passDataToCategoryScreen(CategoryState state);

//        CatalogState getDataFromPreviousScreen();
    }
}
