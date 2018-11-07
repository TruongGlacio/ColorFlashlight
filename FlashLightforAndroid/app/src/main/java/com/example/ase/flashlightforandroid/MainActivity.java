package com.example.ase.flashlightforandroid;

import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    private Camera camera;
    private Camera camera1;
    ImageButton flashlightSwitchImg, mButton_bing,mButton_blue_green,mButton_blue,mButton_orange,mButton_green,mButton_red,mButton_tim_luc,mButton_yellow,mButton_white,mButton_black;

    private boolean isFlashlightOn;
    Camera.Parameters params;
    RelativeLayout mRelativeLayout;
    private String startut="#000000";
    ImageButton mButton_Blink;
    boolean i=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set fullscreen
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if(Build.VERSION.SDK_INT < 19) { //View.SYSTEM_UI_FLAG_IMMERSIVE is only on API 19+
            this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        } else {
            this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE);
        }
        setContentView(R.layout.activity_main);
        setupgui();

        boolean isCameraFlash = getApplicationContext().getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);

        if (!isCameraFlash) {
            showNoCameraAlert();
        } else {
            camera =Camera.open();
            params = camera.getParameters();
        }

        flashlightSwitchImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFlashlightOn) {
                    setFlashlightOff();
                } else {
                    setFlashlightOn();
                }
            }
        });

        mButton_bing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            startut="#F50057";// mau hong
            }
        });
        mButton_blue_green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startut="#00E5FF";// mau luc_sang
            }
        });
        mButton_blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startut="#2962FF";// mau hong
            }
        });
        mButton_green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startut="#00E676";// mau hong
            }
        });
        mButton_orange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startut="#FF6D00";// mau cam
            }
        });
        mButton_red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startut="#D50000";// mau do
            }
        });
        mButton_tim_luc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startut="#6200EA";// mau timluc
            }
        });
        mButton_yellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startut="#FFFF00";// mau vang
            }
        });
        mButton_white.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startut="#FFFFFF";// mau trang
            }
        });
        mButton_black.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startut="#000000";// mau trang
            }
        });
        mButton_Blink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              i=!i;
                if(i==true)
                {
                    mButton_Blink.setImageResource(R.drawable.icon_blink_button_off);
                }
                else
                {
                    mButton_Blink.setImageResource(R.drawable.icon_blink_button);
                }



            }
        });
    }
    public void setupgui()
    { // flashlight on off Image
        flashlightSwitchImg = (ImageButton) findViewById(R.id.flashlightSwitch);
        mRelativeLayout=(RelativeLayout)findViewById(R.id.Relative_layout1);

         mButton_bing=(ImageButton) findViewById(R.id.Color_bing);
         mButton_blue_green=(ImageButton) findViewById(R.id.Color_blue_green);
         mButton_blue=(ImageButton) findViewById(R.id.Color_blue);
         mButton_green=(ImageButton) findViewById(R.id.Color_Green);
         mButton_orange=(ImageButton) findViewById(R.id.Color_orange);
         mButton_red=(ImageButton) findViewById(R.id.Color_red);
         mButton_tim_luc=(ImageButton) findViewById(R.id.Color_Tim_Luc);
         mButton_yellow=(ImageButton) findViewById(R.id.Color_Yellow);
         mButton_white=(ImageButton)findViewById(R.id.Color_White);
         mButton_black=(ImageButton)findViewById(R.id.Color_Black);
        mButton_Blink=(ImageButton)findViewById(R.id.button_blink);


    }


    public CountDownTimer countDownTimer= new CountDownTimer(30000, 100)
       {
            public void onTick(long millisUntilFinished) {

              //  mTextField.setText("seconds remaining: " + millisUntilFinished / 1000);
                long a=millisUntilFinished/100;
                mRelativeLayout.setBackgroundColor(Color.parseColor(startut));
        if(i==true)
            {
                Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);
                mRelativeLayout.startAnimation(animation1);
                blink(1,2);
            }


            }

            public void onFinish() {
             //   mTextField.setText("done!");
               countDownTimer.start();
            }
        }.start();


    private void showNoCameraAlert(){
        new AlertDialog.Builder(this)
                .setTitle("Error: No Camera Flash!")
                .setMessage("Camera flashlight not available in this Android device!")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish(); // close the Android app
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
        return;
    }
    private void setFlashlightOn() {
        params = camera.getParameters();
        params.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
        camera.setParameters(params);
        camera.startPreview();
        isFlashlightOn = true;
        flashlightSwitchImg.setImageResource(R.drawable.power_on);

    }

    private void setFlashlightOff() {
        params.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
        camera.setParameters(params);
        camera.stopPreview();
        isFlashlightOn = false;
        flashlightSwitchImg.setImageResource(R.drawable.power_off);
    }
    private void blink(final int delay, final int times) {
        Thread t = new Thread() {
            public void run() {
                try {

                    for (int j=0; j < times*2; j++) {

                          setFlashlightOff();
                          sleep(delay);
                          setFlashlightOn();
                          sleep(delay);
                    }

                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        t.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (camera != null) {
            camera.release();
            camera = null;
        }
    }
}
