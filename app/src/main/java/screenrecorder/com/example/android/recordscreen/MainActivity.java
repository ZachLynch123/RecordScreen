package screenrecorder.com.example.android.recordscreen;
// TODO 1. Import all necessary libraries
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


public class MainActivity extends AppCompatActivity {
    // TODO 2. Add variables for the following: mScreenDensity, display width+height
    // TODO  The button, media projection, virtualdisplay, media recorder,
    // TODO  request permission, and isRecording

    // all variables
    private static final int displayWidth = 1080;
    private static final int displayHeight = 1920;
    private static final int REQUEST_CODE = 1000;
    private int screenDensity;
    Button buttonAction;
    private VirtualDisplay  mVirtualDisplay;
    private MediaProjection  mMediaProjection;
    private MediaRecorder mMediaRecorder;
    private static int pemissionKey = 1;
    boolean isRecording = false;
    // TODO 5. add static method for the orientation of screen. format:  ORIENTATION.append(Surface.ROTATION_0,90)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonAction = (Button) findViewById(R.id.btn_action);


        // TODO 6. String array that holds the PERMISSIONS{ Manifest.permission.WRITE_INTERNAL_STORAGE,}
        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.RECORD_AUDIO};
        buttonAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isRecording = true;
                changeText();
            }
        });



    }

        // TODO 7. If statement to check if the permissions are granted and request the permissionKey

        // TODO 8. Set up DisplayMatrix and initialize mediarecorder method





        // TODO 9. Get id for button and check for onClick actions


        public void changeText(){

            if (isRecording){
                buttonAction.setText("Stop recording");
            }else {
                buttonAction.setText("Start recording");
            }
    }
    }

    // TODO 10. Make method to change text in button if the recorder is recording or not



    // TODO 11. method to toggle the screen sharing

