package com.example.asabri.activity.onboarding;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import com.example.asabri.R;
import com.example.asabri.activity.LoginActivity;

public class OnBoardingFinishActivity extends AppCompatActivity {
private Button start;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding_finish);
        start = findViewById(R.id.btnstart);
        start.setOnClickListener(view -> {
               startActivity(new Intent(OnBoardingFinishActivity.this, LoginActivity.class));
               return ;
           });

    }
}