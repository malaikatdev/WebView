package com.wayan.com.ilmugratis;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.webkit.WebSettings;
import android.widget.TextView;
import android.widget.Toast;

import static android.content.ContentValues.TAG;

public class MainActivity extends Activity {

    TextView textView;
	WebView webview;
	ProgressBar pbar;
    int progressStatusCounter = 0;
    Handler progressHandler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        webview = (WebView)findViewById(R.id.webView1);
        pbar = (ProgressBar)findViewById(R.id.progressBar1);
        //textView = (TextView) findViewById(R.id.textView);

        new Thread(new Runnable() {
            public void run() {
                while (progressStatusCounter < 100) {
                    progressStatusCounter += 2;
                    progressHandler.post(new Runnable() {
                        public void run() {
                            pbar.setProgress(progressStatusCounter);
                            //Status update in textview
                           // textView.setText("Status: " + progressStatusCounter + "/" + pbar.getMax());
                        }
                    });
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();


		webview.setWebViewClient(new WebViewClient());

        WebSettings webSettings = webview.getSettings();
        //zoom gambar control
        webview.getSettings().setSupportZoom(true);
        webview.getSettings().setBuiltInZoomControls(true);
        webview.getSettings().setDisplayZoomControls(false);

        //khusus biar cepat
        webview.getSettings().setUseWideViewPort(true);
        webview.getSettings().setLoadWithOverviewMode(true);

        webview.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        webview.getSettings().setCacheMode(webSettings.LOAD_NO_CACHE);
        webview.getSettings().setAllowFileAccess(true);
        //biar link web bisa di klik
        webSettings.setJavaScriptEnabled(true);
        webview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
		webview.loadUrl("https://ilmugratisanbro.blogspot.com");
        webview.setWebViewClient(new WebViewClient());

        getNotif();


	 }
    @Override
    public void onBackPressed() {
        if(webview.canGoBack()) {
            webview.goBack();
        } else {
            super.onBackPressed();
        }
    }

    public class WebViewClient extends android.webkit.WebViewClient
        {
         @Override
         public void onPageStarted(WebView view, String url, Bitmap favicon) {

          // TODO Auto-generated method stub

        	 super.onPageStarted(view, url, favicon);
             Log.e("Loading URL", url);


             pbar.setVisibility(View.VISIBLE);
         }

         @Override
         public boolean shouldOverrideUrlLoading(WebView view, String url) {
          // TODO Auto-generated method stub
             if (url != null && url.startsWith("whatsapp://")) {

                 PackageManager pm = getPackageManager();
                 try{

                     Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                     PackageInfo info = pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
                     intent.setPackage("com.whatsapp");
                     view.getContext().startActivity(intent);
                     //return true;

                 }catch (PackageManager.NameNotFoundException e){

                     Toast.makeText(MainActivity.this, "Whatsapp Tidak Terinstall", Toast.LENGTH_LONG).show();
                 }

                 return true;

             } else {
                 return false;
             }
         }
         @Override
         public void onPageFinished(WebView view, String url) {

          // TODO Auto-generated method stub

          super.onPageFinished(view, url);
        //  pbar.setVisibility(View.GONE);
             try {
                 if (pbar.getVisibility() == View.VISIBLE) {

                     pbar.setVisibility(View.GONE);
                 }
             } catch (Exception exception) {
                 exception.printStackTrace();
             }

         }

        }
        //back nya bro
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webview.canGoBack()) {
            webview.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void getNotif(){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create channel to show notifications.
            String channelId  = getString(R.string.default_notification_channel_id);
            String channelName = getString(R.string.default_notification_channel_name);
            NotificationManager notificationManager =
                    getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(new NotificationChannel(channelId,
                    channelName, NotificationManager.IMPORTANCE_LOW));
        }
        if (getIntent().getExtras() != null) {
            for (String key : getIntent().getExtras().keySet()) {
                Object value = getIntent().getExtras().get(key);
                Log.d(TAG, "Key: " + key + " Value: " + value);
            }
        }
    }
}