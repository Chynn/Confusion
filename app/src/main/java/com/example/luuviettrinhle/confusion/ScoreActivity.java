package com.example.luuviettrinhle.confusion;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import dbSQLite.DBHandler;
import dbSQLite.HighscoreActivity;


public class ScoreActivity extends ActionBarActivity {

    TextView scoreTV;
    EditText inputName;

    public void onButtonClick(View v) {
        if (v.getId() == R.id.againButton) {
            Intent intent = new Intent(this, GameActivity.class);
            startActivity(intent);
            finish();
        } else if (v.getId() == R.id.homeButton) {
            Intent intent = new Intent(this, StartActivity.class);
            startActivity(intent);
            finish();
        } else if (v.getId() == R.id.saveButton){
            Intent intent = new Intent(this, HighscoreActivity.class);
            startActivity(intent);
            finish();
        } else if (v.getId() == R.id.exitButton){
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
            System.exit(0);
        }
        //else if (v.getId() == R.id.nameInput){
        // inputName.setText("");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        Typeface bauhausFont = Typeface.createFromAsset(getAssets(), "bauhaus.ttf");
        Typeface bauFont = Typeface.createFromAsset(getAssets(), "bauhaus.ttf");
        Typeface arialFont = Typeface.createFromAsset(getAssets(), "arial.ttf");

        TextView sTitle = (TextView)findViewById(R.id.scoreTitle);
        TextView score = (TextView)findViewById(R.id.scoreTextView);
        TextView sInput = (TextView)findViewById(R.id.nameInput);

        sTitle.setTypeface(bauhausFont);
        score.setTypeface(bauFont);
        sInput.setTypeface(arialFont);

        scoreTV = (TextView)findViewById(R.id.scoreTextView);
        inputName = (EditText)findViewById(R.id.nameInput);

        Intent intent = getIntent();
        String highscore = intent.getStringExtra("scoreTextView");

        Intent i = getIntent();
        String name = i.getStringExtra("nameInput");

        scoreTV.setText(highscore);
        inputName.setText(name);
    }
}
