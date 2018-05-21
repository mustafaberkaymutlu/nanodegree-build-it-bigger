package net.epictimes.jokedisplayer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class JokeDisplayerActivity extends AppCompatActivity {
    private static final String KEY_JOKE = "key_joke";

    public static Intent newIntent(@NonNull Context context, @NonNull String joke) {
        final Intent intent = new Intent(context, JokeDisplayerActivity.class);
        intent.putExtra(KEY_JOKE, joke);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_displayer);

        final TextView textViewJoke = findViewById(R.id.textViewJoke);

        final Bundle extras = Preconditions.checkNotNull(getIntent().getExtras(),
                "Extras cannot be null. Please use the newIntent method. ");
        final String joke = extras.getString(KEY_JOKE);

        textViewJoke.setText(joke);
    }
}
