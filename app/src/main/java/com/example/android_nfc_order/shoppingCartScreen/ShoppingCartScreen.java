package com.example.android_nfc_order.shoppingCartScreen;

import androidx.fragment.app.FragmentActivity;

import com.example.android_nfc_order.app.AppMediator;

import java.lang.ref.WeakReference;

public class ShoppingCartScreen {

  public static void configure(ShoppingCartContract.View view) {

    WeakReference<FragmentActivity> context =
        new WeakReference<>((FragmentActivity) view);

    AppMediator mediator = (AppMediator) context.get().getApplication();
    ShoppingCartState state = mediator.getShoppingCartState();

    ShoppingCartContract.Router router = new ShoppingCartRouter(mediator);
    ShoppingCartContract.Presenter presenter = new ShoppingCartPresenter(state);
    ShoppingCartContract.Model model = new ShoppingCartModel();
    presenter.injectModel(model);
    presenter.injectRouter(router);
    presenter.injectView(new WeakReference<>(view));

    view.injectPresenter(presenter);

  }
}
