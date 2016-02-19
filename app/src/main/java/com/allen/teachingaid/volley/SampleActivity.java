package com.allen.teachingaid.volley;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.allen.teachingaid.R;
import com.allen.teachingaid.config.Urls;
import com.allen.teachingaid.entity.TaCourse;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.socks.library.KLog;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Allen Lin on 2016/02/17.
 */
public class SampleActivity extends AppCompatActivity {
    public static final String TAG = "SampleActivity";
    public static final String mJsonUrl = "http://www.mocky.io/v2/56c33f991200002d3773f261";
    public static final String mImageUrl = "https://d262ilb51hltx0.cloudfront.net/max/800/1*dWGwx6UUjc0tocYzFNBLEw.jpeg";

    @Bind(R.id.textview)
    TextView mTextview;
    @Bind(R.id.imageview)
    ImageView mImageview;
    @Bind(R.id.circleimageview)
    CircleImageView mCircleimageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);
        ButterKnife.bind(this);
        getSupportActionBar().setTitle("volley example");
        getJson();
        getImage();
        getCircleImage();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (VolleyManager.newInstance() != null) {
            VolleyManager.newInstance().cancel(TAG);
        }

    }

    private void getJson() {

//        VolleyManager.newInstance().GsonGetRequest(mJsonUrl, Person.class, new Listener<Person>() {
//            @Override
//            public void onResponse(Person person) {
//                KLog.v(TAG, person.toString());
//                mTextview.setText("first_name: " + person.getFirst_name() + "\n"
//                        + "last_name: " + person.getLast_name() + "\n" +
//                        "gender: " + person.getGender());
//            }
//        }, new ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                KLog.v(TAG, error.getMessage());
//            }
//        });
        VolleyManager.newInstance().GsonGetRequest(Urls.COURSE_URL, TaCourse.class, new Listener<TaCourse>() {
            @Override
            public void onResponse(TaCourse taCourse) {
                KLog.v(TAG, taCourse.getInfo());

                KLog.v(TAG, taCourse.getData().getCourse());
                KLog.v(TAG, taCourse.getData().getCourse().get(0));
                KLog.v(TAG, taCourse.getData().getCourse().get(0).getName());
                KLog.v(taCourse.getData().getCourse().get(1).getName());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                KLog.v(TAG, error.getMessage());
            }
        });
    }

    private void getImage() {
        VolleyManager.newInstance().ImageLoaderRequest
                (mImageview, mImageUrl, R.mipmap.ic_default, R.mipmap.ic_error);
    }

    private void getCircleImage() {

        mCircleimageview.setImageResource(R.mipmap.ic_default);
        VolleyManager.newInstance().ImageRequest(mImageUrl, new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        mCircleimageview.setImageBitmap(bitmap);
                    }
                }, 0, 0, ImageView.ScaleType.CENTER_INSIDE, null,
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        mCircleimageview.setImageResource(R.mipmap.ic_error);
                    }
                });
    }
}
