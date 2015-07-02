package com.example.luuviettrinhle.confusion;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class InstructionActivity extends ActionBarActivity {

    public void onButtonClick(View v) {
        if (v.getId() == R.id.playButton) {
            Intent intent = new Intent(this, GameActivity.class);
            startActivity(intent);
            finish();
        } else if (v.getId() == R.id.homeButton) {
            Intent intent = new Intent(this, StartActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruction);

        Typeface bauhausFont = Typeface.createFromAsset(getAssets(), "bauhaus.ttf");
        TextView iTitle = (TextView)findViewById(R.id.instructionTitle);
        iTitle.setTypeface(bauhausFont);

        Typeface arialFont = Typeface.createFromAsset(getAssets(), "arial.ttf");
        TextView iText = (TextView)findViewById(R.id.instructionText);
        iText.setTypeface(arialFont);

    }
}
