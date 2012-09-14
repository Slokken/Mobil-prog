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
import android.os.Bundle;

public class SendDataToLoggingActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_data_to_logging);
        
        
        
        new Thread(new Runnable() {
            public void run() {
            	
            	//HttpPost httppost = new HttpPost("http://gtl.hig.no/mobile/logging.php?user=Royarne&data=lol");
            	
            	HttpClient client = new DefaultHttpClient(); 
    			HttpGet get = new HttpGet("http://gtl.hig.no/mobile/logging.php?user=Royarne&data=lol");
    			HttpResponse responseGet;
				try {
					responseGet = client.execute(get);
					HttpEntity resEntityGet = responseGet.getEntity(); 
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
            
            }
        }).start();
        
        
        
    }


}
