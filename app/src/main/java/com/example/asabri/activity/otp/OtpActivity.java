package com.example.asabri.activity.otp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.asabri.R;
import com.example.asabri.activity.registrasi.Registrasi3Activity;
import com.example.asabri.api.ApiService;
import com.example.asabri.api.RetrofitBuilder;
import com.example.asabri.model.AccessToken;
import com.example.asabri.model.DetailOtp;
import com.example.asabri.model.GetResponseToken;
import com.example.asabri.util.ApiServiceRaw;
import com.example.asabri.util.ServiceGenerator;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OtpActivity extends AppCompatActivity {

    private EditText etNumberPhone;
    private Button btnSendCode;
    private Call<GetResponseToken> call;
    private ApiService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        etNumberPhone = findViewById(R.id.et_phone);
        btnSendCode = findViewById(R.id.btn_phone2);
        service = RetrofitBuilder.createService(ApiService.class);
        Intent intent = getIntent();
        etNumberPhone.setText(intent.getStringExtra("mobile_number"));

        btnSendCode.setOnClickListener(v -> {
            sendPost();
////                                String s = etNumberPhone.getText().toString();
////                    s = s.trim();
//////
//////                    JSONObject jsonParam = new JSONObject();
////                    JSONObject jsonParam = new JSONObject().put("mobile_number", s);
////
//////
////            // Using the Retrofit
////            ApiServiceRaw jsonPostService = ServiceGenerator.createService(ApiServiceRaw.class, "https://api-poc-asabri.picaso.id/api/");
////            Call<JsonObject> call = jsonPostService.sendotp(jsonObject);
////            call.enqueue(new Callback<JsonObject>() {
////
////                @Override
////                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
////                    try {
//////                        Log.e("response-success", response.body().toString());
////                        Intent intentToOtp = new Intent(OtpActivity.this, VerifikasiOtpActivity.class);
////                        intentToOtp.putExtra("mobile_number", mobile);;
////                        startActivity(intentToOtp);
////                    } catch (Exception e) {
////                        e.printStackTrace();
////                    }
////
////                }
////
////                @Override
////                public void onFailure(Call<JsonObject> call, Throwable t) {
////                    Log.e("response-failure", call.toString());
////                }
////            });
        });
        }





//

    public void sendPost() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("https://api-poc-asabri.picaso.id/api/v1/mobile_otp/send_otp_login");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                    conn.setRequestProperty("Accept", "application/json");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);

                    String s = etNumberPhone.getText().toString();
                    s = s.trim();
//
//                    JSONObject jsonParam = new JSONObject();
                    JSONObject jsonParam = new JSONObject().put("mobile_number", s);

//
//                    Log.i("JSON", jsonParam.toString());
                    DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                    //os.writeBytes(URLEncoder.encode(jsonParam.toString(), "UTF-8"));
                    os.writeBytes(jsonParam.toString());

                    os.flush();
                    os.close();


                    conn.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
    }
}



