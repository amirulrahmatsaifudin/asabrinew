package com.example.asabri.activity.otp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asabri.R;
import com.example.asabri.api.ApiService;
import com.example.asabri.api.RetrofitBuilder;
import com.example.asabri.model.DetailOtp;
import com.example.asabri.model.GetResponseToken;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OtpActivity extends AppCompatActivity {

    private EditText etNumberPhone;
    private Button btnSendCode;

    private ApiService service;
    private Call<GetResponseToken> call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        etNumberPhone = findViewById(R.id.et_phone);
        btnSendCode = findViewById(R.id.btn_phone2);

        service = RetrofitBuilder.createService(ApiService.class);

        btnSendCode.setOnClickListener(v -> {
            String number_phone = etNumberPhone.getText().toString();

            call = service.sendOtp(number_phone);
            call.enqueue(new Callback<GetResponseToken>() {
                @Override
                public void onResponse(Call<GetResponseToken> call, Response<GetResponseToken> response) {
                    if (response.isSuccessful()) {
                        DetailOtp detailOtp = response.body().getDetail();
                        Toast.makeText(OtpActivity.this, detailOtp.getMsg(), Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(OtpActivity.this,VerifikasiOtpActivity.class);
                        intent.putExtra("mobile_number", number_phone);
                        intent.putExtra("otp", detailOtp.getMsg());
                        startActivity(intent);
                    }
                }

                @Override
                public void onFailure(Call<GetResponseToken> call, Throwable t) {

                }
            });
        });
    }
}