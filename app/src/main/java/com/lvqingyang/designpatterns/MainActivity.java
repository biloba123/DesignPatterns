package com.lvqingyang.designpatterns;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.lvqingyang.designpatterns.imageloader.ImageLoader;

public class MainActivity extends AppCompatActivity {

    private android.widget.ImageView iv;
    private android.widget.Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.btn = (Button) findViewById(R.id.btn);
        this.iv = (ImageView) findViewById(R.id.iv);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageLoader.getInstance(MainActivity.this)
                        .displayImage("https://pic1.zhimg.com/v2-6398c11793dee0d99af274980c345cb7_b.jpg", iv);
            }
        });

    }
}
