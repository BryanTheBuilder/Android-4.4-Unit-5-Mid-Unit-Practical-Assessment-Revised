package nyc.c4q.assesment5secondattempt.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Results implements Parcelable {

  private Picture picture;
  private String cell;
  private String dob;
  private String email;
  private Location location;
  private Name name;

  protected Results(Parcel in) {
    picture = in.readParcelable(Picture.class.getClassLoader());
    cell = in.readString();
    dob = in.readString();
    email = in.readString();
    location = in.readParcelable(Location.class.getClassLoader());
    name = in.readParcelable(Name.class.getClassLoader());
  }

  public static final Creator<Results> CREATOR = new Creator<Results>() {
    @Override
    public Results createFromParcel(Parcel in) {
      return new Results(in);
    }

    @Override
    public Results[] newArray(int size) {
      return new Results[size];
    }
  };

  public Picture getPicture() {
    return picture;
  }

  public String getCell() {
    return cell;
  }

  public String getDob() {
    return dob;
  }

  public String getEmail() {
    return email;
  }

  public Location getLocation() {
    return location;
  }

  public Name getName() {
    return name;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeParcelable(picture, flags);
    dest.writeString(cell);
    dest.writeString(dob);
    dest.writeString(email);
    dest.writeParcelable(location, flags);
    dest.writeParcelable(name, flags);
  }
}
