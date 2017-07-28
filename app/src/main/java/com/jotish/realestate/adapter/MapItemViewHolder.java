package com.jotish.realestate.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import com.jotish.realestate.data.Item;
import com.jotish.realestate.databinding.ItemLayoutBinding;
import com.jotish.realestate.databinding.MapItemLayoutBinding;
import com.jotish.realestate.viewmodel.ItemViewModel;
import com.jotish.realestate.viewmodel.MapItemViewModel;

public class MapItemViewHolder extends RecyclerView.ViewHolder {
  MapItemLayoutBinding mMapItemLayoutBinding;

  public MapItemViewHolder(MapItemLayoutBinding itemBinding) {
    super(itemBinding.itemView);
    this.mMapItemLayoutBinding = itemBinding;
  }

  public void bindItem(Item item) {
    if (mMapItemLayoutBinding.getMapItemViewModel() == null) {
      mMapItemLayoutBinding.setMapItemViewModel(
          new MapItemViewModel(itemView.getContext(),item));
    } else {
      mMapItemLayoutBinding.getMapItemViewModel().setItem(item);
    }
  }
}
