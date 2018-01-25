package nyc.c4q.assesment5secondattempt.Controller;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;
import nyc.c4q.assesment5secondattempt.DetailActivity;
import nyc.c4q.assesment5secondattempt.Models.Results;
import nyc.c4q.assesment5secondattempt.R;

/**
 * Created by c4q on 1/24/18.
 */

public class RandomPeopleAdapter extends
    RecyclerView.Adapter<RandomPeopleAdapter.RandomPeopleViewHolder> {

  List<Results> randomPeopleList;
  Context context;

  public RandomPeopleAdapter(
      List<Results> randomPeopleList) {
    this.randomPeopleList = randomPeopleList;
  }

  @Override
  public RandomPeopleAdapter.RandomPeopleViewHolder onCreateViewHolder(ViewGroup parent,
      int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.person_item, parent, false);
    context = parent.getContext();
    return new RandomPeopleViewHolder(view);
  }

  @Override
  public void onBindViewHolder(final RandomPeopleAdapter.RandomPeopleViewHolder holder,
      final int position) {

    String name =
        randomPeopleList.get(position).getName().getFirst() + " " + randomPeopleList.get(position)
            .getName().getLast();

    holder.personName.setText(name);

    Picasso.with(context).load(randomPeopleList.get(position).getPicture().getLarge())
        .into(holder.personImage);

    holder.itemView.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent goToDetailActivity = new Intent(holder.itemView.getContext(), DetailActivity.class);
        goToDetailActivity
            .putExtra("Picture", randomPeopleList.get(position).getPicture().getLarge());
        goToDetailActivity.putExtra("Cell", randomPeopleList.get(position).getCell());
        goToDetailActivity.putExtra("DOB", randomPeopleList.get(position).getDob());
        goToDetailActivity.putExtra("Email", randomPeopleList.get(position).getEmail());
        goToDetailActivity.putExtra("City", randomPeopleList.get(position).getLocation().getCity());
        goToDetailActivity.putExtra("Post_Code",
            String.valueOf(randomPeopleList.get(position).getLocation().getPostcode()));
        goToDetailActivity
            .putExtra("State", randomPeopleList.get(position).getLocation().getState());
        goToDetailActivity
            .putExtra("Street", randomPeopleList.get(position).getLocation().getStreet());
        holder.itemView.getContext().startActivity(goToDetailActivity);
      }
    });

  }

  @Override
  public int getItemCount() {
    return (randomPeopleList == null) ? 0 : randomPeopleList.size();
  }

  public class RandomPeopleViewHolder extends ViewHolder {

    ImageView personImage;
    TextView personName;

    public RandomPeopleViewHolder(View itemView) {
      super(itemView);
      personImage = itemView.findViewById(R.id.person_picture);
      personName = itemView.findViewById(R.id.person_name);
    }
  }

}
