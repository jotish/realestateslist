package com.jotish.realestate.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import com.jotish.realestate.data.Item;
import com.jotish.realestate.databinding.ItemLayoutBinding;
import com.jotish.realestate.viewmodel.ItemViewModel;

public class ItemViewHolder extends RecyclerView.ViewHolder implements OnImageClickListener,
    OnTouchListener{
  ItemLayoutBinding mLayoutBinding;

  public ItemViewHolder(ItemLayoutBinding itemBinding) {
    super(itemBinding.itemView);
    this.mLayoutBinding = itemBinding;
  }

  public void bindItem(Item item) {
    if (mLayoutBinding.getItemViewModel() == null) {
      mLayoutBinding.setItemViewModel(
          new ItemViewModel(itemView.getContext(),item, this, this));
    } else {
      mLayoutBinding.getItemViewModel().setItem(item);
    }
  }

  @Override
  public boolean onTouch(final View view, final MotionEvent motionEvent) {
    mLayoutBinding.photosPager.stopAutoScroll();
    return false;
  }

  @Override
  public void onImageClicked(final int position) {
    mLayoutBinding.photosPager.stopAutoScroll();
  }
}
