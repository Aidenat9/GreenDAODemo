package com.tmgg.greendao.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.tmgg.greendao.MigrationHelper;
import com.tmgg.greendao.gen.DaoMaster;
import com.tmgg.greendao.gen.UserDao;

import org.greenrobot.greendao.database.Database;
/**
* 重写onUpgrade ，解决升级错误问题
*/
public class MyOpenHelper extends DaoMaster.OpenHelper {

    public MyOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

//    @Override
//    public void onCreate(Database db) {
//        super.onCreate(db);
//    }

    /**
     * 数据库升级
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        Log.e("greenDAO", "Upgrading schema from version " + oldVersion + " to " + newVersion + " by migrating all tables data");
        //操作数据库的更新 有几个表升级都可以传入到下面
        MigrationHelper.migrate(db,UserDao.class);
    }
}