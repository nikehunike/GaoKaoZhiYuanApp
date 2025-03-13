package com.swufe.scoresforapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class SchoolDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_detail);

        String schoolName = getIntent().getStringExtra("school_name");  // 获取传递过来的学校名称

        // 显示学校名称
        TextView schoolNameTextView = findViewById(R.id.school_name_textview);
        schoolNameTextView.setText(schoolName);
    }
}
