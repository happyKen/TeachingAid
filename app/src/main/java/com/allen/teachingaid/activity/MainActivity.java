package com.allen.teachingaid.activity;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.allen.teachingaid.R;
import com.allen.teachingaid.fragment.CourseFragment;
import com.allen.teachingaid.fragment.DataFragment;
import com.allen.teachingaid.fragment.HomeworkFragment;
import com.allen.teachingaid.fragment.PersonalFragment;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Toolbar toolbar;
    //    String[] menus = new String[]{
//            "Main", "Homework", "Data", "Personal"
//    };
    private List<Fragment> fragments;
    DrawerLayout drawer;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        getFragmentManager().beginTransaction().add
                (R.id.container, CourseFragment.newInstance()).commit();
        navigationView.setCheckedItem(R.id.nav_course);
//        fragments = new ArrayList<Fragment>();
//        fragments.add(HomeFragment.newInstance("Home"));
//        fragments.add(MercuryFragment.newInstance("Mercury"));
//        fragments.add(VenusFragment.newInstance("Venus"));
//        fragments.add(MarsFragment.newInstance("Mars"));
//        fragments.add(AboutFragment.newInstance("About"));
//        fragments.add(SettingFragment.newInstance("Setting"));
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_course:
                getFragmentManager().beginTransaction().replace
                        (R.id.container, CourseFragment.newInstance()).commit();
                toolbar.setTitle(item.getTitle());
                break;
            case R.id.nav_homework:
                getFragmentManager().beginTransaction().replace
                        (R.id.container, HomeworkFragment.newInstance()).commit();
                toolbar.setTitle(item.getTitle());
                break;
            case R.id.nav_data:
                getFragmentManager().beginTransaction().replace
                        (R.id.container, DataFragment.newInstance()).commit();
                toolbar.setTitle(item.getTitle());
                break;
            case R.id.nav_personal:
                getFragmentManager().beginTransaction().replace
                        (R.id.container, PersonalFragment.newInstance()).commit();
                toolbar.setTitle(item.getTitle());
                break;
            default:
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
//
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