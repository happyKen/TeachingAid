package com.allen.teachingaid.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.allen.teachingaid.R;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

public class StuItemAdapter extends RecyclerView.Adapter<StuItemAdapter.ViewHolder> {

    @Bind(R.id.imageview)
    ImageView mImageview;
    @Bind(R.id.contentview)
    TextView mContentview;
    @Bind(R.id.container)
    LinearLayout mContainer;

    private String[] mStu = {"林启南", "AllenLin", "林启南", "AllenLin", "林启南", "AllenLin", "林启南",
            "AllenLin", "林启南", "AllenLin", "林启南", "AllenLin", "林启南", "AllenLin"};
    Context mContext;
    SparseBooleanArray mItemStates = new SparseBooleanArray();

    public StuItemAdapter(Context context) {
        this.mContext = context;
        //初始化
        for (int i = 0; i < mStu.length; i++) {
            mItemStates.put(i, false);
        }
    }

    /**
     * @return 学生签到状态：0为到，1为没到
     */
    public SparseBooleanArray getStuStates() {
        return mItemStates;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_stu_item, parent, false);
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
        holder.mContentView.setText(mStu[position]);

        holder.container.setTag(position);

        //由保存的数据来控制视图，防止viewholder复用而错位
        if (mItemStates.get(position) == false) {
            //   mItemStates.put(position, true);
            holder.mContentView.setBackgroundColor(mContext.getResources().getColor(R.color.color_white));
        } else {
            holder.mContentView.setBackgroundColor(mContext.getResources().getColor(R.color.colorPrimary));
//            mItemStates.put(position, false);
        }
//        holder.container.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //点击设置字体为红色
//                holder.mContentView.setTextColor(mContext.getResources().getColor(R.color.color_red));
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return mStu == null ? 0 : mStu.length;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.imageview)
        ImageView mImageView;
        @Bind(R.id.contentview)
        TextView mContentView;
        @Bind(R.id.container)
        LinearLayout container;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            //监听item
            container.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int position = (int) container.getTag();
                            if (mItemStates.get(position) == false) {
                                mItemStates.put(position, true);
                                mContentView.setBackgroundColor(mContext.getResources().getColor(R.color.colorPrimary));
                            } else {
                                mContentView.setBackgroundColor(mContext.getResources().getColor(R.color.color_white));
                                mItemStates.put(position, false);
                            }
                        }
                    }

            );
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
