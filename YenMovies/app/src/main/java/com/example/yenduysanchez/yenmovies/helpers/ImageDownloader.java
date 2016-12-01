package com.example.yenduysanchez.yenmovies.helpers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Yenduy Sanchez on 11/17/2016.
 */
public class ImageDownloader extends AsyncTask<Void, Void, Bitmap> {

    private ImageView image;
    private String url;

    public ImageDownloader(String url, ImageView image){
        this.url=url;
        this.image=image;
    }

    @Override
    protected Bitmap doInBackground(Void... voids) {
        Bitmap bmp = null;
        try{
            URL imageUrl = new URL(url);
            HttpURLConnection con = (HttpURLConnection)imageUrl.openConnection();
            InputStream is = con.getInputStream();
            bmp = BitmapFactory.decodeStream(is);
            if (null != bmp)
                return bmp;

        }catch(Exception e){}
            return bmp;

    }

    @Override
    protected void onPostExecute(Bitmap result) {
        image.setImageBitmap(result);
    }
}
