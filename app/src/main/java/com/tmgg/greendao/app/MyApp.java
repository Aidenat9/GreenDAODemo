package com.tmgg.greendao.app;

import android.app.Application;

import com.tmgg.greendao.daomaster.DaoManager;
import com.tmgg.greendao.gen.DaoSession;

/**
 * @author sunwei
 *         邮箱：tianmu19@gmail.com
 *         时间：2018/7/19 9:42
 *         包名：com.tmgg.greendao.app
 *         <p>description:            </p>
 */

public class MyApp extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        DaoManager.getInstance().init(this).setDebug(true);
    }
    public static DaoSession getDaoSession(){
        return DaoManager.getInstance().getDaoSession();
    }

}
