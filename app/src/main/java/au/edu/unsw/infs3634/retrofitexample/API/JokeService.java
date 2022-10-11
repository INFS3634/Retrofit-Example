package au.edu.unsw.infs3634.retrofitexample.API;

import retrofit2.Call;
import retrofit2.http.GET;

// Retrofit interface to provide a list of annotations for HTTP methods
public interface JokeService {
    @GET("jokes/random")
    Call<Joke> getRandomJoke();
}
