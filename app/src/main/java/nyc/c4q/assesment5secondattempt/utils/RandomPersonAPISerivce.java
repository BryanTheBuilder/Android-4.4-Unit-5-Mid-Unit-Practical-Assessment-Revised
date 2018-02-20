package nyc.c4q.assesment5secondattempt.utils;

import nyc.c4q.assesment5secondattempt.model.RandomPeople;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by c4q on 1/24/18.
 */

public interface RandomPersonAPISerivce {

  @GET("api/?nat=us&inc=name,location,cell,email,dob,picture&results=20")
  Call<RandomPeople> getRandomPeople();

}
