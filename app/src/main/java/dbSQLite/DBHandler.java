package dbSQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vi on 24.06.2015.
 */
public class DBHandler extends SQLiteOpenHelper{

    private static final String TAG = DBHandler.class.getSimpleName();
    private SQLiteDatabase db;

    //database name
    private static final String DATABASE_NAME = "highscoreList.db";
    //database version
    private static final int DATABASE_VERSION = 1;
    //table name
    private static final String TABLE_NAME = "scoresTable";

    //column names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_SCORE = "score";

    //queries
    private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+" ( "+KEY_ID+" INTEGER PRIMARY KEY,"+KEY_NAME+" TEXT,"+KEY_SCORE+" INTEGER"+")";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS "+TABLE_NAME;


    public DBHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //create table
    @Override
    public void onCreate(SQLiteDatabase db){
        Log.d("table", "table created");
        db.execSQL(CREATE_TABLE);
    }

    //upgrade table
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        Log.d(TAG, "Upgrade from version: " + oldVersion + " to " + newVersion);
        //drop old table
        db.execSQL(DROP_TABLE);
        //create new table
        onCreate(db);
    }

    //add new score
    public void addScore(Score score){
        long rows = -1;

        try{
            db = getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(KEY_NAME, score.getName());
            values.put(KEY_SCORE, score.getScore());

            rows = db.insert(TABLE_NAME, null, values);

        } catch ( SQLiteException e) {
            Log.e(TAG, "insert error 1",e);
        } catch (Exception e) {
            Log.e(TAG, "insert error 2",e);
        } finally {
            Log.d(TAG, "inserted: " + rows);
        }
    }

    //get all scores in a list
    public List<Score> getAllScores(){

        SQLiteDatabase db = getWritableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_NAME + " ORDER BY " + KEY_SCORE + " DESC";
        //Query: select all
        Cursor cursor = db.rawQuery(selectQuery, null);

        List<Score> scoreList = new ArrayList<Score>();

        //do-while-loop: go through all rows and add to scoreList
        if (cursor.moveToFirst()){
            do{
                Score score = new Score();
                score.setID(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                score.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
                score.setScore(cursor.getInt(cursor.getColumnIndex(KEY_SCORE)));
                scoreList.add(score);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return scoreList;
    }

    //drop table and create new one -- unused
    public void clearTable(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(DROP_TABLE);
        db.execSQL(CREATE_TABLE);
    }

    //update one score where name is the same -- unused
    public int updateScore(Score score){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, score.getName());
        values.put(KEY_SCORE, score.getScore());

        return db.update(TABLE_NAME, values, KEY_NAME + "=?", new String[]{String.valueOf(score.getName())});
    }

    //delete score where name is the same -- unused
    public void deleteScore(Score score){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, KEY_NAME + "=?", new String[]{String.valueOf(score.getName())});
        db.close();
    }
}
