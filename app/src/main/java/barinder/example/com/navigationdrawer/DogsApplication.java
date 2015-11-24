package barinder.example.com.navigationdrawer;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.weavebytes.dogsapp.model.DaoMaster;
import com.weavebytes.dogsapp.model.DaoSession;

/**
 * Created by WeaveBytes on 11/5/2015.
 */
public class DogsApplication extends Application {
    public DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "Dogs_db", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
