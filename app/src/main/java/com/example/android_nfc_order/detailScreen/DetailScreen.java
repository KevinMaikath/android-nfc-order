package com.example.android_nfc_order.detailScreen;

import androidx.fragment.app.FragmentActivity;

import com.example.android_nfc_order.app.AppMediator;
import com.example.android_nfc_order.data.ShoppingCartRepository;

import java.lang.ref.WeakReference;


public class DetailScreen {

  public static void configure(DetailContract.View view) {

    WeakReference<FragmentActivity> context =
        new WeakReference<>((FragmentActivity) view);

    AppMediator mediator = (AppMediator) context.get().getApplication();
    DetailState state = mediator.getDetailState();

    ShoppingCartRepository shoppingCart = ShoppingCartRepository.getInstance(context.get());

    DetailContract.Router router = new DetailRouter(mediator);
    DetailContract.Presenter presenter = new DetailPresenter(state);
    DetailContract.Model model = new DetailModel(shoppingCart);
    presenter.injectModel(model);
    presenter.injectRouter(router);
    presenter.injectView(new WeakReference<>(view));

    view.injectPresenter(presenter);

  }
}
