package com.jotish.realestate.adapter;

import android.support.v7.widget.RecyclerView;
import com.jotish.realestate.data.Item;
import com.jotish.realestate.databinding.MapItemLayoutBinding;
import com.jotish.realestate.view.OnPlaceSelectedListener;
import com.jotish.realestate.viewmodel.MapItemViewModel;

public class MapItemViewHolder extends RecyclerView.ViewHolder {
  MapItemLayoutBinding mMapItemLayoutBinding;
  private OnPlaceSelectedListener mOnPlaceSelectedListener;

  public MapItemViewHolder(MapItemLayoutBinding itemBinding,
      OnPlaceSelectedListener onPlaceSelectedListener) {
    super(itemBinding.itemView);
    this.mMapItemLayoutBinding = itemBinding;
    this.mOnPlaceSelectedListener = onPlaceSelectedListener;
  }

  public void bindItem(Item item, int position) {
    if (mMapItemLayoutBinding.getMapItemViewModel() == null) {
      mMapItemLayoutBinding.setMapItemViewModel(
          new MapItemViewModel(itemView.getContext(),item , position, mOnPlaceSelectedListener));
    } else {
      mMapItemLayoutBinding.getMapItemViewModel().setItemAndPosition(item, position);
    }
  }
}
