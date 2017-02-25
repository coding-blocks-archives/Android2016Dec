package com.codingblocks.cameraapp;

import android.content.Context;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

/**
 * Created by championswimmer on 25/02/17.
 */

public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback {

    private Camera camera;
    private SurfaceHolder holder;
    private boolean initialised = false;

    public CameraPreview(Context context) {
        super(context);
    }

    public CameraPreview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CameraPreview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public CameraPreview(Context context, Camera camera) {
        super(context);

        this.camera = camera;

        this.holder = getHolder();
        holder.addCallback(this);
        initialised = true;
    }

    public void initialise (Camera camera) {
        if (initialised) return;

        this.camera = camera;

        this.holder = getHolder();
        holder.addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        try {
            camera.setPreviewDisplay(holder);
            camera.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        if(holder.getSurface() == null) {
            return;
        }

        camera.stopPreview();
        try {
            camera.getParameters().setPreviewSize(width, height);
            camera.setPreviewDisplay(holder);
            camera.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
