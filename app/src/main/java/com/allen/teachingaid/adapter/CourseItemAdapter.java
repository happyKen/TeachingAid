package com.allen.teachingaid.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.allen.teachingaid.R;
import com.allen.teachingaid.activity.StuListActivity;
import com.allen.teachingaid.util.ToastUtil;
import com.squareup.picasso.Picasso;

public class CourseItemAdapter extends RecyclerView.Adapter<CourseItemAdapter.ViewHolder> {

    private String[] mTitles = {"手机软件周一班", "信息检索周三、四班", "计算机英语", "嵌入式", "中间件周四班"};
    Context mContext;

    public CourseItemAdapter(Context context) {
        this.mContext = context;
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
        Picasso.with(mContext).setIndicatorsEnabled(true);
        Picasso.with(mContext)
                .load("http://i.imgur.com/DvpvklR.png")
                .resize(150, 150)
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.menu1)
                .into(holder.mImageView);
        holder.mContentView.setText(mTitles[position]);


        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showShort(position + mTitles[position]);
                Intent intent = new Intent(mContext, StuListActivity.class);
                intent.putExtra("course_id", "123321");
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mTitles == null ? 0 : mTitles.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView mImageView;
        public final TextView mContentView;


        public ViewHolder(View view) {
            super(view);
            mView = view;
//            mIdView = (TextView) view.findViewById(R.id.id);
            mImageView = (ImageView) view.findViewById(R.id.image1);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
