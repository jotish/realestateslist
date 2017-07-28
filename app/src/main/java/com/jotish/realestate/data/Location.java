package com.jotish.realestate.data;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by jotishsuthar on 27/07/17.
 */

public class Location
{
  private String address;

  private String longitude;

  private String latitude;

  public String getAddress ()
  {
    return address;
  }

  public void setAddress (String address)
  {
    this.address = address;
  }

  public String getLongitude ()
  {
    return longitude;
  }

  public void setLongitude (String longitude)
  {
    this.longitude = longitude;
  }

  public String getLatitude ()
  {
    return latitude;
  }

  public void setLatitude (String latitude)
  {
    this.latitude = latitude;
  }

  @Override
  public String toString()
  {
    return "ClassPojo [address = "+address+", longitude = "+longitude+", latitude = "+latitude+"]";
  }

  public LatLng getLatLng() {
    return new LatLng(Double.valueOf(latitude), Double.valueOf(longitude));
  }
}