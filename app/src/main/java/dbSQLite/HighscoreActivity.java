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

    private DBHandler dbHandler;
    private List<Score> scoreL;

    //content
    private ArrayList<String> scoresList = new ArrayList<>();
    private ArrayAdapter<String> adapter = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);

        Typeface bauhausFont = Typeface.createFromAsset(getAssets(), "bauhaus.ttf");
        TextView tView = (TextView)findViewById(R.id.textView);
        tView.setTypeface(bauhausFont);

        Intent intent = getIntent();
        Intent intent1 = getIntent();
        Intent intent2 = getIntent();

        String highscore = intent.getStringExtra("scoreTextView");
        String name = intent1.getStringExtra("inputNameET");
        String screen = intent2.getStringExtra("Screen");

        dbHandler = new DBHandler(this);

        //2 = scoreActivity
        if (Integer.parseInt(screen) == 2){
            //add test scores
            Log.d("insert", "adding name+score");
            dbHandler.addScore(new Score(name, Integer.parseInt(highscore)));
        }

        Log.d("read", "read all scores");
        scoreL = new ArrayList<>();
        scoreL = dbHandler.getAllScores();

        for (int i = 0; i<scoreL.size(); i++){
            scoresList.add((i+1) + ". " + " " + scoreL.get(i).getName() + "\t" + scoreL.get(i).getScore());
        }

        //Collections.sort(scoresList);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, scoresList);
        ListView lv = (ListView)findViewById(android.R.id.list);
        lv.setAdapter(adapter);
    }

    public void onButtonClick(View v) {
        if (v.getId() == R.id.backButton) {
            Intent intent = new Intent(this, StartActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
