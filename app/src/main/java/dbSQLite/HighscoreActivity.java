package dbSQLite;

import android.app.ListActivity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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
    private ArrayList<String> scoresList = new ArrayList<String>();
    private ArrayAdapter<String> adapter = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);

        dbHandler = new DBHandler(this);
        
        //add test scores
        Log.d("insert", "adding name+score");    //test print
        dbHandler.addScore(new Score("Trinh", 20));
        dbHandler.addScore(new Score("Vi", 4));

        Log.d("read", "read all scores");
        scoreL = new ArrayList<Score>();
        scoreL = dbHandler.getAllScores();

        for (int i = 0; i<scoreL.size(); i++){
            scoresList.add(scoreL.get(i).getID() + ". " + " " + scoreL.get(i).getName() + " " + scoreL.get(i).getScore());
        }

        //Collections.sort(scoresList);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, scoresList);
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
