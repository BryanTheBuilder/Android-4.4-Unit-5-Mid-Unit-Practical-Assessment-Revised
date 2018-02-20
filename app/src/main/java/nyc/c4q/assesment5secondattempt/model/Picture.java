package nyc.c4q.assesment5secondattempt.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Picture implements Parcelable {

  private String thumbnail;
  private String medium;
  private String large;

  protected Picture(Parcel in) {
    thumbnail = in.readString();
    medium = in.readString();
    large = in.readString();
  }

  public static final Creator<Picture> CREATOR = new Creator<Picture>() {
    @Override
    public Picture createFromParcel(Parcel in) {
      return new Picture(in);
    }

    @Override
    public Picture[] newArray(int size) {
      return new Picture[size];
    }
  };

  public String getThumbnail() {
    return thumbnail;
  }

  public String getMedium() {
    return medium;
  }

  public String getLarge() {
    return large;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(thumbnail);
    dest.writeString(medium);
    dest.writeString(large);
  }
}
