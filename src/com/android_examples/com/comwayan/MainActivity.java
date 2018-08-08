package com.wayan.com.ilmugratis;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ProgressBar;


public class MainActivity extends Activity {

	WebView webview;
	ProgressBar pbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        webview = (WebView)findViewById(R.id.webView1);
        pbar = (ProgressBar)findViewById(R.id.progressBar1);
		webview.setWebViewClient(new WebViewClient());
		webview.loadUrl("http://www.google.com");
	 }
    
    	public class WebViewClient extends android.webkit.WebViewClient
        {
         @Override
         public void onPageStarted(WebView view, String url, Bitmap favicon) {
    
          // TODO Auto-generated method stub
        	 super.onPageStarted(view, url, favicon);
         }
         
         @Override
         public boolean shouldOverrideUrlLoading(WebView view, String url) {
    
          // TODO Auto-generated method stub
          view.loadUrl(url);
          return true;
         }
         @Override
         public void onPageFinished(WebView view, String url) {
    
          // TODO Auto-generated method stub
    
          super.onPageFinished(view, url);
          pbar.setVisibility(View.GONE);
    
         }
    
        }

    
}