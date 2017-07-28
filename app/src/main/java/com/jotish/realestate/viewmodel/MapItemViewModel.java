package com.jotish.realestate.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.jotish.realestate.R;
import com.jotish.realestate.adapter.GalleryAdapter;
import com.jotish.realestate.adapter.OnImageClickListener;
import com.jotish.realestate.data.Item;
import com.jotish.realestate.ui.AutoScrollViewPager;
import com.jotish.realestate.ui.CirclePageIndicator;

public class MapItemViewModel extends BaseObservable{

  private Context mContext;
  private Item mItem;

  public MapItemViewModel(Context context, Item item) {
    mContext = context;
    mItem = item;
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

  @BindingAdapter({"bind:imageUrl","bind:placeHolderDrawable", "bind:errorDrawable" })
  public static void setImageUrl(ImageView imageView, String imageUrl, Drawable placeHolder,
      Drawable
      errorDrawable) {
    Glide.with(imageView.getContext())
        .load(imageUrl)
        .placeholder(placeHolder)
        .fitCenter()
        .fallback(placeHolder)
        .error(errorDrawable)
        .into(imageView);
  }

  public String getMediaImageUrl() {
    if (mItem.getImages() != null && mItem.getImages().size() > 0) {
        return  mItem.getImages().get(0).getUrl();
    }
    return null;
  }

  public void setItem(Item item) {
    this.mItem = item;
    notifyChange();
  }
}
