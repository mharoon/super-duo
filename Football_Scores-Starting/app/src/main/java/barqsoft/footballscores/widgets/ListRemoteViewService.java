package barqsoft.footballscores.widgets;

import android.content.Intent;
import android.widget.RemoteViewsService;

/**
 * Created by mhyousuf on 12/21/2015.
 */
public class ListRemoteViewService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {

        ListRemoteViewsFactory viewsFactory = new ListRemoteViewsFactory(getApplicationContext(),intent);
        return viewsFactory;
    }
}
