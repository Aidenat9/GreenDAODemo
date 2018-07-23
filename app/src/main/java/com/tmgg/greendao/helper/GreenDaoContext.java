package com.tmgg.greendao.helper;

import android.content.Context;
import android.content.ContextWrapper;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import java.io.File;

/**
 * Created by Doraemon
 * Date: 16/5/12
 * Time: 09:22
 * Summary:该类主要用于基于GreenDao框架自定义数据库路径
 */
public class GreenDaoContext extends ContextWrapper {

    private String currentUserId;
    private Context mContext;

    public GreenDaoContext(Context base, String currentUserId) {
        super(base);
        this.currentUserId = currentUserId;
        this.mContext = base;
    }

    /**
     * 获得数据库路径，如果不存在，则创建对象
     *
     * @param dbName
     */
    @Override
    public File getDatabasePath(String dbName) {
        File baseFile = new File(Environment.getExternalStorageDirectory()+File.separator+"greendaoDB");
        Log.e("路径：",baseFile.getAbsolutePath());
        if(!baseFile.exists())baseFile.mkdir();
        StringBuffer buffer = new StringBuffer();
        buffer.append(baseFile.getPath());
        buffer.append(File.separator);
        buffer.append(currentUserId);
        File tempDir = new File(buffer.toString());
        if(!tempDir.exists())tempDir.mkdir();

        buffer.append(File.separator);
        buffer.append(dbName);
        return new File(buffer.toString());
    }

    /**
     * 重载这个方法，是用来打开SD卡上的数据库的，android 2.3及以下会调用这个方法。
     *
     * @param name
     * @param mode
     * @param factory
     */
    @Override
    public SQLiteDatabase openOrCreateDatabase(String name, int mode,
                                               SQLiteDatabase.CursorFactory factory) {
        SQLiteDatabase result = SQLiteDatabase.openOrCreateDatabase(getDatabasePath(name), factory);
        return result;
    }

    /**
     * Android 4.0会调用此方法获取数据库。
     *
     * @param name
     * @param mode
     * @param factory
     * @param errorHandler
     * @see android.content.ContextWrapper#openOrCreateDatabase(java.lang.String, int,
     * android.database.sqlite.SQLiteDatabase.CursorFactory,
     * android.database.DatabaseErrorHandler)
     */
    @Override
    public SQLiteDatabase openOrCreateDatabase(String name, int mode, SQLiteDatabase.CursorFactory factory,
                                               DatabaseErrorHandler errorHandler) {
        SQLiteDatabase result = SQLiteDatabase.openOrCreateDatabase(getDatabasePath(name), factory);

        return result;
    }

}
