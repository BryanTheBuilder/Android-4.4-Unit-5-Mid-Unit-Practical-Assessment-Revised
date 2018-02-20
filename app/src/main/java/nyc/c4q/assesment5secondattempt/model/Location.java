package nyc.c4q.assesment5secondattempt.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Location implements Parcelable {

  private int postcode;
  private String state;
  private String city;
  private String street;

  protected Location(Parcel in) {
    postcode = in.readInt();
    state = in.readString();
    city = in.readString();
    street = in.readString();
  }

  public static final Creator<Location> CREATOR = new Creator<Location>() {
    @Override
    public Location createFromParcel(Parcel in) {
      return new Location(in);
    }

    @Override
    public Location[] newArray(int size) {
      return new Location[size];
    }
  };

  public int getPostcode() {
    return postcode;
  }

  public String getState() {
    return state;
  }

  public String getCity() {
    return city;
  }

  public String getStreet() {
    return street;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(postcode);
    dest.writeString(state);
    dest.writeString(city);
    dest.writeString(street);
  }
}
