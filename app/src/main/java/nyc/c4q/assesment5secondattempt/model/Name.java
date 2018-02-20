package nyc.c4q.assesment5secondattempt.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Name implements Parcelable {

  private String last;
  private String first;
  private String title;

  protected Name(Parcel in) {
    last = in.readString();
    first = in.readString();
    title = in.readString();
  }

  public static final Creator<Name> CREATOR = new Creator<Name>() {
    @Override
    public Name createFromParcel(Parcel in) {
      return new Name(in);
    }

    @Override
    public Name[] newArray(int size) {
      return new Name[size];
    }
  };

  public String getLast() {
    return last;
  }

  public String getFirst() {
    return first;
  }

  public String getTitle() {
    return title;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(last);
    dest.writeString(first);
    dest.writeString(title);
  }
}
