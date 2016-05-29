package com.sd.mobile.frmwk;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;

/**
 * Created by SUDDUTT1 on 1/23/2016.
 */
public class ApplicationJSInterface {

    private String fileName;
    private String deviceId;
    private Context mContext;
    private String storedContent;
    private Activity parentActivity;

    /** Instantiate the interface and set the context */
    public ApplicationJSInterface(Context c,Activity hostActivity,String deviceId,String appJsonFileName) {
        mContext = c;
        this.deviceId = deviceId;
        this.fileName=appJsonFileName;
        this.storedContent = null;
        this.parentActivity = hostActivity;
    }

    public void loadStoredContent()
    {
        try{

            File file = new File(mContext.getFilesDir(), this.fileName);
            if(file.exists())
            {
                Log.d("ResultsRegisterApp:jsInterface","File exists " + this.fileName);
                Log.d("ResultsRegisterApp:jsInterface","Loading the content of " + this.fileName);
                BufferedReader br = new BufferedReader(new FileReader(file));
                StringBuffer strBuf = new StringBuffer(2048);
                String line = null;
                while((line=br.readLine())!=null)
                {

                    strBuf.append(new String(line));
                }
                br.close();
                this.storedContent =strBuf.toString();
                Log.d("ResultsRegisterApp:jsInterface","Read file content as "+ this.storedContent);
            }
            else
            {
                Log.d("ResultsRegisterApp:jsInterface","File does not exist " + this.fileName);
                this.storedContent = "{}";
                //We will store the above default content
                FileOutputStream fos = mContext.openFileOutput(this.fileName, Context.MODE_PRIVATE);
                fos.write(this.storedContent.getBytes());
                fos.close();
            }
        }catch(Exception ex)
        {
            Log.e("ResultsRegisterApp:jsInterface","loadFile()",ex);
            this.storedContent = "{}";
        }
    }

    /** Show a toast from the web page */
    @JavascriptInterface
    public String getDeviceId() {

        return this.deviceId;
    }
    @JavascriptInterface
    public void showAlert(String text) {
        Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show();
    }
    @JavascriptInterface
    public boolean saveDataInLocal(String jsonString)
    {
        boolean isSaveSuccess = true;
        this.storedContent = jsonString;
        //File file = new File(mContext.getFilesDir(), this.fileName);
        try {
            FileOutputStream fos = this.mContext.openFileOutput(this.fileName, Context.MODE_PRIVATE);
            fos.write(this.storedContent.getBytes());
            fos.close();
        }catch(Exception ex)
        {
            Log.e(":jsInterface","saveDataInLocal()",ex);
            isSaveSuccess = false;

        }
        return isSaveSuccess;
    }

    @JavascriptInterface
    public String  getStoredDataFromLocal()
    {
        if(this.storedContent==null)
        {
            loadStoredContent();
        }
        return this.storedContent;
    }
    @JavascriptInterface
    public void exitApplication()
    {
        this.parentActivity.finish();
    }

}
