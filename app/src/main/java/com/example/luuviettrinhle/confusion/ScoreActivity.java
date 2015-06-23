package com.example.luuviettrinhle.confusion;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class ScoreActivity extends ActionBarActivity {

    public void onButtonClick(View v) {
        if (v.getId() == R.id.highscoreButton) {
            Intent intent = new Intent(this, HighscoreActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.againButton) {
            Intent intent = new Intent(this, GameActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.homeButton) {
            Intent intent = new Intent(this, StartActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        Typeface baushausFont = Typeface.createFromAsset(getAssets(), "bauhaus.ttf");
        TextView sTitle = (TextView)findViewById(R.id.scoreTitle);
        sTitle.setTypeface(baushausFont);

        Typeface arialFont = Typeface.createFromAsset(getAssets(), "arial.ttf");
        TextView sInput = (TextView)findViewById(R.id.nameInput);
        sInput.setTypeface(arialFont);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_score, menu);
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
}
