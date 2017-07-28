package com.jotish.realestate.view;

/**
 * Created by jotishsuthar on 29/07/17.
 */

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.CardView;
import android.transition.Scene;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.jotish.realestate.R;
import com.jotish.realestate.data.Item;
import com.jotish.realestate.ui.HideDetailsTransitionSet;
import com.jotish.realestate.ui.ShowDetailsTransitionSet;

public class DetailsLayout extends CoordinatorLayout {

  public CardView cardViewContainer;
  public ImageView placeImageView;
  public TextView titleView;
  TextView mLocationName;
  TextView mPriceView;

  public DetailsLayout(final Context context) {
    this(context, null);
  }

  public DetailsLayout(final Context context, final AttributeSet attrs) {
    super(context, attrs);
  }

  @Override
  protected void onFinishInflate() {
    super.onFinishInflate();
    cardViewContainer = (CardView) findViewById(R.id.cardview);
    placeImageView = (ImageView) findViewById(R.id.headerImage);
    titleView = (TextView) findViewById(R.id.title);
    mLocationName = (TextView) findViewById(R.id.location);
    mPriceView = (TextView) findViewById(R.id.priceTitle);
  }

  private void setData(Item place) {
    titleView.setText(place.getTitle());
    mLocationName.setText(place.getLocation().getAddress());
    mPriceView.setText(getContext().getString(R.string.retail_rice, place.getCurrency(),
        place.getPrice()));
    if (place.getImages() != null && place.getImages().size() > 0) {
      Glide.with(placeImageView.getContext())
          .load(place.getImages().get(0).getUrl())
          .fitCenter()
          .into(placeImageView);
    }
  }

  public static Scene showScene(Activity activity, final ViewGroup container,
      final View sharedView,
      final String transitionName, final Item data) {
    DetailsLayout detailsLayout = (DetailsLayout) activity.getLayoutInflater()
        .inflate(R.layout.item_place, container, false);
    detailsLayout.setData(data);

    TransitionSet set = new ShowDetailsTransitionSet(
        activity, transitionName, sharedView, detailsLayout);
    Scene scene = new Scene(container, (View) detailsLayout);
    TransitionManager.go(scene, set);
    return scene;
  }

  public static Scene hideScene(Activity activity, final ViewGroup container,
      final View sharedView, final String transitionName) {
    DetailsLayout detailsLayout = (DetailsLayout)
        container.findViewById(R.id.bali_details_container);

    TransitionSet set = new HideDetailsTransitionSet(
        activity, transitionName, sharedView, detailsLayout);
    Scene scene = new Scene(container, (View) detailsLayout);
    TransitionManager.go(scene, set);
    return scene;
  }
}
