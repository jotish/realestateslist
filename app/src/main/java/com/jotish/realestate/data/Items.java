package com.jotish.realestate.data;

import java.util.ArrayList;

/**
 * Created by jotishsuthar on 27/07/17.
 */

public class Items
{
  private ArrayList<Item> items;

  public ArrayList<Item> getItems ()
  {
    return items;
  }

  public void setItems (ArrayList<Item> items)
  {
    this.items = items;
  }

  @Override
  public String toString()
  {
    return "ClassPojo [items = "+items+"]";
  }
}