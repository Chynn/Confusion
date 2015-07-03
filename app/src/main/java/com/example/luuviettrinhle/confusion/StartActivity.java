package com.example.luuviettrinhle.confusion;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import dbSQLite.HighscoreActivity;


public class StartActivity extends ActionBarActivity {

    MediaPlayer medPlay;
    private boolean soundState = true;
    ImageButton soundOff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        //start music
        medPlay = MediaPlayer.create(this, R.raw.sakuraseason);
        medPlay.start();
    }

    public void onButtonClick(View v) {
        //open gameScreen
        if (v.getId() == R.id.playButton) {
            Intent gameScreen = new Intent(this, GameActivity.class);
            startActivity(gameScreen);
            finish();
        //open highscoreScreen
        } else if (v.getId() == R.id.highscoreButton) {
            Intent highscoreScreen = new Intent(this, HighscoreActivity.class);
            //transfer screen
            //1 = startScreen
            highscoreScreen.putExtra("Screen", "1");
            startActivity(highscoreScreen);
            finish();
        //open insctructionScreen
        } else if (v.getId() == R.id.instructionButton) {
            Intent insctructionScreen = new Intent(this, InstructionActivity.class);
            startActivity(insctructionScreen);
            finish();
        //turn music off
        } else if (v.getId() == R.id.soundButton){
            //if sound on
            if(soundState){
                soundOff = (ImageButton)findViewById(R.id.soundButton);
                soundOff.setImageResource(R.drawable.soundoff);
                medPlay.pause();
                soundState = false;
            //if sound off
            }else {
                soundOff.setImageResource(R.drawable.speaker);
                medPlay.start();
                soundState = true;
            }
        //close app
        }else if (v.getId() == R.id.exitButton){
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
            System.exit(0);
        }
    }
}
