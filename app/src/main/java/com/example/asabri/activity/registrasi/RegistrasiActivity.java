package com.example.asabri.activity.registrasi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.asabri.R;
import com.example.asabri.api.ApiService;
import com.example.asabri.api.RetrofitBuilder;
import com.example.asabri.api.TokenManager;
import com.example.asabri.model.GetResponseToken;

import org.w3c.dom.Text;

import retrofit2.Call;

public class RegistrasiActivity extends AppCompatActivity {
    private EditText etnopensiun,etktp,etttl,etalamat,etkatasandi,ettempat;
    private Button btnregist;
    ProgressDialog load;
    Context mContext;

    private ApiService service;
    private TokenManager tokenManager;
    private Call<GetResponseToken> call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);
        mContext= this;
        initcomponent();
        navigation();

        tokenManager = TokenManager.getInstance(getSharedPreferences("prefs", MODE_PRIVATE));
        if (tokenManager.getToken().getToken() != null) {
            startActivity(new Intent(this, RegistrasiActivity.class));
            finish();
        }
        service = RetrofitBuilder.createService(ApiService.class);
    }


    private void initcomponent(){
        etnopensiun = findViewById(R.id.et_NoPenisun);
        etktp = findViewById(R.id.et_NIK);
        etttl = findViewById(R.id.et_TTL);
        ettempat = findViewById(R.id.et_Tempat);
        etalamat = findViewById(R.id.et_Alamat);
        etkatasandi = findViewById(R.id.et_katasandi);
        btnregist = findViewById(R.id.btn_regist);

    }

    private void navigation(){
        btnregist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                load = ProgressDialog.show(mContext, null, "Harap Tunggu...", true, false);
                requestRegister();
            }
        });
    }

    private  void  requestRegister(){
        String nopensiun = etnopensiun.getText().toString();
        String ktp = etktp.getText().toString();
        String ttl = etttl.getText().toString();
        String alamat = etalamat.getText().toString();
        String katasandi= etkatasandi.getText().toString();
        String tempat= ettempat.getText().toString();

        Intent intent = new Intent(RegistrasiActivity.this, Registrasi2Activity.class);
        intent.putExtra("nopensiun", nopensiun);
        intent.putExtra("ktp", ktp);
        intent.putExtra("ttl", ttl);
        intent.putExtra("tempat", tempat);
        intent.putExtra("alamat", alamat);
        intent.putExtra("katasandi", katasandi);
        startActivity(intent);
    }
}
