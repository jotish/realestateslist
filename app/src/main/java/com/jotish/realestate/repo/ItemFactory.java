package com.jotish.realestate.repo;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ItemFactory {

  private final static String BASE_API_URL = "https://private-e618e0-mobiletask2.apiary-mock.com";

  public static ItemRepo create() {
    Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build();
    return retrofit.create(ItemRepo.class);
  }
}
