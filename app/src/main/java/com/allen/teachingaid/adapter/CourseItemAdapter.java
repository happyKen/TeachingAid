package com.allen.teachingaid.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.allen.teachingaid.R;
import com.allen.teachingaid.entity.JCourse.Data.Course;
import com.allen.teachingaid.util.ToastUtil;
import com.allen.teachingaid.volley.VolleyManager;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CourseItemAdapter extends RecyclerView.Adapter<CourseItemAdapter.ViewHolder> {

    // private String[] mTitles = {"手机软件周一班", "信息检索周三、四班", "计算机英语", "嵌入式", "中间件周四班"};
    Context mContext;
    private List<Course> mCourseList;


    public CourseItemAdapter(Context context) {
        this.mContext = context;

    }

    public void setDataSource(List<Course> mCourseList) {
        this.mCourseList = mCourseList;
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
        VolleyManager.newInstance().ImageLoaderRequest(holder.mImageView, "https://d262ilb51hltx0.cloudfront.net/max/800/1*dWGwx6UUjc0tocYzFNBLEw.jpeg",
                R.mipmap.ic_default,R.mipmap.ic_error,150,150);
        holder.mContentView.setText(mCourseList.get(position).getName());


        holder.mListItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showShort(position + mCourseList.get(position).getName());
//                Intent intent = new Intent(mContext, StuListActivity.class);
//                intent.putExtra("course_id", "123321");
//                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mCourseList == null ? 0 : mCourseList.size();
    }

    @OnClick({R.id.imageview, R.id.contentview, R.id.list_item_view})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image:
                break;
            case R.id.contentview:
                break;
            case R.id.list_item_view:

                break;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.imageview)
        ImageView mImageView;
        @Bind(R.id.contentview)
        TextView mContentView;
        @Bind(R.id.list_item_view)
        LinearLayout mListItemView;


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
