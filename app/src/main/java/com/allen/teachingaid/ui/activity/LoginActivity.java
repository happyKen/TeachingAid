package com.allen.teachingaid.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.allen.teachingaid.R;
import com.allen.teachingaid.config.Urls;
import com.allen.teachingaid.entity.JLogin;
import com.allen.teachingaid.volley.VolleyManager;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends Activity {
    private Button btn_login;
    private EditText edt_username;
    private EditText edt_password;
    private UIHandler uiHandler;
    private UIThread uiThread;
    private ProgressBar progressBar;
    private int username;
    private String password;
    private Toast toast;
    public static final String TAG = "LoginActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        findViewByIds();
        setListeners();
        init();

    }

    private void findViewByIds() {
        btn_login = (Button) findViewById(R.id.email_sign_in_button);
        edt_username = (EditText) findViewById(R.id.email);
        edt_password = (EditText) findViewById(R.id.password);
        progressBar =(ProgressBar) findViewById(R.id.login_progress);
    }

    private void setListeners() {
        //登陆监听器
        btn_login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //登陆中界面，待写
                logining();

                //登陆成功跳转
                if (checkLogin() == 1) {
                    Message msg = new Message();
                    Bundle b = new Bundle();// 存放数据
                    b.putInt("cmd", CMD_SUCCESS);
                    msg.setData(b);
                    uiHandler.sendMessage(msg); // 向Handler发送消息,更新UI
                    mySendIntent();
                } else {
                    loginError();
                }


            }
        });
    }

    /*
     * 显示登陆错误信息
     */
    private void loginError() {
        if (checkLogin() == 0) {
            Message msg = new Message();
            Bundle b = new Bundle();// 存放数据
            b.putInt("cmd", CMD_LOGIN_ERROR);
            msg.setData(b);
            uiHandler.sendMessage(msg); // 向Handler发送消息,更新UI

        }
    }

    /*
     * 登陆中
     */
    private void logining() {
        Message msg = new Message();
        Bundle b = new Bundle();// 存放数据
        b.putInt("cmd", CMD_LOGINING);
        msg.setData(b);
        uiHandler.sendMessage(msg); // 向Handler发送消息,更新UI
    }

	/*
	 * 判断密码是否正确，正确1，错误0
	 */

    private int checkLogin() {
        int checkId = 0;
        username = 0;
        if (!edt_username.getText().toString().equals(""))//""不能转型为int,字符串对比要用equals
        {
            //获取用户输入的账号
            username = Integer.parseInt(edt_username.getText().toString().trim());
        }
        //获取用户输入的密码
        password = edt_password.getText().toString();
        //调用登录方法
        if (login(username, password))
            checkId = 1;
        return checkId;
    }

    /*
     * 与服务器连接
     */
    private Boolean login(int username, String password) {
        Map<String,String> map = new HashMap<String,String>();
        map.put("username", String.valueOf(username));
        map.put("password",password);
       VolleyManager.newInstance().GsonPostRequest(TAG, map, Urls.LOGIN_URL, JLogin.class, new Response.Listener<JLogin>() {
           @Override
           public void onResponse(JLogin jLogin) {
               Log.d("111111111111111111111", "ok" + jLogin.getData());

           }
       }, new Response.ErrorListener() {
           @Override
           public void onErrorResponse(VolleyError error) {
               Log.d("111111111111111111111", "okhhhghhh");

           }
       });
        Log.d("22222", "222222");
        return false;
    }

    private void init() {
        uiHandler = new UIHandler();
        toast = new Toast(this);

    }

    private final static int CMD_LOGIN_ERROR = 2000;
    private final static int CMD_LOGINING = 2001;
    private final static int CMD_INPUT = 2002;
    private final static int CMD_SUCCESS = 2003;

    class UIHandler extends Handler {
        public UIHandler() {
        }

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            Log.d("MyHandler", "handleMessage......");
            super.handleMessage(msg);
            Bundle b = msg.getData();
            int vCmd = b.getInt("cmd");
            switch (vCmd) {
                case CMD_LOGINING:
                    progressBar.setVisibility(View.VISIBLE);

                    break;
                case CMD_LOGIN_ERROR:
                    progressBar.setVisibility(View.GONE);
                  Toast.makeText(LoginActivity.this,"登陆失败",Toast.LENGTH_LONG);

                    break;
                case CMD_SUCCESS:
                   progressBar.setVisibility(View.GONE);
                    break;
                default:
                    break;
            }
        }
    }

    ;

    class UIThread implements Runnable {

        @Override
        public void run() {

        }

    }

    public void mySendIntent() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        //new一个Bundle对象，并将要传递的数据传入
        Bundle bundle = new Bundle();
        bundle.putInt("username", username);
        bundle.putString("password", password);
        //将bundle对象assign给Intent
        intent.putExtras(bundle);
        //开启跳转
        startActivity(intent);
    }
}