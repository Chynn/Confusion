package dbSQLite;

/**
 * Created by Vi on 24.06.2015.
 */
public class Score {

    int id;
    String name;
    int score;

    public Score(){

    }

    public Score(String name, int score){
        this.name = name;
        this.score = score;
    }

    public Score(int id, String name, int score){
        this.id = id;
        this.name = name;
        this.score = score;
    }

    public int getID(){
        return id;
    }

    public String getName() {
        return name;
    }

    public int getScore(){
        return score;
    }

    public void setID(int id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setScore(int score){
        this.score = score;
    }
}
