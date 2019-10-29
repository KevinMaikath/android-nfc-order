package com.example.android_nfc_order.catalogScreen;

import androidx.fragment.app.FragmentActivity;

import com.example.android_nfc_order.app.AppMediator;

import java.lang.ref.WeakReference;

public class CatalogScreen {

    public static void configure(CatalogContract.View view) {

        WeakReference<FragmentActivity> context =
                new WeakReference<>((FragmentActivity) view);

        AppMediator mediator = (AppMediator) context.get().getApplication();
        CatalogState state = mediator.getCatalogState();

        CatalogContract.Router router = new CatalogRouter(mediator);
        CatalogContract.Presenter presenter = new CatalogPresenter(state);
        CatalogContract.Model model = new CatalogModel();
        presenter.injectModel(model);
        presenter.injectRouter(router);
        presenter.injectView(new WeakReference<>(view));

        view.injectPresenter(presenter);

    }
}
