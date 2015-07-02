package com.example.luuviettrinhle.confusion;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Random;


public class GameActivity extends ActionBarActivity {

    Resources res;
    ImageButton imgBtn1, imgBtn2, imgBtn3;
    TextView scoreTV, timerTV;
    Random r = new Random();
    int lastPerm = 0;
    int permPos = 0;
    Drawable[][] permutations;
    Drawable ovRed, ovGreen, ovPurple;
    int score = 0;
    int timerI = 0;
    String[] times;
    private final static int interval = 1000;
    Handler timeHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Typeface bauhausFont = Typeface.createFromAsset(getAssets(), "bauhaus.ttf");
        TextView timer = (TextView)findViewById(R.id.timeTextView);
        timer.setTypeface(bauhausFont);

        Typeface bauFont = Typeface.createFromAsset(getAssets(), "bauhaus.ttf");
        TextView scores = (TextView)findViewById(R.id.scoreTextView);
        scores.setTypeface(bauFont);


        res = getResources();//
        ovRed = res.getDrawable(R.drawable.oval2);
        ovGreen = res.getDrawable(R.drawable.oval1);
        ovPurple = res.getDrawable(R.drawable.oval3);

        permutations = new Drawable[][]{
            {ovGreen,ovRed,ovPurple}, //left
            {ovRed,ovGreen,ovPurple}, //mid
            {ovRed,ovPurple,ovGreen}, //right
            {ovPurple,ovRed,ovGreen}, //right
            {ovPurple,ovGreen,ovRed}, //mid
            {ovGreen,ovPurple,ovRed}  //left
        };

        imgBtn1 = (ImageButton)findViewById(R.id.iBtn1);
        imgBtn2 = (ImageButton)findViewById(R.id.iBtn2);
        imgBtn3 = (ImageButton)findViewById(R.id.iBtn3);

        timerTV = (TextView)findViewById(R.id.timeTextView);

        scoreTV = (TextView)findViewById(R.id.scoreTextView);
        scoreTV.setText("Score: " + score);

        //permPos anfangs = 0
        setImageButtons(permPos);

        times = new String[29];
        for (int i=0;i<times.length;i++){
            times[i] = ""+i;
        }

        startRepeatingTimerTask();

    }

    final Runnable timeHandlerTask = new Runnable() {
        @Override
        public void run() {
            new TimeTask().execute(times);
            timeHandler.postDelayed(timeHandlerTask, interval);
        }
    };

    private void startRepeatingTimerTask(){
        timeHandlerTask.run();
    }

    private void stopRepeatingTimerTask(){
        timeHandler.removeCallbacks(timeHandlerTask);
    }

    private class TimeTask extends AsyncTask<String, Void, String> {

        protected String doInBackground(String[] times) {
            String currentTime = times[timerI];
            timerI++;
            return currentTime;
        }

        protected void onPostExecute(String time) {
            if((Integer.parseInt(time)) == 5){
                stopRepeatingTimerTask();
                timerI = 0;
                Intent scoreScreen = new Intent(GameActivity.this, ScoreActivity.class);
                scoreScreen.putExtra("scoreTextView", "" + score);
                startActivity(scoreScreen);
                finish();
            }else {
                timerTV.setText("Time: " + time);
            }
        }
    }

    public void setImageButtons(int permPos){
        imgBtn1.setImageDrawable(permutations[permPos][0]);
        imgBtn2.setImageDrawable(permutations[permPos][1]);
        imgBtn3.setImageDrawable(permutations[permPos][2]);
    }

        public void onImageButtonClick(View v) {

        if (v.getId() == R.id.iBtn1) {
            if (imgBtn1.getDrawable() == ovGreen) {
                while (permPos == lastPerm) {
                    permPos = r.nextInt(permutations.length);
                }
                lastPerm = permPos;
                setImageButtons(permPos);
                score++;
            }else if(score > 0) score--;
            scoreTV.setText("Score: " + score);

        } else if (v.getId() == R.id.iBtn2) {
            if (imgBtn2.getDrawable() == ovGreen) {
                while (permPos == lastPerm) {
                    permPos = r.nextInt(permutations.length);
                }
                lastPerm = permPos;
                setImageButtons(permPos);
                score++;
            }else if(score > 0) score--;
            scoreTV.setText("Score: " + score);

        } else if (v.getId() == R.id.iBtn3) {
            if (imgBtn3.getDrawable() == ovGreen) {
                while (permPos == lastPerm) {
                    permPos = r.nextInt(permutations.length);
                }
                lastPerm = permPos;
                setImageButtons(permPos);
                score++;
            }else if(score > 0) score--;
            scoreTV.setText("Score: " + score);
        }
    }

    public void onButtonClick(View v) {
        if (v.getId() == R.id.backButton) {
            stopRepeatingTimerTask();
            timerI = 0;
            Intent intent = new Intent(this, StartActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
