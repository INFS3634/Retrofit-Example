package au.edu.unsw.infs3634.retrofitexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import au.edu.unsw.infs3634.retrofitexample.API.Joke;
import au.edu.unsw.infs3634.retrofitexample.API.JokeService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    final String TAG = "MainActivity";
    Button mButton;
    TextView mJoke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButton = findViewById(R.id.btnDisplay);
        mJoke = findViewById(R.id.tvJoke);

        // Implement Retrofit to make API call
        // Implement Retrofit GSON converter library using the addConverterFactory method
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.chucknorris.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Implement the Service interface using the Retrofit create() method
        JokeService service = retrofit.create(JokeService.class);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Implement a Call and Response object
                Call<Joke> responseCall = service.getRandomJoke();
                // Use enqueue method to asynchronously send the request and notify callback of its response
                // or if an error occurred talking to the server, creating the request, or processing the response
                responseCall.enqueue(new Callback<Joke>() {
                    @Override
                    public void onResponse(Call<Joke> call, Response<Joke> response) {
                        Log.d(TAG, "API success!");
                        // Create a Joke object class from the body of the response
                        Joke joke = response.body();
                        mJoke.setText(joke.getValue());
                    }

                    @Override
                    public void onFailure(Call<Joke> call, Throwable t) {
                        // Log a message since an error occurred talking to the server
                        Log.d(TAG, "API Failure");
                    }
                });
            }
        });
    }
}