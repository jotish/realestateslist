package com.jotish.realestate.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.jotish.realestate.R;
import com.jotish.realestate.data.Item;
import com.jotish.realestate.transitions.TransitionUtils;
import com.jotish.realestate.view.OnPlaceSelectedListener;

public class MapItemViewModel extends BaseObservable{

  private Context mContext;
  private Item mItem;
  private int mPosition;
  private OnPlaceSelectedListener mOnPlaceSelectedListener;

  public MapItemViewModel(Context context, Item item, int position,
      OnPlaceSelectedListener onPlaceSelectedListener) {
    mContext = context;
    mItem = item;
    mPosition = position;
    mOnPlaceSelectedListener = onPlaceSelectedListener;
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

  public String getPosition(){
    return String.valueOf(mPosition  + 1);
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

  public void setItemAndPosition(Item item, int position) {
    this.mItem = item;
    this.mPosition = position;
    notifyChange();
  }

  public void onItemClick(View view){
    mOnPlaceSelectedListener.onPlaceClicked(view,
        TransitionUtils.getRecyclerViewTransitionName(mPosition), mPosition, mItem);
  }
}
