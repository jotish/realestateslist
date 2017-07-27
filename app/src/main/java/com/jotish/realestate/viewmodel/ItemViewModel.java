package com.jotish.realestate.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.jotish.realestate.R;
import com.jotish.realestate.adapter.GalleryAdapter;
import com.jotish.realestate.adapter.OnImageClickListener;
import com.jotish.realestate.data.Item;
import com.jotish.realestate.ui.AutoScrollViewPager;
import com.jotish.realestate.ui.CirclePageIndicator;

public class ItemViewModel extends BaseObservable{

  private Context mContext;
  private Item mItem;
  private OnTouchListener mOnTouchListener;
  private OnImageClickListener mOnImageClickListener;

  public ItemViewModel(Context context, Item item, OnTouchListener onTouchListener,
      OnImageClickListener onImageClickListener) {
    mContext = context;
    mItem = item;
    mOnTouchListener = onTouchListener;
    mOnImageClickListener = onImageClickListener;
  }

  public String getItemName() {
    return mItem.getTitle();
  }


  public String getLocationName() {
    return mItem.getLocation().getAddress();
  }


  public String getPrice() {
    return mContext.getString(R.string.retail_rice, mItem.getCurrency(),
        mItem.getPrice());
  }

  public GalleryAdapter getAdapter() {
    GalleryAdapter galleryAdapter = new GalleryAdapter(mContext, mItem.getImages());
    galleryAdapter.setOnItemClickListener(mOnImageClickListener);
    return galleryAdapter;
  }


  @BindingAdapter({"bind:adapter", "bind:touchListener", "bind:circlePageIndicator"})
  public static void bindViewPagerAdapter(final AutoScrollViewPager autoScrollViewPager,
      GalleryAdapter adapter, OnTouchListener onTouchListener, CirclePageIndicator circlePageIndicator) {
    autoScrollViewPager.setAdapter(adapter);
    if (adapter.getCount() > 1) {
      circlePageIndicator.setViewPager(autoScrollViewPager);
    }
    autoScrollViewPager.startAutoScroll();
    autoScrollViewPager.setInterval(2000); // 2 seconds
    autoScrollViewPager.setOffscreenPageLimit(2);
    autoScrollViewPager.setOnTouchListener(onTouchListener);
    autoScrollViewPager.setScrollDurationFactor(4);
  }



  public void setItem(Item item) {
    this.mItem = item;
    notifyChange();
  }

  public OnTouchListener getTouchListener() {
    return mOnTouchListener;
  }
}
