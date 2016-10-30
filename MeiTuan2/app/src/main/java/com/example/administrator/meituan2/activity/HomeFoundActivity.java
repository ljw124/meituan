package com.example.administrator.meituan2.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.meituan2.R;
import com.example.administrator.meituan2.sqlite.DBManager;
import com.example.administrator.meituan2.sqlite.HistoryData;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/19.
 */
public class HomeFoundActivity extends AppCompatActivity {

    private Button search;
    private EditText input;
    private ImageButton back;
    private ImageButton more;
    private GridView mGridView;
    private ListView history;
    private DBManager dbManager;
    private Button delet;
    private List<HistoryData> mHistoryDatas;
    private MyAdapte adapte;
    private HistoryData data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_found);
        initView();
        //给listview设置适配器
        adapte = new MyAdapte();
        history.setAdapter(adapte);
        //初始化DBManager
        try {
            dbManager = new DBManager(this);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //第一次进来时把搜索记录查询出来，bind显示在listview上
        queryAll();

        initData();
    }

    private void initView() {
        search = (Button) findViewById(R.id.btn_home_search);
        input = (EditText) findViewById(R.id.et_home_input);
        back = (ImageButton) findViewById(R.id.ib_home_back);
        more = (ImageButton) findViewById(R.id.ib_home_more);
        mGridView = (GridView) findViewById(R.id.gv_home_hot);
        history = (ListView) findViewById(R.id.lv_home_history);
        delet = (Button) findViewById(R.id.btn_home_delet);
    }

    private void initData() {
        String name = input.getText().toString();
        data = new HistoryData();
        data.setName(name);
        mHistoryDatas = new ArrayList<>();
        mHistoryDatas.add(data);
        //点击搜索按钮，搜索本地数据
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                queryAll();
            }
        });
        //把查询的历史记录保存到数据库
        try {
            dbManager.insertHistoryData(data);
            adapte.notifyDataSetChanged();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //点击删除按钮，删除所以数据
        delet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    dbManager.deleteHistoryData(data);
                    adapte.notifyDataSetChanged();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    public void queryAll(){
        try {
            mHistoryDatas = dbManager.getAllHistoryData();
            adapte.notifyDataSetChanged();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * ListView对应的适配器
     */
    class MyAdapte extends BaseAdapter {

        @Override
        public int getCount() {
            return mHistoryDatas == null ? 0 : mHistoryDatas.size();
        }

        @Override
        public Object getItem(int i) {
            return mHistoryDatas.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            TextView tv = new TextView(HomeFoundActivity.this);
            tv.setText(mHistoryDatas.get(i).getName().toString());
            return tv;
        }
    }
}
