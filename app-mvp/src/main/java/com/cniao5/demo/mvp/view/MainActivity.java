package com.cniao5.demo.mvp.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cniao5.demo.mvp.R;
import com.cniao5.demo.mvp.model.User;
import com.cniao5.demo.mvp.presenter.MainPresenterImpl;

public class MainActivity extends AppCompatActivity  implements MainBaseView{

    private EditText et_username ;
    private EditText et_pwd ;
    private Button btn_login ;

    private MainPresenterImpl mainPresenterImp ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_username = (EditText) findViewById(R.id.et_username);
        et_pwd = (EditText) findViewById(R.id.et_pwd);
        btn_login = (Button) findViewById(R.id.btn_login);

        mainPresenterImp = new MainPresenterImpl();
        mainPresenterImp.attachView(this);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User(et_username.getText().toString(),et_pwd.getText().toString());
                mainPresenterImp.login(user);
            }
        });
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginSuccess(String msg) {
        showToast(msg);
    }

    @Override
    public void loginFailed(String msg) {
        showToast(msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPresenterImp.detachView();
    }
}
