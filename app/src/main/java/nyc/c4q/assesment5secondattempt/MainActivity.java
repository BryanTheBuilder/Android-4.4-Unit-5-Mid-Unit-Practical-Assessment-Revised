package nyc.c4q.assesment5secondattempt;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import nyc.c4q.assesment5secondattempt.Controller.RandomPeopleAdapter;
import nyc.c4q.assesment5secondattempt.Models.RandomPeople;
import nyc.c4q.assesment5secondattempt.Models.Results;
import nyc.c4q.assesment5secondattempt.Utils.RandomPersonAPISerivce;
import nyc.c4q.assesment5secondattempt.Utils.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

  private static final String TAG = MainActivity.class.getSimpleName();
  RandomPersonAPISerivce randomPersonAPISerivce;
  RecyclerView recyclerView;
  RandomPeopleAdapter randomPeopleAdapter;
  List<Results> randomPeopleList = new ArrayList<>();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    randomPersonAPISerivce = ServiceGenerator.createService(RandomPersonAPISerivce.class);
    recyclerView = findViewById(R.id.random_people_rv);

    if (getApplication().getResources().getConfiguration().orientation
        == Configuration.ORIENTATION_PORTRAIT) {
      recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
    } else {
      recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));
    }

    randomPeopleAdapter = new RandomPeopleAdapter(randomPeopleList);
    recyclerView.setAdapter(randomPeopleAdapter);

    fetchRandomPeopleList();
  }

  private void fetchRandomPeopleList() {
    Call<RandomPeople> call = randomPersonAPISerivce.getRandomPeople();
    call.enqueue(new Callback<RandomPeople>() {
      @Override
      public void onResponse(Call<RandomPeople> call, Response<RandomPeople> response) {
        List<Results> newResults = response.body().getResults();
        recyclerView.setAdapter(new RandomPeopleAdapter(newResults));
      }

      @Override
      public void onFailure(Call<RandomPeople> call, Throwable t) {
        Log.e(TAG, "error message: " + t.getMessage());
      }
    });

  }
}
