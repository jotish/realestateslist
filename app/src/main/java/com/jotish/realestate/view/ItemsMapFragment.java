package com.jotish.realestate.view;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnCameraIdleListener;
import com.google.android.gms.maps.GoogleMap.OnCameraMoveListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLngBounds;
import com.jotish.realestate.R;
import com.jotish.realestate.adapter.MapItemAdapter;
import com.jotish.realestate.data.Item;
import com.jotish.realestate.databinding.FragmentItemMapBinding;
import com.jotish.realestate.transitions.ScaleDownImageTransition;
import com.jotish.realestate.ui.maps.MapBitmapCache;
import com.jotish.realestate.ui.maps.PulseOverlayLayout;
import com.jotish.realestate.viewmodel.FragmentMapViewModel;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class ItemsMapFragment extends Fragment implements OnMapReadyCallback, Observer {

  private FragmentMapViewModel mItemMapViewModel;
  private FragmentItemMapBinding mFragmentItemMapBinding;
  private FragmentActivity mContext;

  public static ItemsMapFragment newInstance(final Context context) {
    ItemsMapFragment fragment = new ItemsMapFragment();
    ScaleDownImageTransition transition = new ScaleDownImageTransition(context, MapBitmapCache.instance().getBitmap());
    transition.addTarget(context.getString(R.string.mapPlaceholderTransition));
    transition.setDuration(600);
    fragment.setEnterTransition(transition);
    return fragment;
  }




  @Override
  public void onCreate(@Nullable final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mContext = getActivity();
  }

  @Nullable
  @Override
  public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container,
      @Nullable final Bundle savedInstanceState) {
    mFragmentItemMapBinding = FragmentItemMapBinding.inflate(inflater,
        container,false);
    mItemMapViewModel = new FragmentMapViewModel(mContext);
    mFragmentItemMapBinding.setItemMapViewModel(mItemMapViewModel);
    setupItemsView(mFragmentItemMapBinding.recyclerview);
    mItemMapViewModel.init();
    setupMapFragment();
    setupObserver(mItemMapViewModel);
    return mFragmentItemMapBinding.getRoot();
  }

  public void setupObserver(Observable observable) {
    observable.addObserver(this);
  }

  private void setupItemsView(RecyclerView listRecyler) {
    MapItemAdapter adapter = new MapItemAdapter();
    listRecyler.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
    listRecyler.setAdapter(adapter);
  }

  private void setupMapFragment() {
    ((SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.mapFragment)).getMapAsync(this);
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    mItemMapViewModel.reset();
  }

  @Override
  public void onMapReady(final GoogleMap googleMap) {
    mFragmentItemMapBinding.mapOverlayLayout.setupMap(googleMap);
  }

  private void setupMapLayout(final PulseOverlayLayout mapOverlayLayout,
      LatLngBounds latLngBounds, final List<Item> items) {
    mapOverlayLayout.moveCamera(latLngBounds);
    mapOverlayLayout.setOnCameraIdleListener(new OnCameraIdleListener() {
      @Override
      public void onCameraIdle() {
        for (int i = 0; i < items.size(); i++) {
          mapOverlayLayout.createAndShowMarker(i, items.get(i).getLocation().getLatLng());
        }
        mapOverlayLayout.setOnCameraIdleListener(null);
      }
    });
    mapOverlayLayout.setOnCameraMoveListener(new OnCameraMoveListener() {
      @Override
      public void onCameraMove() {
        mapOverlayLayout.refresh();
      }
    });
  }

  @Override
  public void update(final Observable observable, final Object o) {
    if (observable instanceof FragmentMapViewModel) {
      MapItemAdapter adapter = (MapItemAdapter) mFragmentItemMapBinding.recyclerview.getAdapter();
      FragmentMapViewModel itemMapViewModel = (FragmentMapViewModel) observable;
      List<Item> items = itemMapViewModel.getProperties();
      adapter.setItems(items);
      setupMapLayout(mFragmentItemMapBinding.mapOverlayLayout,
          mItemMapViewModel.provideLatLngBoundsForAllPlaces(), items);
    }
  }
}
