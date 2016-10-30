package com.example.administrator.meituan2.sqlite;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2016/10/19.
 */
public class DBManager {

    private final Dao dao;

    public DBManager(Context context) throws SQLException {
        OrmHelper ormHelper = new OrmHelper(context);
//        SQLiteDatabase readableDatabase = ormHelper.getReadableDatabase(); //可以获取一个原生的数据库
        //Dao相当于原生的SQLiteDatabase，可以操作数据库,一个Dao只能操作一张表
        dao = ormHelper.getDao(HistoryData.class);
    }

    /**
     * 插入数据
     * @param history
     * @throws SQLException
     */
    public void insertHistoryData(HistoryData history) throws SQLException {
        //在数据库中创建一条记录，作用与SQLiteDatabase.insert一样
        dao.create(history);
    }

    /**
     * 查询数据
     * @return
     * @throws SQLException
     */
    public List<HistoryData> getAllHistoryData() throws SQLException {
        List<HistoryData> list = dao.queryForAll();
        return list;
    }

    /**
     * 查询某个数据
     * @return
     * @throws SQLException
     */
    public List<HistoryData> getHistoryData(HistoryData history) throws SQLException {

        QueryBuilder queryBuilder = dao.queryBuilder();
        queryBuilder.where().eq("name",history); //多条件查询
        List<HistoryData> query = queryBuilder.query();//此方法相当于build，提交设置
        return query;
    }

    /**
     * 删除数据
     * @param history
     * @throws SQLException
     */
    public void deleteHistoryData(HistoryData history) throws SQLException {
        //只看id
        dao.delete(history);
    }
}
