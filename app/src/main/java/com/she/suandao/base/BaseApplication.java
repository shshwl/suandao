package com.she.suandao.base;

import android.database.sqlite.SQLiteDatabase;
import android.support.multidex.MultiDexApplication;

import com.she.suandao.entity.greendao.DaoMaster;
import com.she.suandao.entity.greendao.DaoSession;


/**
 * Description:
 *
 * @author : mrj
 * Date: 2019/10/23
 * Time: 10:40
 */
public class BaseApplication extends MultiDexApplication {

    private static BaseApplication application;
    private DaoSession daoSession;

    public static BaseApplication getInstance() {
        return application;
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
//        initMobClick();
        initGreenDao();

    }

    /**
     * 初始化GreenDao,直接在Application中进行初始化操作
     */
    private void initGreenDao() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "suandao.db");
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }


}
