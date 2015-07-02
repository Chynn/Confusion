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

        medPlay = MediaPlayer.create(this, R.raw.sakuraseason);
        medPlay.start();

    }

    public void onButtonClick(View v) {
        if (v.getId() == R.id.playButton) {
            Intent intent = new Intent(this, GameActivity.class);
            startActivity(intent);
            finish();
        } else if (v.getId() == R.id.highscoreButton) {
            Intent highscoreScreen = new Intent(this, HighscoreActivity.class);
            highscoreScreen.putExtra("Screen", "1");
            startActivity(highscoreScreen);
            finish();
        } else if (v.getId() == R.id.instructionButton) {
            Intent intent = new Intent(this, InstructionActivity.class);
            startActivity(intent);
            finish();
        } else if (v.getId() == R.id.soundButton){
            if(soundState){
                soundOff = (ImageButton)findViewById(R.id.soundButton);
                soundOff.setImageResource(R.drawable.soundoff);
                medPlay.pause();
                soundState = false;
            }else {
                soundOff.setImageResource(R.drawable.speaker);
                medPlay.start();
                soundState = true;
            }
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
