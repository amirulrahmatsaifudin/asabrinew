package com.example.asabri.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.asabri.R;

public class HomeActivity extends AppCompatActivity {
    ImageView pengajuan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        pengajuan = findViewById(R.id.pengajuan);
        pengajuan.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this, AuthenticationBiometricActivity.class)));

    }
}