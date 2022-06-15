package com.example.asabri.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.example.asabri.R;
import com.example.asabri.activity.registrasi.RegistrasiActivity;
import com.example.asabri.api.ApiService;
import com.example.asabri.api.RetrofitBuilder;
import com.example.asabri.api.TokenManager;
import com.example.asabri.model.GetResponseToken;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private LoginActivity self;
    private EditText etnopensiun, etpassword;
    private Button btnlogin;
    private ImageButton btnfaceid;
    private TextView tvforgot, tvsignup;
    boolean passwordvisable;
    private View mProgressBar;
    private ProgressBar mCycleProgressBar;

    private ApiService service;
    private TokenManager tokenManager;
    private Call<GetResponseToken> call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        self = this;
        initview();
        navigate();
    }

    private void initview() {
        etnopensiun = findViewById(R.id.et_nopenisun);
        etpassword = findViewById(R.id.et_password);
        btnlogin = findViewById(R.id.btn_login);
        btnfaceid = findViewById(R.id.btn_faceid);
        tvforgot = findViewById(R.id.tv_lupapassword);
        tvsignup = findViewById(R.id.tv_signup);
        mProgressBar = findViewById(R.id.progress_bar_login);
        mCycleProgressBar = mProgressBar.findViewById(R.id.progress_bar_cycle);

    }

    private void navigate() {
        tvforgot.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class)));
        tvsignup.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, RegistrasiActivity.class)));
        btnfaceid.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, LoginFaceidActivity.class)));
        btnlogin.setOnClickListener(view -> login());
    }

    private void login() {
        String nopensiun = etnopensiun.getText().toString();
        String password = etpassword.getText().toString();
        tokenManager = TokenManager.getInstance(getSharedPreferences("prefs", MODE_PRIVATE));
        if (tokenManager.getToken().getToken() != null) {
            startActivity(new Intent(this, HomeActivity.class));
            finish();
        }
        service = RetrofitBuilder.createService(ApiService.class);

        mProgressBar.setVisibility(View.VISIBLE);
        mCycleProgressBar.setVisibility(View.VISIBLE);
        call = service.login(nopensiun, password);
        call.enqueue(new Callback<GetResponseToken>() {
            @Override
            public void onResponse(Call<GetResponseToken> call, Response<GetResponseToken> response) {
                Log.w(TAG, "onResponse: " + response);
                if (response.isSuccessful()) {
                    tokenManager.saveToken(response.body().getData());
                    startActivity(new Intent(self, HomeActivity.class));
                    finish();
                }else {
                    Toast.makeText(LoginActivity.this,"Gagal", Toast.LENGTH_SHORT).show();
                }
                mProgressBar.setVisibility(View.GONE);
                mCycleProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<GetResponseToken> call, Throwable t) {
                Log.w(TAG, "onFailure: " + t.getMessage());
                Toast.makeText(self, t.getMessage(), Toast.LENGTH_SHORT).show();
                mProgressBar.setVisibility(View.GONE);
                mCycleProgressBar.setVisibility(View.GONE);
            }
        });
    }


}


