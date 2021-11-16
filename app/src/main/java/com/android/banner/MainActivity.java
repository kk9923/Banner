package com.android.banner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        List<String> paths = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            paths.add(Constants.IMAGE_URL[i]);
        }
        Banner banner = findViewById(R.id.banner);
        banner.setList(paths);
    }
}