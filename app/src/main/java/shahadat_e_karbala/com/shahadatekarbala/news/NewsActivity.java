package shahadat_e_karbala.com.shahadatekarbala.news;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import shahadat_e_karbala.com.shahadatekarbala.R;
import shahadat_e_karbala.com.shahadatekarbala.language.LocaleHelper;

public class NewsActivity extends AppCompatActivity {

    private ProgressDialog progress;
    private WebView myWebViewMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        //===============================================| Change Actionbar Title
        ActionBar bar = getSupportActionBar();
        bar.setTitle(getResources().getString(R.string.news_title));

        myWebViewMain = (WebView) findViewById(R.id.news_web_view_id);
        startWebView("http://shahadat-e-karbala.com/category/news/");
    }

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

    //===============================================| Language Change
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase));
    }
}
