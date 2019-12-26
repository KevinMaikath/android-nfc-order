package com.example.android_nfc_order.categoryScreen;

import java.lang.ref.WeakReference;

import androidx.fragment.app.FragmentActivity;

import com.example.android_nfc_order.app.AppMediator;
import com.example.android_nfc_order.data.Repository;
import com.example.android_nfc_order.data.ShoppingCart;

public class CategoryScreen {

  public static void configure(CategoryContract.View view) {

    WeakReference<FragmentActivity> context =
        new WeakReference<>((FragmentActivity) view);

    AppMediator mediator = (AppMediator) context.get().getApplication();
    CategoryState state = mediator.getCategoryState();

    Repository repository = Repository.getInstance(context.get());
    ShoppingCart shoppingCart = ShoppingCart.getInstance(context.get());

    CategoryContract.Router router = new CategoryRouter(mediator);
    CategoryContract.Presenter presenter = new CategoryPresenter(state);
    CategoryContract.Model model = new CategoryModel(repository, shoppingCart);
    presenter.injectModel(model);
    presenter.injectRouter(router);
    presenter.injectView(new WeakReference<>(view));

    view.injectPresenter(presenter);

  }
}
