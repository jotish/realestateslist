package com.jotish.realestate;

import android.app.Application;
import android.content.Context;
import com.jotish.realestate.repo.ItemFactory;
import com.jotish.realestate.repo.ItemRepo;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by jotishsuthar on 27/07/17.
 */


public class RealEstateApplication  extends Application {

  private ItemRepo mItemRepo;
  private Scheduler mScheduler;

  private static RealEstateApplication get(Context context) {
    return (RealEstateApplication) context.getApplicationContext();
  }

  public static RealEstateApplication create(Context context) {
    return RealEstateApplication.get(context);
  }

  public ItemRepo getItemRepo() {
    if (mItemRepo == null) {
      mItemRepo = ItemFactory.create();
    }
    return mItemRepo;
  }

  public Scheduler subscribeScheduler() {
    if (mScheduler == null) {
      mScheduler = Schedulers.io();
    }

    return mScheduler;
  }
}

