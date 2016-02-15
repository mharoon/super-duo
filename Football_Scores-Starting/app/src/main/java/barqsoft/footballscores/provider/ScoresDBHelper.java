package barqsoft.footballscores.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by yehya khaled on 2/25/2015.
 */
public class ScoresDBHelper extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME = "Scores.db";
    private static final int DATABASE_VERSION = 2;
    public ScoresDBHelper(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        final String CreateScoresTable = "CREATE TABLE " + DatabaseContract.SCORES_TABLE + " ("
                + DatabaseContract.scores_table._ID + " INTEGER PRIMARY KEY,"
                + DatabaseContract.scores_table.DATE_COL + " TEXT NOT NULL,"
                + DatabaseContract.scores_table.TIME_COL + " INTEGER NOT NULL,"
                + DatabaseContract.scores_table.HOME_COL + " TEXT NOT NULL,"
                + DatabaseContract.scores_table.AWAY_COL + " TEXT NOT NULL,"
                + DatabaseContract.scores_table.LEAGUE_COL + " INTEGER NOT NULL,"
                + DatabaseContract.scores_table.HOME_GOALS_COL + " TEXT NOT NULL,"
                + DatabaseContract.scores_table.AWAY_GOALS_COL + " TEXT NOT NULL,"
                + DatabaseContract.scores_table.MATCH_ID + " INTEGER NOT NULL,"
                + DatabaseContract.scores_table.MATCH_DAY + " INTEGER NOT NULL,"
                + " UNIQUE ("+ DatabaseContract.scores_table.MATCH_ID+") ON CONFLICT REPLACE"
                + " );";
        db.execSQL(CreateScoresTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        //Remove old values when upgrading.
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.SCORES_TABLE);
    }
}