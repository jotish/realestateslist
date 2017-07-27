package com.jotish.realestate.data;

/**
 * Created by jotishsuthar on 27/07/17.
 */

public class Image
{
  private String id;

  private String url;

  public String getId ()
  {
    return id;
  }

  public void setId (String id)
  {
    this.id = id;
  }

  public String getUrl ()
  {
    return url;
  }

  public void setUrl (String url)
  {
    this.url = url;
  }

  @Override
  public String toString()
  {
    return "ClassPojo [id = "+id+", url = "+url+"]";
  }
}


