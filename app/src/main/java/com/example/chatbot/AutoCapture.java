package com.example.chatbot;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.hardware.Camera;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.Surface;
import android.view.SurfaceView;
import android.view.SurfaceHolder;
import android.widget.TextView;

import java.io.IOException;


public class AutoCapture extends AppCompatActivity implements SurfaceHolder.Callback {

    public static Bitmap identityBitmap;
    private Camera camera;
    private SurfaceView surfaceView;
    private SurfaceHolder surfaceHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_capture);
        surfaceView = (SurfaceView) findViewById(R.id.surfaceView);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);

    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        try {
            // Open the front-facing camera
            camera = Camera.open(Camera.CameraInfo.CAMERA_FACING_FRONT);
            // Get the current display rotation
            int rotation = getWindowManager().getDefaultDisplay().getRotation();

            // Set the display orientation based on the current display rotation
            switch (rotation) {
                case Surface.ROTATION_0:
                    camera.setDisplayOrientation(90);
                    break;
                case Surface.ROTATION_90:
                    camera.setDisplayOrientation(0);
                    break;
                case Surface.ROTATION_180:
                    camera.setDisplayOrientation(270);
                    break;
                case Surface.ROTATION_270:
                    camera.setDisplayOrientation(180);
                    break;
            }
            camera.setPreviewDisplay(surfaceHolder);
            camera.startPreview();


            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    camera.takePicture(null, null, new Camera.PictureCallback() {
                        @Override
                        public void onPictureTaken(byte[] bytes, Camera camera) {
                            int imageWidth = 200;
                            int imageHeight = 200;
                            Bitmap bitmap=  BitmapFactory.decodeByteArray(bytes, 0, bytes.length, null);
                            identityBitmap = Bitmap.createScaledBitmap(bitmap, imageWidth, imageHeight, false);

                            Intent intent=new Intent(AutoCapture.this,MainActivity.class);
                            startActivity(intent);
                        }
                    });
                }
            },2000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
        camera = Camera.open(Camera.CameraInfo.CAMERA_FACING_FRONT);
        camera.stopPreview();
        camera.release();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (camera != null) {
            camera.stopPreview();
            camera.release();
            camera = null;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {

    }
}