package nyc.c4q.assesment5secondattempt.Utils;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by c4q on 1/24/18.
 */

public class  ServiceGenerator {
  private static final String BASE_URL = "https://randomuser.me/";

  private static Retrofit.Builder builder = new Retrofit.Builder().baseUrl(BASE_URL)
      .addConverterFactory(
          GsonConverterFactory.create());

  private static Retrofit retrofit = builder.build();

  private static HttpLoggingInterceptor logging = new HttpLoggingInterceptor().setLevel(Level.BODY);

  private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

  public static <S> S createService(Class<S> serviceClass ){
    if(!httpClient.interceptors().contains(logging)){
      httpClient.addInterceptor(logging);
      builder.client(httpClient.build());
      retrofit = builder.build();
    }
    return retrofit.create(serviceClass);
  }
}
