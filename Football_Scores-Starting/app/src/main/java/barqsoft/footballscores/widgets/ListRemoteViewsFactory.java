package barqsoft.footballscores.widgets;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Binder;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.util.ArrayList;
import java.util.List;

import barqsoft.footballscores.R;
import barqsoft.footballscores.Util.Utilies;
import barqsoft.footballscores.provider.DatabaseContract;

/**
 * Created by mhyousuf on 12/21/2015.
 */
public class ListRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    List mCollection = new ArrayList();
    Context mContext = null;
    private Cursor data = null;

    private static final String[] SCORE_COLUMNS = {
            DatabaseContract.scores_table.TIME_COL,
            DatabaseContract.scores_table.HOME_COL,
            DatabaseContract.scores_table.AWAY_COL,
            DatabaseContract.scores_table.HOME_GOALS_COL,
            DatabaseContract.scores_table.AWAY_GOALS_COL,
            DatabaseContract.scores_table.MATCH_ID,
            DatabaseContract.scores_table.LEAGUE_COL
    };

    public static final int TIME_COL = 0;
    public static final int HOME_COL = 1;
    public static final int AWAY_COL = 2;
    public static final int HOME_GOALS_COL = 3;
    public static final int AWAY_GOALS_COL = 4;


    public ListRemoteViewsFactory(Context context, Intent intent){
        mContext = context;
    }

    @Override
    public void onCreate() {
        //initData();
    }

    @Override
    public void onDataSetChanged() {
        //initData();
        if (data != null) {
            data.close();
        }

        final long identityToken = Binder.clearCallingIdentity();
        Uri dateUri = DatabaseContract.scores_table.buildScoreWithDate();
        String[] date = new String[1];
        date[0] = Utilies.getTodaysDate();
        data = mContext.getContentResolver().query(dateUri, SCORE_COLUMNS, null, date, null);
        Binder.restoreCallingIdentity(identityToken);

    }

    @Override
    public void onDestroy() {
        if (data != null) {
            data.close();
            data = null;
        }
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.getCount();
    }

    @Override
    public RemoteViews getViewAt(int position) {

        data.moveToPosition(position);

        String dateMatch = data.getString(TIME_COL);
        String homeName = data.getString(HOME_COL);
        String awayName = data.getString(AWAY_COL);
        int homeGoals = data.getInt(HOME_GOALS_COL);
        int awayGoals = data.getInt(AWAY_GOALS_COL);


        RemoteViews remoteViews = new RemoteViews(mContext.getPackageName(), R.layout.widget_list_item);

        remoteViews.setTextViewText(R.id.match_time, dateMatch);
        remoteViews.setTextViewText(R.id.home_name, homeName);
        remoteViews.setTextViewText(R.id.away_name, awayName);

        if(homeGoals != -1 && awayGoals != -1) {
            remoteViews.setTextViewText(R.id.home_score, String.valueOf(homeGoals));
            remoteViews.setTextViewText(R.id.away_score, String.valueOf(awayGoals));
        }
        else {
            remoteViews.setTextViewText(R.id.home_score, "--");
            remoteViews.setTextViewText(R.id.away_score, "--");
        }

        //remoteViews.setTextColor(R.id.text1, Color.BLACK);

        final Intent fillInIntent = new Intent();


        Uri scoreUri = DatabaseContract.scores_table.buildScoreWithDate();
        fillInIntent.setData(scoreUri);
        remoteViews.setOnClickFillInIntent(R.id.widget_list_item, fillInIntent);

        return remoteViews;
    }

    @Override
    public RemoteViews getLoadingView() {
        return new RemoteViews(mContext.getPackageName(), R.layout.widget_list_item);
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}
