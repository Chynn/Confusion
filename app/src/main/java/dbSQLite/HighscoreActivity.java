package dbSQLite;

import android.app.ListActivity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.luuviettrinhle.confusion.R;
import com.example.luuviettrinhle.confusion.StartActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dbSQLite.DBHandler;
import dbSQLite.Score;


public class HighscoreActivity extends ListActivity {

    //content for listView
    private ArrayList<String> scoresList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);

        Typeface bauhausFont = Typeface.createFromAsset(getAssets(), "bauhaus.ttf");
        TextView tView = (TextView)findViewById(R.id.textView);
        tView.setTypeface(bauhausFont);

        //get score, name and screen
        Intent intent = getIntent();

        String highscore = intent.getStringExtra("scoreTextView");
        String name = intent.getStringExtra("inputNameET");
        String screen = intent.getStringExtra("Screen");

        DBHandler dbHandler = new DBHandler(this);

        //2 = scoreActivity
        if (Integer.parseInt(screen) == 2){
            //add test scores
            Log.d("insert", "adding name+score");
            if (name.isEmpty()) name = "Unknown";
            //add name+score to database
            dbHandler.addScore(new Score(name, Integer.parseInt(highscore)));
        }

        Log.d("read", "read all scores");
        //get all values of database and put in a list
        List<Score> scoreL;
        scoreL = dbHandler.getAllScores();

        //make arrayList with strings
        for (int i = 0; i< scoreL.size(); i++){
            scoresList.add((i+1) + ". " + " " + scoreL.get(i).getName() + "\t\t" + scoreL.get(i).getScore() + " points");
        }

        //connecting rows of listView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, scoresList);
        ListView lv = (ListView)findViewById(android.R.id.list);
        lv.setAdapter(adapter);
    }

    public void onButtonClick(View v) {
        //open startScreen
        if (v.getId() == R.id.backButton) {
            Intent startScreen = new Intent(this, StartActivity.class);
            startActivity(startScreen);
            finish();
        }
    }
}
