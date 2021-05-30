package com.example.androidmedia;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class UploadActivity extends AppCompatActivity {

    private static final String LOG_TAG = MediaListActivity.class.getName();
    private final int REQUEST_CODE_ASK_PERMISSION = 123;
    private final int tag = 1;

    private FirebaseFirestore mFirestore;
    private CollectionReference mItems;

    ImageView imageView;

    EditText identifierET;
    EditText basedOnET;
    EditText statusET;
    EditText typeET;
    EditText modalityET;
    EditText viewET;
    EditText subjectET;
    EditText encounterET;
    EditText createdDateTimeET;
    EditText createdPeriodET;
    EditText issuedET;
    EditText operatorET;
    EditText reasonCodeET;
    EditText bodysiteET;
    EditText deviceNameET;
    EditText deviceET;
    EditText heightET;
    EditText widthET;
    EditText framesET;
    EditText durationET;
    EditText noteET;
    Bitmap img;

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        imageView = findViewById(R.id.imageView2);

        identifierET = findViewById(R.id.identifier);
        basedOnET = findViewById(R.id.basedOn);
        statusET = findViewById(R.id.status);
        typeET = findViewById(R.id.type);
        modalityET = findViewById(R.id.modality);
        viewET = findViewById(R.id.view);
        subjectET = findViewById(R.id.subject);
        encounterET = findViewById(R.id.encounter);
        createdDateTimeET = findViewById(R.id.createdDateTime);
        createdPeriodET = findViewById(R.id.createdPeriod);
        issuedET = findViewById(R.id.issued);
        operatorET = findViewById(R.id.operator);
        reasonCodeET = findViewById(R.id.reasonCode);
        bodysiteET = findViewById(R.id.bodysite);
        deviceNameET = findViewById(R.id.deviceName);
        deviceET = findViewById(R.id.device);
        heightET = findViewById(R.id.height);
        widthET = findViewById(R.id.width);
        framesET = findViewById(R.id.frames);
        durationET = findViewById(R.id.duration);
        noteET = findViewById(R.id.note);

        mFirestore = FirebaseFirestore.getInstance();
        mItems = mFirestore.collection("Contents");

        button = findViewById(R.id.button2);
    }

    private void onUpload(View v) {
        String identifier = identifierET.getText().toString();
        String basedOn = basedOnET.getText().toString();
        String status = statusET.getText().toString();
        String type = typeET.getText().toString();
        String modality = modalityET.getText().toString();
        String view = viewET.getText().toString();
        String subject = subjectET.getText().toString();
        String encounter = encounterET.getText().toString();
        String createdDateTime = createdDateTimeET.getText().toString();
        String createdPeriod = createdPeriodET.getText().toString();
        String issued = issuedET.getText().toString();
        String operator = operatorET.getText().toString();
        String reasonCode = reasonCodeET.getText().toString();
        String bodysite = bodysiteET.getText().toString();
        String deviceName = deviceNameET.getText().toString();
        String device = deviceET.getText().toString();
        String height = heightET.getText().toString();
        String width = widthET.getText().toString();
        String frames = framesET.getText().toString();
        String duration = durationET.getText().toString();
//        TypedArray image = getResources().getDrawable();
        String note = noteET.getText().toString();

//        mItems.add(new MediaContent(identifier,
//                basedOn,
//                status,
//                type,
//                modality,
//                view,
//                subject,
//                encounter,
//                createdDateTime,
//                createdPeriod,
//                issued,
//                operator,
//                reasonCode,
//                bodysite,
//                deviceName,
//                device,
//                height,
//                width,
//                frames,
//                duration,
//                image.,
//                note));
    }

    public void openCamera(View view) {
        checkUserPermission();
    }

    void checkUserPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_ASK_PERMISSION);
                return;
            }
        }
        takePicture();
    }

    private void takePicture() {
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, tag);
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSION:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    takePicture();
                } else {
                    Toast.makeText(this, "Permission denied!", Toast.LENGTH_LONG).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == tag && resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            img = (Bitmap) bundle.get("data");
            imageView.setImageBitmap(img);
        }
    }

    public void rotate(View view) {
        Animation rotate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.image_rotate);
        button.startAnimation(rotate);

    }
}