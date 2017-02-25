package com.codingblocks.cameraapp;

import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Surface;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import java.io.File;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "CAM";

    Camera cam = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (cam == null) {
            cam = Camera.open();
        }

        List<Camera.Size> picSizes =  cam.getParameters().getSupportedPictureSizes();
        List<Camera.Size> vidSizes = cam.getParameters().getSupportedVideoSizes();
        List<Camera.Size> prevSizes = cam.getParameters().getSupportedPreviewSizes();

        for (Camera.Size size : picSizes) {
            Log.d(TAG, "PIC: " + size.width + " " + size.height);
        }
        for (Camera.Size size : vidSizes) {
            Log.d(TAG, "VID: " + size.width + " " + size.height);
        }
        for (Camera.Size size : prevSizes) {
            Log.d(TAG, "PREV: " + size.width + " " + size.height);
        }


        final Camera.PictureCallback picCallback = new Camera.PictureCallback() {
            @Override
            public void onPictureTaken(byte[] data, Camera camera) {
                Log.d(TAG, "onPictureTaken: " + data.length);
                cam.stopPreview();
                cam.startPreview();
            }
        };

        Button clickButton = (Button) findViewById(R.id.btnClick);

        FrameLayout fLayout = (FrameLayout) findViewById(R.id.preview_container);
//        CameraPreview myPreview = (CameraPreview) findViewById(R.id.preview);
//        myPreview.initialise(cam);

        int rotation = 0;

        switch (getWindowManager().getDefaultDisplay().getRotation()) {
            case Surface.ROTATION_0: rotation = 90; break;
            case Surface.ROTATION_90: rotation = 0; break;
            case Surface.ROTATION_270: rotation = 180; break;
        }

        cam.setDisplayOrientation(rotation);
        cam.getParameters().setRotation(rotation);

        final CameraPreview camPreview = new CameraPreview(this, cam);

        fLayout.addView(camPreview);

        clickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cam.takePicture(null, null, picCallback);
            }
        });


    }



    @Override
    protected void onStop() {

        cam.release();
        super.onStop();
    }
}
