package com.example.assignment1;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class SendDataToLoggingActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_data_to_logging);
        
        //Get string from edit_text in MainActivity
        Intent intent = getIntent();
	    String data = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
	    
	    //Concatenate location and user with the data from edit_text
	    String locationToLogData = "http://gtl.hig.no/mobile/logging.php?user=Simen&data=";
	    final String locationAndData = locationToLogData.concat(data);
        
	    // Log "locationAndData to the GTL-site. 
        new Thread(new Runnable() {
            public void run() {
            	
            	HttpClient client = new DefaultHttpClient(); 
    			HttpGet get = new HttpGet(locationAndData);
    			HttpResponse responseGet;
				try {
					responseGet = client.execute(get);
					HttpEntity resEntityGet = responseGet.getEntity(); 
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}  
            
            }
        }).start();
        
        
        
    }


}
