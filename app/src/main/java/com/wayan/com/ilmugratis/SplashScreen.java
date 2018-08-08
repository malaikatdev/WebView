package com.wayan.com.ilmugratis;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;


import static java.lang.Thread.sleep;

public class SplashScreen extends AppCompatActivity {

    ImageView imageView;
    private static int splashInterval = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_splash_screen);
        imageView=(ImageView)findViewById(R.id.image1);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade);
        imageView.startAnimation(animation);

       /* new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    sleep(3000);
                    // TODO Auto-generated method stub
                    Intent i = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(i);
                    //jeda selesai Splashscreen
                    this.finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
            private void finish() {
                // TODO Auto-generated method stub
            }
        }, splashInterval);*/
       Thread timer=new Thread(){
           @Override
           public void run() {
               try {
                   sleep(3000);
                   // TODO Auto-generated method stub
                   Intent i = new Intent(SplashScreen.this, MainActivity.class);
                   startActivity(i);
                   //jeda selesai Splashscreen
                   finish();
                   super.run();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }

           }
       };
       timer.start();
    }
}
