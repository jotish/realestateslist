package com.jotish.realestate.viewmodel;

import android.content.Context;
import android.support.annotation.NonNull;
import java.util.Observable;

public class ActivityListingViewModel extends Observable {
  private Context mContext;

  public ActivityListingViewModel(@NonNull Context context) {
    mContext = context;
  }
}
