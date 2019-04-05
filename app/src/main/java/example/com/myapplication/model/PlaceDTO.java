package example.com.myapplication.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kestrella on 4/5/19.
 * ken.aque@gmail.com
 */
public class PlaceDTO {
  private String name;
  private String subtitle;
  @SerializedName("country_code")
  private String countryCode;
  private String color;
  private String banner;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSubtitle() {
    return subtitle;
  }

  public void setSubtitle(String subtitle) {
    this.subtitle = subtitle;
  }

  public String getCountryCode() {
    return countryCode;
  }

  public void setCountryCode(String countryCode) {
    this.countryCode = countryCode;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public String getBanner() {
    return banner;
  }

  public void setBanner(String banner) {
    this.banner = banner;
  }
}
