package com.example.administrator.meituan2.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by Administrator on 2016/10/19.
 */
public class OrmHelper extends OrmLiteSqliteOpenHelper {

    public static final String DB_NAME ="1607.db";
    public static final int DB_VERSION = 1;

    public OrmHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        //建表,和Gson类似，第二个参数即是业务实体类
        try {
            TableUtils.createTable(connectionSource,HistoryData.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        //区分不同的版本做不同的更新
    }
}
