package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import net.epictimes.jokedisplayer.JokeDisplayerActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) {
        new JokerAsyncTask(new JokerAsyncTask.Callback() {
            @Override
            public void onResult(String joke) {
                final Intent displayerIntent = JokeDisplayerActivity.newIntent(MainActivity.this, joke);
                startActivity(displayerIntent);
            }

            @Override
            public void onFailed() {
                Toast.makeText(MainActivity.this, "Joker failed to amuse us.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled() {
                Toast.makeText(MainActivity.this, "Joker decided to quit.", Toast.LENGTH_SHORT).show();
            }
        }).execute();
    }

}
