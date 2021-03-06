package com.example.assignment1;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

public class DownloadImage extends Activity {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_image);
    
        new Thread(new Runnable() {
            public void run() {
	            final Bitmap bitmap = DownloadImage("http://www.hig.no/var/ezwebin_site/storage/images/ansatte/avdeling_for_informatikk_og_medieteknikk/simon_j_r_mccallum/270018-7-nor-NO/simon_j_r_mccallum.jpg");
	            final ImageView img = (ImageView) findViewById(R.id.imageView1);
	            img.post(new Runnable(){
					public void run() {
						img.setImageBitmap(bitmap);
					}
	            });
	            }
        }).start();
        

    }
    
    /**
     * @author Simon McCallum
     *
     *This function downloads the image at the URL
     *location passed and then returns the bitmap
     * @param  URL     an absolute URL giving the base location and name of the image
     * @return bitmap  the image at the specified URL
     *
     */
    private Bitmap DownloadImage(String URL)
    {        
        Bitmap bitmap = null;
        InputStream in = null;        
        try {
            in = OpenHttpConnection(URL);
            bitmap = BitmapFactory.decodeStream(in);
            in.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return bitmap;                
    }
//    
//    
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
