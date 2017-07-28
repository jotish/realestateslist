package com.jotish.realestate.adapter;

/**
 * Created by jotishsuthar on 27/07/17.
 */

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.jotish.realestate.R;
import com.jotish.realestate.data.Image;
import java.util.ArrayList;
import java.util.List;

public class GalleryAdapter extends PagerAdapter {

  private static final String TAG = "GalleryAdapter";

  private final List<Image> mItems;
  private final LayoutInflater mLayoutInflater;
  /**
   * The click event listener which will propagate click events to the parent or any other listener set
   */
  private OnImageClickListener mOnItemClickListener;

  /**
   * Constructor for gallery adapter which will create and screen slide of images.
   *
   * @param context
   *         The context which will be used to inflate the layout for each page.
   * @param mediaGallery
   *         The list of items which need to be displayed as screen slide.
   */
  public GalleryAdapter(@NonNull Context context,
      @NonNull ArrayList<Image> mediaGallery) {
    super();

    // Inflater which will be used for creating all the necessary pages
    mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    // The items which will be displayed.
    mItems = mediaGallery;
  }

  @Override
  public int getCount() {
    // Just to be safe, check also if we have an valid list of items - never return invalid size.
    return null == mItems ? 0 : mItems.size();
  }

  @Override
  public boolean isViewFromObject(View view, Object object) {
    // The object returned by instantiateItem() is a key/identifier. This method checks whether
    // the View passed to it (representing the page) is associated with that key or not.
    // It is required by a PagerAdapter to function properly.
    return view == object;
  }

  @Override
  public Object instantiateItem(ViewGroup container, final int position) {
    // This method should create the page for the given position passed to it as an argument.
    // In our case, we inflate() our layout resource to create the hierarchy of view objects and then
    // set resource for the ImageView in it.
    // Finally, the inflated view is added to the container (which should be the ViewPager) and return it as well.

    // inflate our layout resource
    View itemView = mLayoutInflater.inflate(R.layout.fragment_gallery_item, container, false);

    // Display the resource on the view
    displayGalleryItem((ImageView) itemView.findViewById(R.id.gallery_item), mItems.get(position));

    // Add our inflated view to the container
    container.addView(itemView);

    // Detect the click events and pass them to any listeners
    itemView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (null != mOnItemClickListener) {
          mOnItemClickListener.onImageClicked(position);
        }
      }
    });

    // Return our view
    return itemView;
  }

  @Override
  public void destroyItem(ViewGroup container, int position, Object object) {
    // Removes the page from the container for the given position. We simply removed object using removeView()
    // but could’ve also used removeViewAt() by passing it the position.
    try {
      // Remove the view from the container
      container.removeView((View) object);

      // Try to clear resources used for displaying this view
      Glide.clear(((View) object).findViewById(R.id.gallery_item));
      // Remove any resources used by this view
      unbindDrawables((View) object);
      // Invalidate the object
      object = null;
    } catch (Exception e) {
      Log.w(TAG, "destroyItem: failed to destroy item and clear it's used resources", e);
    }
  }

  /**
   * Recursively unbind any resources from the provided view. This method will clear the resources of all the
   * children of the view before invalidating the provided view itself.
   *
   * @param view
   *         The view for which to unbind resource.
   */
  protected void unbindDrawables(View view) {
    if (view.getBackground() != null) {
      view.getBackground().setCallback(null);
    }
    if (view instanceof ViewGroup) {
      for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
        unbindDrawables(((ViewGroup) view).getChildAt(i));
      }
      ((ViewGroup) view).removeAllViews();
    }
  }

  /**
   * Set an listener which will notify of any click events that are detected on the pages of the view pager.
   *
   * @param onItemClickListener
   *         The listener. If {@code null} it will disable any events from being sent.
   */
  public void setOnItemClickListener(OnImageClickListener onItemClickListener) {
    mOnItemClickListener = onItemClickListener;
  }

  /**
   * Display the gallery image into the image view provided.
   *
   * @param galleryView
   *         The view which will display the image.
   * @param galleryItem
   *         The item from which to get the image.
   */
  private void displayGalleryItem(ImageView galleryView, Image galleryItem) {
    if (null != galleryItem) {
      Glide.with(galleryView.getContext()) // Bind it with the context of the actual view used
          .load(galleryItem.getUrl()) // Load the image
          .asBitmap() // All our images are static, we want to display them as bitmaps
          .format(DecodeFormat.PREFER_RGB_565) // the decode format - this will not use alpha at all
          .centerCrop() // scale type
          .error(R.drawable.network_failure)
          .placeholder(R.drawable.image_placeholder) // temporary holder displayed while the image loads
          .animate(R.anim.fade_in) // need to manually set the animation as bitmap cannot use cross fade
          .thumbnail(0.2f) // make use of the thumbnail which can display a down-sized version of the image
          .into(galleryView); // Voilla - the target view
    }
  }
}