package com.example.asabri.activity.registrasi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.example.asabri.R;
import com.example.asabri.activity.LoginActivity;
import com.example.asabri.activity.LoginFaceidActivity;
import com.example.asabri.activity.otp.OtpActivity;

public class RegistrasiSuccesActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi_succes);
        Bundle extras = getIntent().getExtras();
        byte[] byteArray = extras.getByteArray("picture");

        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        ImageView image = (ImageView) findViewById(R.id.viewfoto);
        Button submite11 = (Button) findViewById(R.id.btn_submite11);
        image.setImageBitmap(bmp);

        Intent intent = getIntent();
        String mobile_number = intent.getStringExtra("mobile_number");

        submite11.setOnClickListener(v -> startActivity(new Intent(RegistrasiSuccesActivity.this, OtpActivity.class)));
    }
}