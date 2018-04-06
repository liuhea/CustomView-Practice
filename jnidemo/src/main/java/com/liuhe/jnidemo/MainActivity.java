package com.liuhe.jnidemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


/**
 * JNI开发流程
 * (1)定义java的接口
 * (2)定义c的接口
 * (3)利用jni的规范让java-c
 *
 * @author liuhe
 */
public class MainActivity extends AppCompatActivity {

    static {
        System.loadLibrary("hello");
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final DataProvider dataProvider = new DataProvider();
        final EditText name = findViewById(R.id.edt_name);
        final EditText pwd = findViewById(R.id.edt_pwd);
        findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int loginCode = dataProvider.login(Integer.valueOf(name.getText().toString()), Integer.valueOf(pwd.getText().toString()));
                if (loginCode > 0) {
                    Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}


