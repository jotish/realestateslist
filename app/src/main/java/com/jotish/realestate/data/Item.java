package com.jotish.realestate.data;

import java.util.ArrayList;

/**
 * Created by jotishsuthar on 27/07/17.
 */


public class Item
{
  private String id;

  private String title;

  private String price;

  private Location location;

  private String currency = "€";

  private ArrayList<Image> images;

  public String getId ()
  {
    return id;
  }

  public void setId (String id)
  {
    this.id = id;
  }

  public String getTitle ()
  {
    return title;
  }

  public void setTitle (String title)
  {
    this.title = title;
  }

  public String getPrice ()
  {
    return price;
  }

  public String getCurrency ()
  {
    return currency;
  }

  public void setPrice (String price)
  {
    this.price = price;
  }

  public Location getLocation ()
  {
    return location;
  }

  public void setLocation (Location location)
  {
    this.location = location;
  }

  public ArrayList<Image> getImages ()
  {
    return images;
  }

  public void setImages (ArrayList<Image> images)
  {
    this.images = images;
  }

  @Override
  public String toString()
  {
    return "ClassPojo [id = "+id+", title = "+title+", price = "+price+", location = "+location+", images = "+images+"]";
  }
}
