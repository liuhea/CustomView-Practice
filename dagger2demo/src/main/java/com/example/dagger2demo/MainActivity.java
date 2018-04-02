package com.example.dagger2demo;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import javax.inject.Inject;

class MainActivity extends AppCompatActivity {

    /**
     * 标明需要注入的对象
     */
    @Inject
    Person person;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView txtPerson = findViewById(R.id.txt_main_person);

        /**
         * 构造桥梁对象
         */
        MainComponent component = DaggerMainComponent.builder().mainModule(new MainModule()).build();

        /**
         * 注入
         */
        component.inject(this);

        person.name = "liuhe";
        person.age = 18;

        txtPerson.setText(String.format("姓名：%s，年龄：%d", person.name, person.age));
    }
}
