package com.cniao5.demo.mvvm.viewmodel;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

/**
 * <p>Description:
 *
 * @author xzhang
 */

public class MainViewModel {

    private String userName ;
    private String pwd ;

    private Context mContext ;

    public MainViewModel(Context context){
        this.mContext = context ;
    }

    public TextWatcher userNameChangeListener(){
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                userName = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        } ;
    }

    public TextWatcher pwdChangeListener(){
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                pwd = s.toString() ;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        } ;
    }



    public void login(View view){
        if(!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(pwd)){
            if(userName.equals("zhangsan") && pwd.equals("123456")){

                Toast.makeText(mContext,"登录成功",Toast.LENGTH_SHORT).show();
            }else{

                Toast.makeText(mContext,"登录失败",Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(mContext,"用户名和密码不能为空",Toast.LENGTH_SHORT).show();
        }
    }

}
