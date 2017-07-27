package com.jotish.realestate.adapter;


import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.jotish.realestate.R;
import com.jotish.realestate.data.Item;
import com.jotish.realestate.databinding.ItemLayoutBinding;
import java.util.Collections;
import java.util.List;


public class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder> {

  private List<Item> mItems;

  public ItemAdapter() {
    this.mItems = Collections.emptyList();
  }

  @Override public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    ItemLayoutBinding itemBinding =
        DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_layout,
            parent, false);
    return new ItemViewHolder(itemBinding);
  }

  @Override public void onBindViewHolder(ItemViewHolder holder, int position) {
    holder.bindItem(mItems.get(position));
  }

  @Override public int getItemCount() {
    return mItems.size();
  }

  public void setItems(List<Item> items) {
    this.mItems = items;
    notifyDataSetChanged();
  }
}

