package com.jotish.realestate.view;

import android.view.View;
import com.jotish.realestate.data.Item;

/**
 * Created by jotishsuthar on 29/07/17.
 */

public interface OnPlaceSelectedListener {
  void onPlaceClicked(View sharedView, String transitionName, final int position, final Item place);
}
