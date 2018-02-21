package nyc.c4q.assesment5secondattempt;

import android.content.res.Configuration;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import nyc.c4q.assesment5secondattempt.controller.RandomPeopleAdapter;
import nyc.c4q.assesment5secondattempt.model.RandomPeople;
import nyc.c4q.assesment5secondattempt.model.Results;
import nyc.c4q.assesment5secondattempt.utils.RandomPersonAPISerivce;
import nyc.c4q.assesment5secondattempt.utils.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

  private static final String TAG = MainActivity.class.getSimpleName();
  RandomPersonAPISerivce randomPersonAPISerivce;
  RecyclerView recyclerView;
  RandomPeopleAdapter randomPeopleAdapter;
  ArrayList<Results> randomPeopleList = new ArrayList<>();
  ProgressBar progressBar;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    progressBar = findViewById(R.id.activity_main_pb);
    progressBar.setVisibility(View.VISIBLE);

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

    if (savedInstanceState != null && savedInstanceState.containsKey("users")) {
      randomPeopleList.clear();
      progressBar.setVisibility(View.VISIBLE);
      randomPeopleAdapter.notifyDataSetChanged();
      ArrayList<Results> savedList = savedInstanceState.getParcelableArrayList("users");
      randomPeopleList.addAll(savedList);
      randomPeopleAdapter.notifyDataSetChanged();
      progressBar.setVisibility(View.INVISIBLE);
    } else {
      fetchRandomPeopleList();
    }
  }

  private void fetchRandomPeopleList() {
    Call<RandomPeople> call = randomPersonAPISerivce.getRandomPeople();
    call.enqueue(new Callback<RandomPeople>() {
      @Override
      public void onResponse(Call<RandomPeople> call, Response<RandomPeople> response) {
        randomPeopleList.addAll(response.body().getResults());
        randomPeopleAdapter.notifyDataSetChanged();
        progressBar.setVisibility(View.INVISIBLE);
      }

      @Override
      public void onFailure(Call<RandomPeople> call, Throwable t) {
        Log.e(TAG, "error message: " + t.getMessage());
      }
    });
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.main_menu, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    if (id == R.id.action_refresh) {
      randomPeopleList.clear();
      progressBar.setVisibility(View.VISIBLE);
      randomPeopleAdapter.notifyDataSetChanged();
      fetchRandomPeopleList();
    }
    return super.onOptionsItemSelected(item);
  }

  @Override
  protected void onSaveInstanceState(Bundle outState) {
    outState.putParcelableArrayList("users", randomPeopleList);
    super.onSaveInstanceState(outState);
  }
}
