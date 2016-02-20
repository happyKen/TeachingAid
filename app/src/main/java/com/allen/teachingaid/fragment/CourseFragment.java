package com.allen.teachingaid.fragment;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
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

public class CourseFragment extends Fragment {

    static CourseFragment fragment = null;
    @Bind(R.id.course_list_rcview)
    RecyclerView mCourseListRcview;

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
        VolleyManager.newInstance().GsonGetRequest(Urls.COURSE_URL, JCourse.class
                , new Response.Listener<JCourse>() {
            @Override
            public void onResponse(JCourse jCourse) {
                KLog.v("TAG", "ok" + jCourse.getData().getCourse().get(0).getName());
                mCourseItemAdapter.setDataSource(jCourse.getData().getCourse());
                hideDialog();
                mCourseItemAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                KLog.v("TAG", error.getMessage(), error);
                KLog.v("TAG", error.getNetworkTimeMs());
                KLog.v("TAG", error.toString());
                hideDialog();
            }
        });
    }

    private void hideDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_course_list, container, false);
        ButterKnife.bind(this, view);
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        httpGetData();
        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading...");
        pDialog.show();


        mCourseListRcview.setLayoutManager(new LinearLayoutManager(App.getContext()));
        mCourseItemAdapter = new CourseItemAdapter(getActivity());
        mCourseListRcview.setAdapter(mCourseItemAdapter);
        mCourseListRcview.addItemDecoration(new DividerItemDecoration(
                getActivity(), DividerItemDecoration.VERTICAL_LIST));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
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
}
