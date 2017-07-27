package com.jotish.realestate.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import com.jotish.realestate.R;
import com.jotish.realestate.databinding.ActivityListingBinding;
import com.jotish.realestate.viewmodel.ListingViewModel;

public class ListingActivity extends AppCompatActivity {

  private ActivityListingBinding mActivityListingBinding;
  private ListingViewModel mListingViewModel;
  private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
      = new BottomNavigationView.OnNavigationItemSelectedListener() {

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
      switch (item.getItemId()) {
        case R.id.navigation_list:
          loadListingFragment();
          return true;
        case R.id.navigation_map:
          return true;
      }
      return false;
    }

  };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initDataBinding();
    setSupportActionBar(mActivityListingBinding.toolbar);
    getSupportActionBar().setDisplayShowTitleEnabled(false);
    BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
    navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    navigation.setSelectedItemId(R.id.navigation_list);
  }

  private void initDataBinding() {
    mActivityListingBinding = DataBindingUtil.setContentView(this, R.layout
        .activity_listing);
    mListingViewModel = new ListingViewModel(this);
    mActivityListingBinding.setListingViewModel(mListingViewModel);
  }

  private void loadListingFragment() {
    Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.content_fragment);
    if (currentFragment == null || !(currentFragment instanceof  ItemsListFragment)) {
      ItemsListFragment listingsFragment = ItemsListFragment.newInstance();
      FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
      transaction.replace(R.id.content_fragment, listingsFragment, listingsFragment.getTag());
      transaction.commit();
    }
  }
}
