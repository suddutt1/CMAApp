package com.sd.mobile.app;

import android.app.Activity;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.sd.mobile.frmwk.ApplicationJSInterface;


public class LauncherActivity extends Activity {

    private static final int SPLASH_TIMEOUT = 5000;
    WebView myWebView;
    TextView textView;
    TextView textViewAppName;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        String deviceId = Settings.Secure.getString(this.getContentResolver(),
                Settings.Secure.ANDROID_ID);

        //Show the splash screen

        //Set the Splash screen timing
        myWebView = (WebView)findViewById(R.id.webView);
        textView =(TextView)findViewById(R.id.textView);
        imageView = (ImageView)findViewById(R.id.logoView);
        textViewAppName = (TextView)findViewById(R.id.textViewAppName);

        myWebView.setWebViewClient(new WebViewClient()
        {
            public void onPageFinished(WebView view, String url) {

            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;

            }
        });

        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.getSettings().setGeolocationEnabled(true);
        myWebView.getSettings().setAllowFileAccessFromFileURLs(true);
        myWebView.getSettings().setAllowUniversalAccessFromFileURLs(true);
        myWebView.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onGeolocationPermissionsShowPrompt(String origin,
                                                           GeolocationPermissions.Callback callback) {
                // Always grant permission since the app itself requires location
                // permission and the user has therefore already granted it
                callback.invoke(origin, true, false);
            }


        });

        //Add java script interface
        ApplicationJSInterface jsInterface  =new ApplicationJSInterface(this,this,deviceId,"data.json");
        jsInterface.loadStoredContent();
        myWebView.addJavascriptInterface(jsInterface, "Android");
        imageView.postDelayed(new Runnable() {
            @Override
            public void run() {
                imageView.setVisibility(View.GONE);
                textView.setVisibility(View.GONE);
                textViewAppName.setVisibility(View.GONE);
                myWebView.setVisibility(View.VISIBLE);

            }
        },SPLASH_TIMEOUT);
        myWebView.loadUrl("file:///android_asset/www/index.html");
    }



}
