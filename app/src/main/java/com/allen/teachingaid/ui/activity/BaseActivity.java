package com.allen.teachingaid.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

import com.allen.teachingaid.util.PhoneUtils;

/**
 * Created by Allen Lin on 2016/02/23.
 */
public abstract class BaseActivity extends AppCompatActivity {
    private boolean isExit = false; // 双击返回键退出标记
    private boolean isLeftFlingFinish = true; //默认左滑可以finish掉Activity
    private GestureDetectorCompat mGestureDetector;//手势检测

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.v(this.getClass().getSimpleName(), ">>>onCreate");
        mGestureDetector = new GestureDetectorCompat(getApplicationContext(), new MyGestureListener());
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.v(this.getClass().getSimpleName(), ">>>onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v(this.getClass().getSimpleName(), ">>>onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v(this.getClass().getSimpleName(), ">>>onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v(this.getClass().getSimpleName(), ">>>onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.v(this.getClass().getSimpleName(), ">>>onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v(this.getClass().getSimpleName(), ">>>onDestroy");
    }

    public Activity getActivity() {
        return this;
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (getLeftFlingFinishEnable()) {
            mGestureDetector.onTouchEvent(event);//遇到左滑动作就会finish
        }
        return super.dispatchTouchEvent(event);
    }

    public boolean getLeftFlingFinishEnable() {
        return isLeftFlingFinish;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    /**
     * 设置Activity界面上左滑滑动是否finish掉Activity
     *
     * @param isLeftFlingFinish
     */
    public void setLeftFlingFinish(boolean isLeftFlingFinish) {
        this.isLeftFlingFinish = isLeftFlingFinish;
    }

    /**
     * 手势监听器
     */
    private class MyGestureListener implements GestureDetector.OnGestureListener {
        private float xLeftEdgeMinDistance;
        private float xMinDistance;
        private float yMinDistance;

        public MyGestureListener() {
            DisplayMetrics dm = PhoneUtils.getDisplayMetrics(getActivity());
            xLeftEdgeMinDistance = dm.widthPixels / 15.0f;
            xMinDistance = dm.widthPixels / 20.f;
            yMinDistance = dm.heightPixels / 30.f;
        }

        @Override
        public boolean onDown(MotionEvent e) {
            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {

        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float x = e2.getX() - e1.getX();
            float y = e2.getY() - e1.getY();
            if (x > xMinDistance && y < yMinDistance && y > -yMinDistance) {
                finish();
            }
            return false;
        }
    }
}
