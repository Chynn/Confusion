package dbSQLite;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.luuviettrinhle.confusion.R;
import com.example.luuviettrinhle.confusion.StartActivity;

import java.util.List;

import dbSQLite.DBHandler;
import dbSQLite.Score;


public class HighscoreActivity extends ActionBarActivity {

    private static String TAG = HighscoreActivity.class.getSimpleName();
    private DBHandler dbHandler;

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
        List<Score> scoreL = dbHandler.getAllScores();

        for (Score score : scoreL) {
            String log = "Id: " + score.getID() + ", Name:" + score.getName() + ", Score: " + score.getScore();
            Log.d("player: ", log);
        }
    }

    public void onButtonClick(View v) {
        if (v.getId() == R.id.backButton) {
            Intent intent = new Intent(this, StartActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
