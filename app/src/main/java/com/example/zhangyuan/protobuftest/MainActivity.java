package com.example.zhangyuan.protobuftest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProtobufTestTask protobufTestTask = new ProtobufTestTask();
                protobufTestTask.execute();
            }
        });

    }
}
