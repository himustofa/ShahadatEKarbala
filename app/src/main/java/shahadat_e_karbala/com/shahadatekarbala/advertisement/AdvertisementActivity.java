package shahadat_e_karbala.com.shahadatekarbala.advertisement;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import shahadat_e_karbala.com.shahadatekarbala.R;
import shahadat_e_karbala.com.shahadatekarbala.language.LocaleHelper;

public class AdvertisementActivity extends AppCompatActivity {

    private ProgressDialog progress;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advertisement);

        ActionBar bar = getSupportActionBar();
        bar.setTitle(getResources().getString(R.string.advertisement_title));

        //===============================================| Image load from web server
        progressBar();
        imageView = (ImageView) findViewById(R.id.promo_img_id);
        new LoadImage().execute();

        //===============================================| Share promotion image to facebook, twitter etc
        TextView share = (TextView) findViewById(R.id.share_id);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("image/*");
                share.putExtra(Intent.EXTRA_STREAM, getLocalBitmapUri(bitmap));
                startActivity(Intent.createChooser(share,"Share via"));
            }
        });
    }

    //===============================================| Language Change
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase));
    }

    //===============================================| Progress Bar
    public void progressBar() {
        progress = new ProgressDialog(this);
        progress.setTitle(getResources().getString( R.string.loading_title));
        progress.setMessage(getResources().getString( R.string.loading));
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();
    }

    //====================================================| Convert Uri from Bitmap
    public Uri getLocalBitmapUri(Bitmap bmp) {
        Uri bmpUri = null;
        try {
            File file =  new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "share_image_" + System.currentTimeMillis() + ".png");
            FileOutputStream out = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.close();
            bmpUri = Uri.fromFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmpUri;
    }

    //====================================================| Download image from Web Server
    private class LoadImage extends AsyncTask<Void, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(Void... voids) {
            String url = "http://app.shahadat-e-karbala.com/promotions.php";
            try {
                URLConnection connection = new URL(url).openConnection();
                connection.setConnectTimeout(1000 * 30);
                connection.setReadTimeout(1000 * 30);

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"iso-8859-1"));
                String result="";
                String line="";
                while((line = bufferedReader.readLine())!= null) {
                    result += line;
                }
                bufferedReader.close();

                Log.d("AdvertisementActivity", String.valueOf(result));

                return BitmapFactory.decodeStream(new java.net.URL(result).openStream());
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if (bitmap != null) {
                progress.dismiss();
                imageView.setImageBitmap(bitmap);
            }
        }
    }

    //====================================================| For Activity Starting and Closing
    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

}
