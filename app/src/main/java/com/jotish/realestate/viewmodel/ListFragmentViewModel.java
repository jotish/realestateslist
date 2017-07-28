package com.jotish.realestate.viewmodel;


import android.content.Context;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import android.view.View;
import com.jotish.realestate.R;
import com.jotish.realestate.RealEstateApplication;
import com.jotish.realestate.data.Item;
import com.jotish.realestate.data.Items;
import com.jotish.realestate.repo.ItemRepo;
import com.jotish.realestate.utils.Utils;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class ListFragmentViewModel extends Observable {

  private Context mContext;
  public ObservableInt listProgress;
  public ObservableInt itemRecycler;
  private CompositeDisposable compositeDisposable = new CompositeDisposable();
  private List<Item> mProperties;

  public ListFragmentViewModel(@NonNull Context context) {
    mContext = context;
    mProperties = new ArrayList<>();
    listProgress = new ObservableInt(View.GONE);
    itemRecycler = new ObservableInt(View.GONE);
    compositeDisposable = new CompositeDisposable();
  }

  public void init() {
    initializeViews();
    getItemList();
  }

  public void initializeViews() {
    itemRecycler.set(View.GONE);
    listProgress.set(View.VISIBLE);
  }

  public void showList() {
    listProgress.set(View.GONE);
    itemRecycler.set(View.VISIBLE);
  }

  public void showFailureState() {
    listProgress.set(View.GONE);
    itemRecycler.set(View.GONE);
    Utils.showNotificationToast(mContext, mContext.getString(R.string.error_network));
  }

  private void getItemList() {

    RealEstateApplication realEstateApplication = RealEstateApplication.create(mContext);
    ItemRepo itemService = realEstateApplication.getItemRepo();

    Disposable disposable = itemService.fetchItems()
        .subscribeOn(realEstateApplication.subscribeScheduler())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<Items>() {
          @Override public void accept(Items itemResponse) throws Exception {
            changeItemListSet(itemResponse.getItems());
            showList();
          }
        }, new Consumer<Throwable>() {
          @Override public void accept(Throwable throwable) throws Exception {
            showFailureState();
          }
        });
    compositeDisposable.add(disposable);
  }

  private void changeItemListSet(ArrayList<Item> items) {
    mProperties.addAll(items);
    setChanged();
    notifyObservers();
  }
  public void reset() {
    unSubscribeFromObservable();
    compositeDisposable = null;
    mContext = null;
  }
  private void unSubscribeFromObservable() {
    if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
      compositeDisposable.dispose();
    }
  }

  public List<Item> getProperties() {
    return mProperties;
  }
}
