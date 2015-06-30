package com.example.luuviettrinhle.confusion;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;


public class GameActivity extends ActionBarActivity {

    Resources res;
    ImageButton imgBtn1, imgBtn2, imgBtn3;
    TextView tv;
    Random r = new Random();
    int lastPerm = 0;
    int permPos = 0;
    Drawable[][] permutations;
    Drawable recRed, recGreen, recBlue;
    int score = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        res = getResources();
        recRed = res.getDrawable(R.drawable.rectangle2);
        recGreen = res.getDrawable(R.drawable.rectangle1);
        recBlue = res.getDrawable(R.drawable.rectangle3);

        permutations = new Drawable[][]{
            {recGreen,recRed,recBlue}, //left
            {recRed,recGreen,recBlue}, //mid
            {recRed,recBlue,recGreen}, //right
            {recBlue,recRed,recGreen}, //right
            {recBlue,recGreen,recRed}, //mid
            {recGreen,recBlue,recRed}  //left
        };

        imgBtn1 = (ImageButton)findViewById(R.id.iBtn1);
        imgBtn2 = (ImageButton)findViewById(R.id.iBtn2);
        imgBtn3 = (ImageButton)findViewById(R.id.iBtn3);

        tv = (TextView)findViewById(R.id.scoreTextView);
        tv.setText("" + score);

        //permPos anfangs = 0
        setImageButtons(permPos);
    }

    public void setImageButtons(int permPos){
        imgBtn1.setImageDrawable(permutations[permPos][0]);
        imgBtn2.setImageDrawable(permutations[permPos][1]);
        imgBtn3.setImageDrawable(permutations[permPos][2]);
    }

    public void onImageButtonClick(View v) {

        if (v.getId() == R.id.iBtn1) {
            if (imgBtn1.getDrawable() == recGreen) {
                while (permPos == lastPerm) {
                    permPos = r.nextInt(permutations.length);
                }
                lastPerm = permPos;
                setImageButtons(permPos);
                score++;
            }else if(score > 0) score--;
            tv.setText("" + score);

        } else if (v.getId() == R.id.iBtn2) {
            if (imgBtn2.getDrawable() == recGreen) {
                while (permPos == lastPerm) {
                    permPos = r.nextInt(permutations.length);
                }
                lastPerm = permPos;
                setImageButtons(permPos);
                score++;
            }else if(score > 0) score--;
            tv.setText("" + score);

        } else if (v.getId() == R.id.iBtn3) {
            if (imgBtn3.getDrawable() == recGreen) {
                while (permPos == lastPerm) {
                    permPos = r.nextInt(permutations.length);
                }
                lastPerm = permPos;
                setImageButtons(permPos);
                score++;
            }else if(score > 0) score--;
            tv.setText("" + score);
        }
    }

    public void onButtonClick(View v) {
        if (v.getId() == R.id.backButton) {
            Intent intent = new Intent(this, StartActivity.class);
            startActivity(intent);
            finish();
        } if(v.getId() == R.id.button){
            Intent scoreScreen = new Intent(this, ScoreActivity.class);
            scoreScreen.putExtra("scoreTextView", "" + score);
            startActivity(scoreScreen);
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game, menu);
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
