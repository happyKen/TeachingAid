package com.allen.teachingaid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.allen.teachingaid.R;
import com.allen.teachingaid.adapter.StuItemAdapter;
import com.allen.teachingaid.util.ToastUtil;
import com.allen.teachingaid.view.DividerItemDecoration;

import butterknife.Bind;
import butterknife.ButterKnife;

public class StuListActivity extends AppCompatActivity {

    public static String course_id;
    @Bind(R.id.list_stu)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stu_list);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        course_id = intent.getStringExtra("course_id");
        ToastUtil.showShort(course_id);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new StuItemAdapter(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(
                this, DividerItemDecoration.VERTICAL_LIST));

        showDialog();
        initData();

    }

    private void showDialog() {
    }

    private void initData() {

    }
}
