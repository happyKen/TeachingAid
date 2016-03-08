package com.allen.teachingaid.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.allen.teachingaid.R;
import com.allen.teachingaid.entity.JCourse.Data.Course;
import com.allen.teachingaid.ui.activity.AskActivity;
import com.allen.teachingaid.ui.activity.RollcallActivity;
import com.allen.teachingaid.ui.activity.StuListActivity;
import com.allen.teachingaid.volley.VolleyManager;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CourseItemAdapter extends RecyclerView.Adapter<CourseItemAdapter.ViewHolder> implements View.OnClickListener {

    // private String[] mTitles = {"手机软件周一班", "信息检索周三、四班", "计算机英语", "嵌入式", "中间件周四班"};
    Activity activity;
    @Bind(R.id.imageview)
    ImageView mImageview;
    @Bind(R.id.contentview)
    TextView mContentview;
    @Bind(R.id.rollcall_button)
    Button mRollcallButton;
    @Bind(R.id.ask_button)
    Button mAskButton;
    @Bind(R.id.container)
    LinearLayout mContainer;
    private List<Course> mCourseList;

    public CourseItemAdapter(Activity activity) {
        this.activity = activity;
    }
    /**
     * 更新adapter数据
     * @param mCourseList
     */
    public void refresh(List<Course> mCourseList) {
        this.mCourseList = mCourseList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_course_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
//        holder.mItem = mValues.get(position);
//        holder.mIdView.setText(mValues.get(position).id);

        //Picasso.with(mContext).load("http://i.imgur.com/DvpvklR.png").into(holder.mImageView);
//        Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.ic_launcher);
//        holder.mImageView.setImageBitmap(bitmap);

//        Picasso.with(mContext).setIndicatorsEnabled(true);
//        Picasso.with(mContext)
//                .load("http://i.imgur.com/DvpvklR.png")
//                .resize(150, 150)
//                .centerCrop()
//                .placeholder(R.mipmap.ic_launcher)
//                .error(R.mipmap.menu1)
//                .into(holder.mImageView);
        VolleyManager.newInstance().ImageLoaderRequest(holder.mImageView,
                "http://img2.ali213.net/picfile/News/image/2015/08/12/2015081225355305.jpg",
                R.mipmap.ic_default, R.mipmap.ic_error, 170, 170);
        holder.mContentView.setText(mCourseList.get(position).getName());

        holder.mAskButton.setOnClickListener(this);
        holder.mAskButton.setTag(position);
        holder.mRollcallButton.setOnClickListener(this);
        holder.mRollcallButton.setTag(position);
        holder.container.setOnClickListener(this);
        holder.container.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mCourseList == null ? 0 : mCourseList.size();
    }

    @Override
    public void onClick(View v) {
        Bundle bundle = null;
        Intent intent = null;
        switch (v.getId()) {

            case R.id.container:
                //ToastUtil.showShort(v.getTag() + mCourseList.get((int) v.getTag()).getName());

                intent = new Intent(activity, StuListActivity.class);

                bundle = new Bundle();
                bundle.putInt("course_id", mCourseList.get((int) v.getTag()).getId());
                bundle.putString("course_name", mCourseList.get((int) v.getTag()).getName());
                intent.putExtras(bundle);
                activity.startActivity(intent);
                activity.overridePendingTransition(R.anim.slide_right_in,
                        R.anim.slide_left_out);
                break;
            case R.id.rollcall_button:
                intent = new Intent(activity, RollcallActivity.class);

                bundle = new Bundle();
                bundle.putInt("course_id", mCourseList.get((int) v.getTag()).getId());
                bundle.putString("course_name", mCourseList.get((int) v.getTag()).getName());
                intent.putExtras(bundle);
                activity.startActivity(intent);
                activity.overridePendingTransition(R.anim.slide_right_in,
                        R.anim.slide_left_out);
                break;
            case R.id.ask_button:
                intent = new Intent(activity, AskActivity.class);

                bundle = new Bundle();
                bundle.putInt("course_id", mCourseList.get((int) v.getTag()).getId());
                bundle.putString("course_name", mCourseList.get((int) v.getTag()).getName());
                intent.putExtras(bundle);
                activity.startActivity(intent);
                activity.overridePendingTransition(R.anim.slide_right_in,
                        R.anim.slide_left_out);
                break;

        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.imageview)
        ImageView mImageView;
        @Bind(R.id.contentview)
        TextView mContentView;
        @Bind(R.id.container)
        LinearLayout container;
        @Bind(R.id.rollcall_button)
        Button mRollcallButton;
        @Bind(R.id.ask_button)
        Button mAskButton;

        public ViewHolder(View view) {
            super(view);

            //  mView = view;
            ButterKnife.bind(this, view);
//            mIdView = (TextView) view.findViewById(R.id.id);
            //  mImageView = (ImageView) view.findViewById(R.id.image1);
            //  mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
