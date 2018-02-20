package nyc.c4q.assesment5secondattempt;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

  ImageView imageView;
  ListView listView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_detail);

    imageView = findViewById(R.id.detail_person_image);
    listView = findViewById(R.id.details_list_view);
    getDetails();
  }

  private void getDetails() {
    Intent detailIntent = getIntent();
    String[] items = {detailIntent.getStringExtra("Cell"), detailIntent.getStringExtra("DOB"),
        detailIntent.getStringExtra("Email"), detailIntent.getStringExtra("City"),
        detailIntent.getStringExtra("Post_Code"), detailIntent.getStringExtra("State"),
        detailIntent.getStringExtra("Street")};
    Picasso.with(getApplicationContext()).load(detailIntent.getStringExtra("Picture"))
        .into(imageView);
    ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.detail_string_item,
        R.id.detail_text, items);
    listView.setAdapter(adapter);
  }
}
