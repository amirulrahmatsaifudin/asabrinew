package com.example.asabri.activity.registrasi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.asabri.R;
import com.example.asabri.api.ApiService;
import com.example.asabri.api.RetrofitBuilder;
import com.example.asabri.api.TokenManager;
import com.example.asabri.model.GetResponseToken;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;

public class RegistrasiActivity extends AppCompatActivity {

    private EditText etName, etMobileNumber, etRetrimentNumber, etktp, ettempat, etttl, etalamat, etkatasandi, etkatasandi2;
    private Button btnregist;
    Calendar myCalendar;
    DatePickerDialog.OnDateSetListener date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);
        initComponent();
        requestRegister();
    }


    private void initComponent() {
        etName = findViewById(R.id.edit_text_name);
        etMobileNumber = findViewById(R.id.edit_text_mobile_number);
        etMobileNumber.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
        etRetrimentNumber = findViewById(R.id.edit_text_retirement_number);
        etktp = findViewById(R.id.et_NIK);
        etttl = findViewById(R.id.et_TTL);
        datapikerdate();
        ettempat = findViewById(R.id.et_Tempat);
        etalamat = findViewById(R.id.et_Alamat);
        etkatasandi = findViewById(R.id.et_katasandi);

        etkatasandi2 = findViewById(R.id.et_katasandi2);
        btnregist = findViewById(R.id.btn_regist);
    }
    private  void  requestRegister() {
        btnregist.setOnClickListener(v -> {
            String name = etName.getText().toString();
            String mobile_number = etMobileNumber.getText().toString();
            String retirement_number = etRetrimentNumber.getText().toString();
            String nik_number = etktp.getText().toString();
            String date_of_birth = etttl.getText().toString();
            String address = etalamat.getText().toString();
            String password_confirmation = etkatasandi2.getText().toString();
            String password = etkatasandi.getText().toString();
            String place_of_birth= ettempat.getText().toString();

            Intent intent = new Intent(RegistrasiActivity.this,Registrasi3Activity.class);
            intent.putExtra("name", name);
            intent.putExtra("mobile_number", mobile_number);
            intent.putExtra("retirement_number", retirement_number);
            intent.putExtra("nik_number", nik_number);
            intent.putExtra("date_of_birth", date_of_birth);
            intent.putExtra("place_of_birth", place_of_birth);
            intent.putExtra("address", address);
            intent.putExtra("password", password);
            intent.putExtra("password_confirmation", password_confirmation);
            startActivity(intent);
        });
    }
    private void datapikerdate() {
        myCalendar = Calendar.getInstance();
        date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                TextView tanggal = findViewById(R.id.et_TTL);
                String myFormat = "dd-MMMM-yyyy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                tanggal.setText(sdf.format(myCalendar.getTime()));
            }
        };
        etttl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(RegistrasiActivity.this, date,
                        myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }
}

