package nyc.c4q.assesment5secondattempt.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by c4q on 1/24/18.
 */

public class RandomPeople implements Parcelable {

  private Info info;
  private ArrayList<Results> results;

  protected RandomPeople(Parcel in) {
    results = in.createTypedArrayList(Results.CREATOR);
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeTypedList(results);
  }

  @Override
  public int describeContents() {
    return 0;
  }

  public static final Creator<RandomPeople> CREATOR = new Creator<RandomPeople>() {
    @Override
    public RandomPeople createFromParcel(Parcel in) {
      return new RandomPeople(in);
    }

    @Override
    public RandomPeople[] newArray(int size) {
      return new RandomPeople[size];
    }
  };

  public Info getInfo() {
    return info;
  }

  public ArrayList<Results> getResults() {
    return results;
  }

}
