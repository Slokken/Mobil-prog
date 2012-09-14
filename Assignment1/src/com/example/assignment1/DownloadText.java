package com.example.assignment1;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DownloadText extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_text);
        
        new Thread(new Runnable() {
            public void run() {
	            final String dl_txt = DownloadText("http://www.ikke.no/");
	            final TextView txt = (TextView) findViewById(R.id.downloaded_txt);
	            txt.post(new Runnable(){
					public void run() {
						txt.setText(dl_txt);
					}
	            });
	            }
        }).start();
    }

    /**
     * @author Simon McCallum
     *
     *This function downloads the string of text at the URL
     *location passed and then returns content at a Java String
     * @param  URL           a String containing an absolute URL giving the base location and name of the text
     * @return returnString  the String constructed using a buffered reader
     *
     */
    private String DownloadText(String URL)
    {
    	int BUFFER_SIZE = 2000;
        InputStream in = null;
        try {
            in = OpenHttpConnection(URL);
        } catch (IOException e1) {
            e1.printStackTrace();
            return "";
        }
        
        InputStreamReader isr = new InputStreamReader(in);
        int charRead;
        
        
        String returnString = "";
        char[] inputBuffer = new char[BUFFER_SIZE];          
        try {
            while ((charRead = isr.read(inputBuffer))>0)
            {                    
                //---convert the chars to a String---
                String readString = 
                    String.copyValueOf(inputBuffer, 0, charRead);                    
                returnString += readString;
                inputBuffer = new char[BUFFER_SIZE];
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }    
        return returnString;
    }
    
    /**
     * @author Simon McCallum
     *
     * Handles some of the complexity of opening up a HTTP connection
     * @param  URL   a String containing the absolute URL giving the base location and name of the content
     * @return in    an inputStream which will be the stream of text from the server
     *
     */    
    private InputStream OpenHttpConnection(String urlString) 
    throws IOException
    {
        int response = -1;
        int http_status;
               
        URL url = new URL(urlString); 
        URLConnection conn = url.openConnection();
        
        InputStream in= null;
                 
        if (!(conn instanceof HttpURLConnection))                     
            throw new IOException("Not an HTTP connection");
        
   //     try{
            HttpURLConnection httpConn = (HttpURLConnection) conn;

             in = conn.getInputStream();  
                http_status = httpConn.getResponseCode();

                // better check it first
                if (http_status / 100 != 2) {
                  // redirects, server errors, lions and tigers and bears! Oh my!
                }
     //   }
    //    catch (Exception ex)
     //   {
       //     throw new IOException("Error connecting");            
       // }
        return in;     
    }
}
