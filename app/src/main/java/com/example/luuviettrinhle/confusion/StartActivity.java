package com.example.luuviettrinhle.confusion;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;


public class StartActivity extends ActionBarActivity {

    MediaPlayer medPlay;
    //private boolean soundState = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        //terminates the app, when exitButton is clicked
        ImageButton exitButton = (ImageButton)findViewById(R.id.exitButton);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });

        medPlay = MediaPlayer.create(this, R.raw.sakuraseason);
        medPlay.start();
    }

    @Override
    protected void onDestroy() {
        medPlay.release();
        super.onDestroy();
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
            if(medPlay.isPlaying()){
                medPlay.pause();
                //soundState = false;
            }else {
                medPlay.seekTo(0);
                medPlay.start();
                //soundState = true;
            }
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
