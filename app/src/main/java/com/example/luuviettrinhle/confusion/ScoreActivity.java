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
    EditText inputNameET;
    String highscore, playerName;

    public void onButtonClick(View v) {
        //open gameScreen
        if (v.getId() == R.id.againButton) {
            Intent gameScreen = new Intent(this, GameActivity.class);
            startActivity(gameScreen);
            finish();
        //open startScreen
        } else if (v.getId() == R.id.homeButton) {
            Intent startScreen = new Intent(this, StartActivity.class);
            startActivity(startScreen);
            finish();
        //open highscoreScreen
        } else if (v.getId() == R.id.saveButton){
            Intent highscoreScreen = new Intent(this, HighscoreActivity.class);
            //transfer highscore
            highscoreScreen.putExtra("scoreTextView", highscore);
            //get name from EditText
            playerName = (inputNameET.getText()).toString();
            //transfer name and screen
            //2 = scoreScreen
            highscoreScreen.putExtra("inputNameET", playerName);
            highscoreScreen.putExtra("Screen", "2");
            startActivity(highscoreScreen);
            finish();
        //close app
        } else if (v.getId() == R.id.exitButton){
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
            System.exit(0);
        }
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
        inputNameET = (EditText)findViewById(R.id.nameInput);

        //get score
        Intent intent = getIntent();
        highscore = intent.getStringExtra("scoreTextView");

        scoreTV.setText(highscore);
    }
}
