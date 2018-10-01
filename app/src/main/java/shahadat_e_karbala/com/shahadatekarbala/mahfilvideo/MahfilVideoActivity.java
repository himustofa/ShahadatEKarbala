package shahadat_e_karbala.com.shahadatekarbala.mahfilvideo;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import shahadat_e_karbala.com.shahadatekarbala.R;
import shahadat_e_karbala.com.shahadatekarbala.language.LocaleHelper;

public class MahfilVideoActivity extends AppCompatActivity {

    private CardView cardView1, cardView2, cardView3, cardView4;
    private ProgressDialog progress;
    private WebView myWebViewMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mahfil_video);

        //===============================================| Change Actionbar Title
        ActionBar bar = getSupportActionBar();
        bar.setTitle(getResources().getString(R.string.mahfil_title));

        cardView1 = (CardView) findViewById(R.id.mahfil_video_live);
        cardView1.setOnClickListener(new ActionHandler());
        /*cardView2 = (CardView) findViewById(R.id.mahfil_video_30);
        cardView2.setOnClickListener(new ActionHandler());
        cardView3 = (CardView) findViewById(R.id.mahfil_video_31);
        cardView3.setOnClickListener(new ActionHandler());
        cardView4 = (CardView) findViewById(R.id.mahfil_video_32);
        cardView4.setOnClickListener(new ActionHandler());*/

        myWebViewMain = (WebView) findViewById(R.id.live_web_view_id);
        startWebView("http://shahadat-e-karbala.com/category/mahfil/");
    }

    //===============================================| Language Change
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase));
    }

    //===============================================| Other Mahfil  List
    private void startWebView(String url) {
        WebSettings settings = myWebViewMain.getSettings();
        settings.setJavaScriptEnabled(true);
        myWebViewMain.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);

        myWebViewMain.getSettings().setBuiltInZoomControls(true);
        myWebViewMain.getSettings().setUseWideViewPort(true);
        myWebViewMain.getSettings().setLoadWithOverviewMode(true);

        progress = new ProgressDialog(this);
        progress.setTitle(getResources().getString( R.string.loading_title));
        progress.setMessage(getResources().getString( R.string.loading));
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();

        myWebViewMain.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
            @Override
            public void onPageFinished(WebView view, String url) {
                if (progress.isShowing()) {
                    progress.dismiss();
                }
            }
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(getApplicationContext(), "Error:" + description, Toast.LENGTH_SHORT).show();

            }
        });
        myWebViewMain.loadUrl(url);
    }

    //===============================================| Go to YouTube player intent
    private class ActionHandler implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if(view.getId()==R.id.mahfil_video_live){
                new LoadData().execute();
            }
            /*if(view.getId()==R.id.mahfil_video_30){
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/playlist?list=PLXqygJSDTcyimmA0KqstanY5CVdHIayuo")));
            }
            if(view.getId()==R.id.mahfil_video_31){
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/playlist?list=PLXqygJSDTcygBtcpqnYSUlqmfn8QRwzow")));
            }
            if(view.getId()==R.id.mahfil_video_32){
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/playlist?list=PLXqygJSDTcyg0gMMZoSHN_JZ6VtfRBdH4")));
            }*/
        }
    }

    //===============================================| Load live video URL from web server
    private class LoadData extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            String url = "http://app.shahadat-e-karbala.com/live_video.php";
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
                //Log.d("MahfilVideoActivity", String.valueOf(result));
                return result;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            //myWebView.loadUrl(result);
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(result)));
        }
    }

    //===============================================| For Activity Starting and Closing
    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }



}
