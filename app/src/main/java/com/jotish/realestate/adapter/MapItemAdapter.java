package com.jotish.realestate.adapter;


import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.jotish.realestate.R;
import com.jotish.realestate.data.Item;
import com.jotish.realestate.databinding.MapItemLayoutBinding;
import com.jotish.realestate.view.OnPlaceSelectedListener;
import java.util.Collections;
import java.util.List;


public class MapItemAdapter extends RecyclerView.Adapter<MapItemViewHolder> {

  private List<Item> mItems;
  private OnPlaceSelectedListener mOnPlaceSelectedListener;
  public MapItemAdapter(OnPlaceSelectedListener onPlaceSelectedListener) {
    this.mItems = Collections.emptyList();
    mOnPlaceSelectedListener = onPlaceSelectedListener;
  }

  @Override public MapItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    MapItemLayoutBinding mapItemLayoutBinding =
        DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.map_item_layout,
            parent, false);
    return new MapItemViewHolder(mapItemLayoutBinding, mOnPlaceSelectedListener);
  }

  @Override public void onBindViewHolder(MapItemViewHolder holder, int position) {
    holder.bindItem(mItems.get(position), position);
  }

  @Override public int getItemCount() {
    return mItems.size();
  }

  public void setItems(List<Item> items) {
    this.mItems = items;
    notifyDataSetChanged();
  }
}

