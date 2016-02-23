package com.allen.teachingaid.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.allen.teachingaid.R;
import com.allen.teachingaid.adapter.StuItemAdapter;
import com.allen.teachingaid.ui.widget.DividerItemDecoration;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AskActivity extends BaseActivity {

    public static String course_id;
    @Bind(R.id.list_stu)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stu_list);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String course_id = bundle.getString("course_id");
        String course_name = bundle.getString("course_name");
        getSupportActionBar().setTitle(course_name);
        //根据course_id获取此课程的所有学生

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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
    }
}
