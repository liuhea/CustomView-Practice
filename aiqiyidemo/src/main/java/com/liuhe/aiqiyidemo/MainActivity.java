package com.liuhe.aiqiyidemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.liuhe.aiqiyidemo.bindview.BindView;
import com.liuhe.aiqiyidemo.bindview.BindViewUtils;

/**
 * @author liuhe
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.txt_main_hello)
    TextView txt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BindViewUtils.bindView(this);

        txt.setText("BindView Success");
    }
}
