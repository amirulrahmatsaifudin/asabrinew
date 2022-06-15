package com.example.asabri.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.view.PreviewView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.example.asabri.R;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AuthenticationBiometricActivity extends AppCompatActivity {
    private Executor executor = Executors.newSingleThreadExecutor();
    private int REQUEST_CODE_PERMISSIONS = 1001;
    private final String[] REQUIRED_PERMISSIONS = new String[]{"android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE"};

    PreviewView NPreviewView;
    ImageView mImageView;
    Button scan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication_biometric);


    }
}