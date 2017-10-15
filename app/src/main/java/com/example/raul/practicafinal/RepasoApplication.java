package com.example.raul.practicafinal;

import android.app.Application;

import com.example.raul.practicafinal.models.DaoMaster;
import com.example.raul.practicafinal.models.DaoSession;

import org.greenrobot.greendao.database.Database;

/**
 * Created by RAUL on 14/10/2017.
 */

public class RepasoApplication extends Application {

    private DaoSession daoSession;

    @Override public void onCreate() {
        super.onCreate();
        DaoMaster.OpenHelper helper = new DaoMaster.DevOpenHelper(this, "mydb");
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public DaoSession getDaoSesion(){ return daoSession;}


}
