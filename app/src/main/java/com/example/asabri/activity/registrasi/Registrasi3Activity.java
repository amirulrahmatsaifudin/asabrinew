package com.example.asabri.activity.registrasi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.Preview;
import androidx.camera.extensions.HdrImageCaptureExtender;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.Toast;
import com.example.asabri.R;
import com.example.asabri.activity.otp.OtpActivity;
import com.example.asabri.api.ApiService;
import com.example.asabri.api.RetrofitBuilder;
import com.example.asabri.model.GetResponseToken;
import com.example.asabri.util.ImageResizer;
import com.google.common.util.concurrent.ListenableFuture;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Registrasi3Activity extends AppCompatActivity {

    private Executor executor = Executors.newSingleThreadExecutor();
    private int REQUEST_CODE_PERMISSIONS = 1001;
    private final String[] REQUIRED_PERMISSIONS = new String[]{"android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE"};

    PreviewView mPreviewView;
    Button captureImage;

    private ApiService service;
    private Call<GetResponseToken> call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi3);

        mPreviewView = findViewById(R.id.previewView);
        captureImage = findViewById(R.id.btn_submite11);

        if(allPermissionsGranted()){
            startCamera(); //start camera if permission has been granted by user
        } else{
            ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS);
        }

        service = RetrofitBuilder.createService(ApiService.class);
    }

    private void startCamera() {
        final ListenableFuture<ProcessCameraProvider> cameraProviderFuture = ProcessCameraProvider.getInstance(this);
        cameraProviderFuture.addListener(new Runnable() {
            @Override
            public void run() {
                try {
                    ProcessCameraProvider cameraProvider = cameraProviderFuture.get();
                    bindPreview(cameraProvider);
                } catch (ExecutionException | InterruptedException e) {
                }
            }
        }, ContextCompat.getMainExecutor(this));
    }

    void bindPreview(@NonNull ProcessCameraProvider cameraProvider) {
        Preview preview = new Preview.Builder()
                .build();
        CameraSelector cameraSelector = new CameraSelector.Builder()
                .requireLensFacing(CameraSelector.LENS_FACING_FRONT)
                .build();
        ImageAnalysis imageAnalysis = new ImageAnalysis.Builder()
                .build();
        ImageCapture.Builder builder = new ImageCapture.Builder();
        //Vendor-Extensions (The CameraX extensions dependency in build.gradle)
        HdrImageCaptureExtender hdrImageCaptureExtender = HdrImageCaptureExtender.create(builder);
        // Query if extension is available (optional).
        if (hdrImageCaptureExtender.isExtensionAvailable(cameraSelector)) {
            // Enable the extension if available.
            hdrImageCaptureExtender.enableExtension(cameraSelector);
        }

        final ImageCapture imageCapture = builder
                .setTargetRotation(this.getWindowManager().getDefaultDisplay().getRotation())
                .build();
        preview.setSurfaceProvider(mPreviewView.createSurfaceProvider());
        Camera camera = cameraProvider.bindToLifecycle((LifecycleOwner)this, cameraSelector, preview, imageAnalysis, imageCapture);

        captureImage.setOnClickListener(v -> {
            SimpleDateFormat mDateFormat = new SimpleDateFormat("yyyyMMddHHmmss", Locale.US);
            File file = new File(getBatchDirectoryName(), mDateFormat.format(new Date())+ ".jpeg");
            ImageCapture.OutputFileOptions outputFileOptions = new ImageCapture.OutputFileOptions.Builder(file).build();
            imageCapture.takePicture(outputFileOptions, executor, new ImageCapture.OnImageSavedCallback () {
                @Override
                public void onImageSaved(@NonNull ImageCapture.OutputFileResults outputFileResults) {
                    new Handler(Looper.getMainLooper()).post(() -> {
                        Bitmap fullSizeBitmap = BitmapFactory.decodeFile(file.getPath());
                        Bitmap reducedBitmap = ImageResizer.reduceBitmapSize(fullSizeBitmap, 800);
                        File reducedFile = getBitmapfile(reducedBitmap);

//                        Bitmap bitmap = Bitmap.createScaledBitmap(bm, 800, 800, true);

                         RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"),reducedFile);
                         MultipartBody.Part image = MultipartBody.Part.createFormData("image",reducedFile.getName(),requestFile);

                        Intent intent = getIntent();
                        String nameone = intent.getStringExtra("name");
                        String mobile_numberone = intent.getStringExtra("mobile_number");
                        String retirement_numberone = intent.getStringExtra("retirement_number");
                        String nik_numberone = intent.getStringExtra("nik_number");
                        String addressone = intent.getStringExtra("address");
                        String date_of_birthone = intent.getStringExtra("date_of_birth");
                        String place_of_birthone = intent.getStringExtra("place_of_birth");
                        String imagebase64one = intent.getStringExtra("name");
                        String passwordone = intent.getStringExtra("password");
                        String password_confirmationone = intent.getStringExtra("password_confirmation");

                        RequestBody name = RequestBody.create(MediaType.parse("multipart/form-data"),nameone);
                        RequestBody mobile_number = RequestBody.create(MediaType.parse("multipart/form-data"),mobile_numberone);
                        RequestBody retirement_number = RequestBody.create(MediaType.parse("multipart/form-data"),retirement_numberone);
                        RequestBody nik_number = RequestBody.create(MediaType.parse("multipart/form-data"),nik_numberone);
                        RequestBody address = RequestBody.create(MediaType.parse("multipart/form-data"),addressone);
                        RequestBody date_of_birth = RequestBody.create(MediaType.parse("multipart/form-data"),date_of_birthone);
                        RequestBody place_of_birth = RequestBody.create(MediaType.parse("multipart/form-data"),place_of_birthone);
                        RequestBody imagebase64 = RequestBody.create(MediaType.parse("multipart/form-data"),imagebase64one);
                        RequestBody password = RequestBody.create(MediaType.parse("multipart/form-data"),passwordone);
                        RequestBody password_confirmation = RequestBody.create(MediaType.parse("multipart/form-data"),password_confirmationone);

                        call = service.crete_user(name, mobile_number, retirement_number, nik_number, address, date_of_birth, place_of_birth, image, imagebase64, password, password_confirmation);
                        call.enqueue(new Callback<GetResponseToken>() {
                            @Override
                            public void onResponse(Call<GetResponseToken> call, Response<GetResponseToken> response) {
                                if (response.isSuccessful()) {
                                    Toast.makeText(Registrasi3Activity.this, "Succes", Toast.LENGTH_SHORT).show();
                                    Intent intentToOtp = new Intent(Registrasi3Activity.this, OtpActivity.class);
                                    intent.putExtra("mobile_number", mobile_numberone);
                                    startActivity(intentToOtp);
                                }
                            }

                            @Override
                            public void onFailure(Call<GetResponseToken> call, Throwable t) {
                                Toast.makeText(Registrasi3Activity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    });
                }

                @Override
                public void onError(@NonNull ImageCaptureException error) {
                    error.printStackTrace();
                }
            });
        });
    }
    public String getBatchDirectoryName() {
        String app_folder_path = "";
        app_folder_path = Environment.getExternalStorageDirectory().toString() + "/images";
        File dir = new File(app_folder_path);
        if (!dir.exists() && !dir.mkdirs()) {
        }
        return app_folder_path;
    }
    private boolean allPermissionsGranted(){
        for(String permission : REQUIRED_PERMISSIONS){
            if(ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED){
                return false;
            }
        }
        return true;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (allPermissionsGranted()) {
                startCamera();
            } else {
                Toast.makeText(this, "Permissions not granted by the user.", Toast.LENGTH_SHORT).show();
                this.finish();
            }
        }
    }
    private File getBitmapfile (Bitmap reducedBitmap){
        File filee = new File(Environment.getExternalStorageDirectory()+File.separator + "reduced_file");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
      reducedBitmap.compress(Bitmap.CompressFormat.JPEG, 0, baos);
      byte[] bitmapdata = baos.toByteArray();
      try {
          filee.createNewFile();
          FileOutputStream fos = new FileOutputStream(filee);
          fos.write(bitmapdata);
          fos.flush();
          fos.close();
          return filee;
      }catch (Exception e){
          e.printStackTrace();
      }
      return filee;
  }
}