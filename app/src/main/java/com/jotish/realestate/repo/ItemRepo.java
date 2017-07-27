package com.jotish.realestate.repo;

import com.jotish.realestate.data.Items;
import io.reactivex.Observable;
import retrofit2.http.GET;


public interface ItemRepo {

  @GET("realestates")
  Observable<Items> fetchItems();
}
