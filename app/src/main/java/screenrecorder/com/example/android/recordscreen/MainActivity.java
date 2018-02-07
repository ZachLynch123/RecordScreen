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

import static android.hardware.display.DisplayManager.VIRTUAL_DISPLAY_FLAG_AUTO_MIRROR;


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
    // have to create a private inner class within the main class in order to use MediaProjectionCallback as a variable type
    private MediaProjectionCallback mMediaProjectionCallback;
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
        // cast media recorder to the mMediaRecorder variable
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





        public void btnReload(){

        if (isRecording){
            buttonAction.setText("Stop Recording");
        } else {
            buttonAction.setText("Start Recording");
        }
        }
        public void onToggleScreenShare() {
            if (!isRecording) {
                initialzieRec();
                shareScreen();
            } else {
                // stops and resets the media recorder
                mMediaRecorder.stop();
                mMediaRecorder.reset();
                stopScreenSharing();
            }
        }
        public void shareScreen() {
            /* refer to MediaProjectionManager documentation. in order to start the screenCapture intent
            startActivityForResult () must pass to getMediaProjection
             */
            if (mMediaProjection == null) {
                startActivityForResult(mProjectionManager.createScreenCaptureIntent(),REQUEST_CODE);
                return;
            }
            mVirtualDisplay = createVirtualDisplay();
            mMediaRecorder.start();
            isRecording = true;
            btnReload();
            }
            // refer to displayManager documentation. Have to us VIRTUAL_DISPLAY_MIRROR and getSurface()
            private VirtualDisplay createVirtualDisplay(){
            // capture screen to record
            return mMediaProjection.createVirtualDisplay("MainActivity", displayWidth, displayHeight,
                    mScreenDensity, VIRTUAL_DISPLAY_FLAG_AUTO_MIRROR, mMediaRecorder.getSurface(), null, null);
            }
            // inintialze the recording of the screen
            private void initialzieRec(){
                try {
                    /*
                     CAREFUL WITH THE VIDEO CODING it must be configured in a
                                        SPECIFIC
                     order for it to work properly
                      */
                    /*
                     sets the audio source (audio output in this case is set to MIC)
                     since the mic is in the virtual display, the mic is basically just the
                     output of the phone
                     if there are any questions about this, look up MediaRecorder documentation
                     it has everything there. and trust me future self or others, there may be some questions
                      */
                    mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                    // setting video source, which in this case is just the surface of the actual phone
                    mMediaRecorder.setVideoSource(MediaRecorder.VideoSource.SURFACE);
                    mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
                    mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                    // saves it to the external SD card with the extension video.mp4
                    mMediaRecorder.setOutputFile(Environment.getExternalStorageDirectory() + "/video.mp4");
                    // set video size to the display width and height
                    mMediaRecorder.setVideoSize(displayWidth, displayHeight);
                    mMediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264);
                    mMediaRecorder.setVideoEncodingBitRate(512 * 1000);
                    // tried to set frameRate to 60. if it doesn't work set it to 30 (16)
                    mMediaRecorder.setVideoFrameRate(32); // 60?
                    mMediaRecorder.setVideoEncodingBitRate(3000000);
                    // get the rotation of the screen based on whether the display was rotated or not
                    int rotation = getWindowManager().getDefaultDisplay().getRotation();
                    int orientation = ORIENTATIONS.get(rotation + 90);
                    // set the orientation
                    mMediaRecorder.setOrientationHint(orientation);
                    // prepare to record
                    mMediaRecorder.prepare();
                } catch (IOException e){
                    e.printStackTrace();
                }

            }

    }




    // TODO 10. Make method to change text in button if the recorder is recording or not



    // TODO 11. method to toggle the screen sharing

