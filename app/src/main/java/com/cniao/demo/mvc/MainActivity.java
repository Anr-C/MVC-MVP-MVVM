package com.cniao.demo.mvc;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText et_username ;
    private EditText et_pwd ;
    private Button btn_login ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_username = (EditText) findViewById(R.id.et_username);
        et_pwd = (EditText) findViewById(R.id.et_pwd);
        btn_login = (Button) findViewById(R.id.btn_login);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = et_username.getText().toString();
                String pwd = et_pwd.getText().toString();
                if(!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(pwd)){
                    login(userName,pwd);
                }else{
                    Toast.makeText(MainActivity.this,"用户名和密码不能为空",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void login(final String userName,final String pwd){

        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(2000);

                if(userName.equals("zhangsan") && pwd.equals("123456")){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                        }
                    });
                }else{
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this,"登录失败",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        }).start();

    }
}
