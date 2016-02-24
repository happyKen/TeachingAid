package com.allen.teachingaid.ui.activity;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.allen.teachingaid.R;
import com.allen.teachingaid.ui.fragment.CourseFragment;
import com.allen.teachingaid.ui.fragment.DataFragment;
import com.allen.teachingaid.ui.fragment.HomeworkFragment;
import com.allen.teachingaid.ui.fragment.PersonalFragment;
import com.allen.teachingaid.util.ToastUtil;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.nav_view)
    NavigationView mNavView;
    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    //    String[] menus = new String[]{
//            "Main", "Homework", "Data", "Personal"
//    };
    private List<Fragment> fragments;
    static int fragment_position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setLeftFlingFinish(false);

        mToolbar.setLogo(R.mipmap.ic_launcher);
        //  mToolbar.setSubtitle("Sub title");
        setSupportActionBar(mToolbar);
//        toolbar.setOnMenuItemClickListener(onMenuItemClick);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        mNavView.setNavigationItemSelectedListener(this);
        getFragmentManager().beginTransaction().add
                (R.id.container, CourseFragment.newInstance()).commit();
        mNavView.setCheckedItem(R.id.nav_course);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /**
     * Menu选项更新监听
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        menu.clear();
        MenuInflater menuInflater = getMenuInflater();
        switch (fragment_position) {
            case 0:
                menuInflater.inflate(R.menu.fragment_course, menu);
                break;
            case 1:
                menuInflater.inflate(R.menu.fragment_homework, menu);
                break;
            case 2:
                menuInflater.inflate(R.menu.fragment_data, menu);
                break;
            case 3:
                menuInflater.inflate(R.menu.fragment_personal, menu);
                break;
            default:
                break;
        }

        return true;
    }

    /**
     * menu选项点击监听
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {
            ToastUtil.showShort("添加");
            return true;
        } else if (id == R.id.action_publish) {
            ToastUtil.showShort("发布");
            return true;
        } else if (id == R.id.action_upload) {
            ToastUtil.showShort("上传");
            return true;
        } else if (id == R.id.action_edit) {
            ToastUtil.showShort("编辑");
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    /**
     * 抽屉选项点击监听
     *
     * @param item
     * @return
     */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_course:
                getFragmentManager().beginTransaction().replace
                        (R.id.container, CourseFragment.newInstance()).commit();
                mToolbar.setTitle(item.getTitle());
                fragment_position = 0;
                invalidateOptionsMenu();
                break;
            case R.id.nav_homework:

                getFragmentManager().beginTransaction().replace
                        (R.id.container, HomeworkFragment.newInstance()).commit();
                mToolbar.setTitle(item.getTitle());
                fragment_position = 1;
                invalidateOptionsMenu();
                break;
            case R.id.nav_data:
                getFragmentManager().beginTransaction().replace
                        (R.id.container, DataFragment.newInstance()).commit();
                mToolbar.setTitle(item.getTitle());
                fragment_position = 2;
                invalidateOptionsMenu();
                break;
            case R.id.nav_personal:
                getFragmentManager().beginTransaction().replace
                        (R.id.container, PersonalFragment.newInstance()).commit();
                mToolbar.setTitle(item.getTitle());
                fragment_position = 3;
                invalidateOptionsMenu();
                break;
            default:
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

//    private void selectItem(int id) {
//        //   Fragment fragment = fragments.get(position);
//        FragmentManager manager = getFragmentManager();
//        Fragment fragment = HomeworkFragment.newInstance();
//        manager.beginTransaction().replace(R.id.container, fragment).commit();
//        // title.setText(menus[position]);
////        navigationView.setCheckedItem(id);
////        drawer.closeDrawer(navigationView);
//    }
}
