package com.lidiwo.weexdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * *****************************************************
 *
 * @author：lidi
 * @date：2018/8/27 15:08
 * @Company：深圳思创远大企业管理咨询有限公司
 * @Description： *****************************************************
 */
public class ScanHistoryActivity extends AppCompatActivity implements View.OnClickListener {

    private List<String> mShowDatas = new ArrayList<>();

    private MyAdapter mAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_history);
        ListView lv_content = findViewById(R.id.lv_content);
        ImageView tv_back = findViewById(R.id.tv_back);
        ImageView iv_delete = findViewById(R.id.iv_delete);
        tv_back.setOnClickListener(this);
        iv_delete.setOnClickListener(this);

        String scanHistory = SPUtils.getString(this, SPUtils.SCANHISTORY, "");
        if (!TextUtils.isEmpty(scanHistory)) {
            String[] historys = scanHistory.split("@");
            if (historys.length > 0) {
                mShowDatas.addAll(Arrays.asList(historys));
            }
        }
        lv_content.setAdapter(mAdapter = new MyAdapter());
        lv_content.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String history = mShowDatas.get(position);
                Toast.makeText(ScanHistoryActivity.this, history, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ScanHistoryActivity.this, WeexActivity.class);
                intent.putExtra("weex_url", history);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.iv_delete:
                SPUtils.putString(this, SPUtils.SCANHISTORY, "");
                mShowDatas.clear();
                mAdapter.notifyDataSetChanged();
                break;
        }
    }

    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mShowDatas.size();
        }

        @Override
        public String getItem(int position) {
            return mShowDatas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = View.inflate(ScanHistoryActivity.this, R.layout.item_history, null);
                holder = new ViewHolder();
                holder.tv_title = convertView.findViewById(R.id.tv_title);
                holder.tv_subtitle = convertView.findViewById(R.id.tv_subtitle);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            String title = mShowDatas.get(position);
            holder.tv_title.setText(title);
            holder.tv_subtitle.setText(title);
            return convertView;
        }
    }

    private class ViewHolder {
        private TextView tv_title;
        private TextView tv_subtitle;

    }

}
