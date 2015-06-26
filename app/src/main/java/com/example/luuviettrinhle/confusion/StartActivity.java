package com.example.luuviettrinhle.confusion;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import dbSQLite.HighscoreActivity;


public class StartActivity extends ActionBarActivity {

    MediaPlayer medPlay;
    private boolean soundState = true;


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
        } else if (v.getId() == R.id.highscoreButton) {
            Intent intent = new Intent(this, HighscoreActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.instructionButton) {
            Intent intent = new Intent(this, InstructionActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.soundButton){
            if(soundState){
                medPlay.pause();
                soundState = false;
            }else {
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
}
