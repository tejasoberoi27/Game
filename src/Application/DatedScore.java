package Application;

import java.io.Serializable;
import java.util.Date;

public class DatedScore implements Serializable{
    private int score;
    private Date date;

    public DatedScore(int s, Date date) {
        score = s;
        this.date = date;
    }

    public int getScore() {
        return this.score;
    }

    public Date getDate() {
        return this.date;
    }


}
