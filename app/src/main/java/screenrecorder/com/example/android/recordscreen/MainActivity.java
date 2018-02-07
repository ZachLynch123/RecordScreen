package screenrecorder.com.example.android.recordscreen;
// TODO 1. Import all necessary libraries
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.display.DisplayManager;
import android.hardware.display.VirtualDisplay;
import android.media.MediaRecorder;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.media.projection.*;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.Surface;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.util.function.Function;


public class MainActivity extends AppCompatActivity {
    // TODO 2. Add variables for the following: mScreenDensity, display width+height
    // TODO  The button, media projection, virtualdisplay, media recorder,
    // TODO  request permission, and isRecording

    // all variables
    private static final String TAG = "MainActivity";
    private static final int displayWidth = 1080;
    private static final int displayHeight = 1920;
    private static final int REQUEST_CODE = 1000;
    private int mScreenDensity;
    // added findViewById to the variable all in 1 line to eliminate redundancy
    Button buttonAction = (Button) findViewById(R.id.btn_action);
    private VirtualDisplay  mVirtualDisplay;
    private MediaProjection  mMediaProjection;
    private MediaProjectionManager  mProjectionManager;
    private MediaRecorder mMediaRecorder;
    private static int pemissionKey = 1;
    private  static final SparseIntArray ORIENTATIONS = new SparseIntArray();
    boolean isRecording = false;

    // TODO 5. add static method for the orientation of screen. format:  ORIENTATION.append(Surface.ROTATION_0,90)
    static {
        ORIENTATIONS.append(Surface.ROTATION_0,90);
        ORIENTATIONS.append(Surface.ROTATION_90,180);
        ORIENTATIONS.append(Surface.ROTATION_180,270);
        ORIENTATIONS.append(Surface.ROTATION_270,360);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.RECORD_AUDIO};
        // initialized Display Mertrics and set it to the variable metrics
        DisplayMetrics metrics = new DisplayMetrics();
        // initialized media recorder
        mMediaRecorder = new MediaRecorder();
        // gets DPI of screen
        mScreenDensity = metrics.densityDpi;

        // manages the retrieval of certain types of MediaProjection
        mProjectionManager = (MediaProjectionManager) getSystemService(Context.MEDIA_PROJECTION_SERVICE);



        buttonAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onToggleScreenShare();
            }
        });



    }

        // TODO 7. If statement to check if the permissions are granted and request the permissionKey

        // TODO 8. Set up DisplayMatrix and initialize mediarecorder method







        public void brnReload(){

        if (isRecording){
            buttonAction.setText("Stop Recording");
        } else {
            buttonAction.setText("Start Recording");
        }
        }
        public void onToggleScreenShare() {
            if (!isRecording) {
                initalizeRec();
                shareScreen();
            }
        }
    }

    // TODO 10. Make method to change text in button if the recorder is recording or not



    // TODO 11. method to toggle the screen sharing

