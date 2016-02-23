package com.allen.teachingaid.fragment;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.allen.teachingaid.App;
import com.allen.teachingaid.R;
import com.allen.teachingaid.adapter.CourseItemAdapter;
import com.allen.teachingaid.config.Urls;
import com.allen.teachingaid.entity.JCourse;
import com.allen.teachingaid.view.DividerItemDecoration;
import com.allen.teachingaid.volley.VolleyManager;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.socks.library.KLog;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CourseFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    public static final String TAG = "CourseFragment";
    @Bind(R.id.course_list_rcview)
    RecyclerView mCourseListRcview;
    @Bind(R.id.swiperefreshlayout)
    SwipeRefreshLayout mSwiperefreshlayout;

    static CourseFragment fragment = null;
    private ProgressDialog pDialog;
    private CourseItemAdapter mCourseItemAdapter;

    public CourseFragment() {
    }

    public static CourseFragment newInstance() {
        if (fragment == null) {
            fragment = new CourseFragment();
        }
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void httpGetData() {
        new Handler().post(new Runnable() {

            @Override
            public void run() {
                mSwiperefreshlayout.setRefreshing(true);
            }
        });

        VolleyManager.newInstance().GsonGetRequest(TAG, Urls.COURSE_URL, JCourse.class
                , new Response.Listener<JCourse>() {
            @Override
            public void onResponse(JCourse jCourse) {
                KLog.v("TAG", "ok" + jCourse.getData().getCourse().get(0).getName());
                mCourseItemAdapter.setDataSource(jCourse.getData().getCourse());
                //  hideDialog();
                mCourseItemAdapter.notifyDataSetChanged();
                if (mSwiperefreshlayout != null)
                    mSwiperefreshlayout.setRefreshing(false);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                KLog.v("TAG", error.getMessage(), error);
                KLog.v("TAG", error.getNetworkTimeMs());
                KLog.v("TAG", error.toString());
                mSwiperefreshlayout.setRefreshing(false);
            }
        });
    }

//    private void hideDialog() {
//        if (pDialog != null) {
//            pDialog.dismiss();
//            pDialog = null;
//        }
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_course, container, false);
        ButterKnife.bind(this, view);
        // Inflate the layout for this fragment

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSwiperefreshlayout.setColorSchemeResources(
                R.color.colorPrimary,
                R.color.colorPrimaryDark,
                R.color.colorAccent

        );
        mSwiperefreshlayout.setOnRefreshListener(this);

        mCourseListRcview.setLayoutManager(new LinearLayoutManager(App.getContext()));
        mCourseItemAdapter = new CourseItemAdapter(getActivity());
        mCourseListRcview.setAdapter(mCourseItemAdapter);
        mCourseListRcview.addItemDecoration(new DividerItemDecoration(
                getActivity(), DividerItemDecoration.VERTICAL_LIST));


    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        httpGetData();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onPause() {
        super.onPause();

        if (mSwiperefreshlayout != null) {
            mSwiperefreshlayout.setRefreshing(false);
            mSwiperefreshlayout.destroyDrawingCache();
            mSwiperefreshlayout.clearAnimation();
        }
        if (VolleyManager.newInstance() != null) {
            VolleyManager.newInstance().cancel(TAG);
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onRefresh() {
        httpGetData();
    }

}
