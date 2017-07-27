package com.jotish.realestate.viewmodel;

import android.content.Context;
import android.support.annotation.NonNull;
import java.util.Observable;

public class ListingViewModel extends Observable {
  private Context mContext;

  public ListingViewModel(@NonNull Context context) {
    mContext = context;
  }
}
