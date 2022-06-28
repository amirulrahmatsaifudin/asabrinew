package com.example.asabri.activity.otp;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.alimuzaffar.lib.pin.PinEntryEditText;
import com.example.asabri.R;
import com.example.asabri.api.ApiService;
import com.example.asabri.api.RetrofitBuilder;
import com.example.asabri.model.GetResponseToken;
import com.example.asabri.util.ApiServiceRaw;
import com.example.asabri.util.ServiceGenerator;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerifikasiOtpActivity extends AppCompatActivity {

    private TextView etnohp11;
    private Button btnSendCodeotp;
    private PinEntryEditText otp;
    private Call<GetResponseToken> call;
    private ApiService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verifikasi_otp);
        btnSendCodeotp = findViewById(R.id.btn_sendcode1);
        etnohp11 = findViewById(R.id.nohp1);
        otp = findViewById(R.id.otplogin);
//        String otp1 = otp.getText().toString();
        Intent intent = getIntent();
        etnohp11.setText(intent.getStringExtra("mobile_number"));
        service = RetrofitBuilder.createService(ApiService.class);
        btnSendCodeotp.setOnClickListener(v -> {
            sendPostOtp();

//            String nomer = etnohp11.getText().toString();
//            JSONArray jArr = new JSONArray();
//            JSONObject jObj = new JSONObject();
//            try {
//                jObj.put("mobile_number", etnohp11.getText().toString());
//                jArr.put(jObj);
//
//            } catch (Exception e) {
//                System.out.println("Error:" + e);
//            }
//
//            // Using the Retrofit
//            ApiServiceRaw jsonPostService = ServiceGenerator.createService(ApiServiceRaw.class, "https://api-poc-asabri.picaso.id/api/");
//            Call<JsonObject> call = jsonPostService.otpconfirmation(jObj);
//            call.enqueue(new Callback<JsonObject>() {
//
//                @Override
//                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
//                    try {
//                        Log.e("response-success", response.body().toString());
//                        Intent intentToOtp = new Intent(VerifikasiOtpActivity.this, VerifikasiOtpFinishActivity.class);
//                        startActivity(intentToOtp);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//                @Override
//                public void onFailure(Call<JsonObject> call, Throwable t) {
//                    Log.e("response-failure", call.toString());
//                }
//            });
        });
    }




                    public void sendPostOtp () {
                        Thread thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    URL url = new URL("https://api-poc-asabri.picaso.id/api/v1/otp/confirmation");
                                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                                    conn.setRequestMethod("POST");
                                    conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                                    conn.setRequestProperty("Accept", "application/json");
                                    conn.setDoOutput(true);
                                    conn.setDoInput(true);

                                    String nohp = etnohp11.getText().toString();
                                    nohp = nohp.trim();
                                    String otp1 = otp.getText().toString();
                                    otp1 = otp1.trim();

                                    //
//                    JSONObject jsonParam = new JSONObject();
                                    JSONObject jsonParam = new JSONObject()
                                            .put("mobile_number", nohp)
                                            .put("otp", otp1)
                                            ;
////
//                    Log.i("JSON", jsonParam.toString());
                                    DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                                    //os.writeBytes(URLEncoder.encode(jsonParam.toString(), "UTF-8"));
                                    os.writeBytes(jsonParam.toString());
                                    os.flush();
                                    os.close();
                    Log.i("STATUS", String.valueOf(conn.getResponseCode()));
                    Log.i("MSG" , conn.getResponseMessage());

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });

                        thread.start();
                    }

                }



