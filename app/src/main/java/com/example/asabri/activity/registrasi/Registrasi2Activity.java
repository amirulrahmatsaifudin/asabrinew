package com.example.asabri.activity.registrasi;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.example.asabri.R;

public class Registrasi2Activity extends AppCompatActivity {
    private EditText etnopensiun1,etktp1,etttl1,etalamat1,etkatasandi1,ettempat1;
    private Button btnregist1;
    Context nContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi2);
        nContext= this;
        initcomponent1();
        getValue();
    }

    private void initcomponent1(){
        etnopensiun1 = findViewById(R.id.et_NoPenisun1);
        etktp1 = findViewById(R.id.et_NIK1);
        etttl1 = findViewById(R.id.et_TTL1);
        ettempat1 = findViewById(R.id.et_Tempat1);
        etalamat1 = findViewById(R.id.et_Alamat1);
        etkatasandi1 = findViewById(R.id.et_katasandi1);
        btnregist1 = findViewById(R.id.btn_regist1);
    }

    private void getValue() {
        Intent intent = getIntent();
        String nopensiun = intent.getStringExtra("nopensiun");
        String ktp = intent.getStringExtra("ktp");
        String ttl = intent.getStringExtra("ttl");
        String tempat= intent.getStringExtra("tempat");
        String alamat = intent.getStringExtra("alamat");
        String katasandi = intent.getStringExtra("katasandi");

        String nopensiun1 = etnopensiun1.getText().toString();
        String ktp1 = etktp1.getText().toString();
        String ttl1 = etttl1.getText().toString();
        String alamat1 = etalamat1.getText().toString();
        String katasandi1 = etkatasandi1.getText().toString();
        String tempat1 = ettempat1.getText().toString();

        Intent intentToNextRegister3 = new Intent(Registrasi2Activity.this, Registrasi3Activity.class);
        intent.putExtra("nopensiun", nopensiun);
        intent.putExtra("ktp", ktp);
        intent.putExtra("ttl", ttl);
        intent.putExtra("tempat", tempat);
        intent.putExtra("alamat", alamat);
        intent.putExtra("katasandi", katasandi);
        intent.putExtra("nopensiun1", nopensiun1);
        intent.putExtra("ktp1", ktp1);
        intent.putExtra("ttl1", ttl1);
        intent.putExtra("tempat1", tempat1);
        intent.putExtra("alamat1", alamat1);
        intent.putExtra("katasandi1", katasandi1);
        startActivity(intentToNextRegister3);

    }


}