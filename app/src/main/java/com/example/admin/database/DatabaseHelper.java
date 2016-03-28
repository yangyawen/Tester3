package com.example.admin.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import com.example.admin.models.Memo;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 2016/3/9.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    //
    private final String LOG_TAG = getClass().getSimpleName();

    private static final String DATABASE_NAME = "helloDB.db";

    // any time you make changes to your database objects, you may have to increase the database version
    private static final int DATABASE_VERSION = 1;

    private static DatabaseHelper helper = null;

    private Map<String, Dao> daos = new HashMap<>();
    private Map<String, RuntimeExceptionDao> runtimeDaos = new HashMap<>();
    //DAO:Data Access Objects
    //private Dao<T, Integer> dao = null;
    //private RuntimeExceptionDao<T, Integer> runtimeDao = null;

    private DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This is called when the database is first created. Usually you should call createTable statements here to create
     * the tables that will store your data.
     */
    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        //create database
        try {
            Log.i(LOG_TAG, "Create database: " + DATABASE_NAME);
            TableUtils.createTable(connectionSource, Memo.class);
        } catch (SQLException e) {
            Log.e(LOG_TAG, "Can't create database "+DATABASE_NAME, e);
            throw new RuntimeException(e);
        }

        // 设置初始值
        RuntimeExceptionDao dao = getRuntimeDao(Memo.class);

        Memo memo = new Memo("欢迎来到备忘录","长按可以删除备忘录哦！");
        dao.create(memo);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }

    // DatabaseHelper设置为单例模型
    public static synchronized DatabaseHelper getHelper(Context context){
        if(helper == null){
            helper = new DatabaseHelper(context);
        }
        return helper;
    }

    /**
     * Returns the Database Access Object (DAO) for our Memo class. It will create it or just give the cached
     * value.
     */
    public synchronized Dao getDao(Class clazz) throws SQLException {
        Log.e(LOG_TAG, "getDao");
        Dao dao = null;
        String className = clazz.getSimpleName();

        if (daos.containsKey(className)) {
            dao = daos.get(className);
        } else {
            dao = super.getDao(clazz);
            daos.put(className, dao);
        }

        return dao;
    }

    /**
     * Returns the RuntimeExceptionDao (Database Access Object) version of a Dao for our Memo class. It will
     * create it or just give the cached value. RuntimeExceptionDao only through RuntimeExceptions.
     */
    public RuntimeExceptionDao getRuntimeDao(Class clazz) {
        RuntimeExceptionDao dao = null;
        String className = clazz.getSimpleName();
        Log.e(LOG_TAG, ""+className);
        if (runtimeDaos.containsKey(className)) {
            dao = runtimeDaos.get(className);
        } else {
            dao = super.getRuntimeExceptionDao(clazz);
            runtimeDaos.put(className, dao);
        }

        return dao;

    }

    /**
     * Close the database connections and clear any cached DAOs.
     */
    @Override
    public void close() {
        super.close();
        helper = null;
        daos = null;
        runtimeDaos = null;
        Log.e(LOG_TAG, "DatabaseHelper closed.");
    }
}