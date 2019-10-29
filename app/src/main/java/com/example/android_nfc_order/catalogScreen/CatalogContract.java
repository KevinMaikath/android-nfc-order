package com.example.android_nfc_order.catalogScreen;

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
    }

    interface Model {
        String fetchData();
    }

    interface Router {
        void navigateToNextScreen();

        void passDataToNextScreen(CatalogState state);

        CatalogState getDataFromPreviousScreen();
    }
}
