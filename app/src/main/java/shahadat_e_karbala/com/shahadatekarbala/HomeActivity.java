package shahadat_e_karbala.com.shahadatekarbala;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import shahadat_e_karbala.com.shahadatekarbala.advertisement.AdvertisementActivity;
import shahadat_e_karbala.com.shahadatekarbala.books.BooksActivity;
import shahadat_e_karbala.com.shahadatekarbala.communication.CommunicationActivity;
import shahadat_e_karbala.com.shahadatekarbala.database.ConstantKey;
import shahadat_e_karbala.com.shahadatekarbala.donation.DonationActivity;
import shahadat_e_karbala.com.shahadatekarbala.founder.FounderActivity;
import shahadat_e_karbala.com.shahadatekarbala.language.LocaleHelper;
import shahadat_e_karbala.com.shahadatekarbala.mahfilvideo.MahfilVideoActivity;
import shahadat_e_karbala.com.shahadatekarbala.management.ManagementActivity;
import shahadat_e_karbala.com.shahadatekarbala.members.MembersActivity;
import shahadat_e_karbala.com.shahadatekarbala.news.NewsActivity;
import shahadat_e_karbala.com.shahadatekarbala.registration.RegistrationActivity;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private SharedPreferences preferences;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;

    private TextView textView;
    private LinearLayout btnMahfil, btnManagement, btnMembers, btnRegistration, btnBooks, btnAdvertisement, btnNews, btnOpinion, btnDonation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //===============================================| Change Actionbar Title
        ActionBar bar = getSupportActionBar();
        bar.setTitle(getResources().getString(R.string.home_title));

        //===============================================| To Display Navigation Bar Icon and Back
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_id);
        toggle = new ActionBarDrawerToggle(HomeActivity.this, drawerLayout, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView = (NavigationView) findViewById(R.id.navigation_id);
        navigationView.setNavigationItemSelectedListener(HomeActivity.this);

        //===============================================| Getting SharedPreferences
        preferences = getSharedPreferences("loginInfo", MODE_PRIVATE);
        boolean isLoggedIn = preferences.getBoolean("isLoggedIn", false);
        String name = preferences.getString("userName", "Data not found");
        //Toast.makeText(this, "SharedPreferences is "+isLoggedIn +" for "+name, Toast.LENGTH_SHORT).show();

        //===============================================| From MainActivity
        textView = (TextView) findViewById(R.id.tv_user_name);
        textView.setText(getIntent().getStringExtra(ConstantKey.USER_NAME_KEY));

        //===============================================| All Button initialize and fire intent by click event
        btnMahfil = findViewById(R.id.btn_mahfil);
        btnMahfil.setOnClickListener(new ActionHandler());
        btnManagement = findViewById(R.id.btn_management);
        btnManagement.setOnClickListener(new ActionHandler());
        btnMembers = findViewById(R.id.btn_members);
        btnMembers.setOnClickListener(new ActionHandler());
        btnRegistration = findViewById(R.id.btn_registration);
        btnRegistration.setOnClickListener(new ActionHandler());
        btnBooks = findViewById(R.id.btn_books);
        btnBooks.setOnClickListener(new ActionHandler());
        btnAdvertisement = findViewById(R.id.btn_advertisement);
        btnAdvertisement.setOnClickListener(new ActionHandler());
        btnNews = findViewById(R.id.btn_news);
        btnNews.setOnClickListener(new ActionHandler());
        btnOpinion = findViewById(R.id.btn_communication);
        btnOpinion.setOnClickListener(new ActionHandler());
        btnDonation = findViewById(R.id.btn_donation);
        btnDonation.setOnClickListener(new ActionHandler());

        //===============================================| Notice
        WebView notice = (WebView) findViewById(R.id.notice_web_view_id);
        if (LocaleHelper.getLanguage(this).contains("en")){
            notice.loadUrl("http://shahadat-e-karbala.com/notice-eng.php");
        } else {
            notice.loadUrl("http://shahadat-e-karbala.com/notice.php");
        }



    }

    //===============================================| Language Change
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase));
    }

    //===============================================| Go to mobile screen and background pause
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(toggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //===============================================| Navigation
    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        if(menuItem.getItemId()==R.id.mahfil_details_nav_id){
            getIntent(FounderActivity.class);
        }
        if(menuItem.getItemId()==R.id.website_nav_id){
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.shahadat-e-karbala.com")));
        }
        if(menuItem.getItemId()==R.id.facebook_nav_id){
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/shahadatekarbala/")));
        }
        if(menuItem.getItemId()==R.id.twitter_nav_id){
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/BayatAhle")));
        }
        if(menuItem.getItemId()==R.id.youtube_nav_id){
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/c/SUFITVONLINE")));
        }
        if(menuItem.getItemId()==R.id.email_nav_id){
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
            emailIntent.setData(Uri.parse("mailto:info@shahadat-e-karbala.com"));
            try {
                startActivity(emailIntent);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(getApplicationContext(), ""+e, Toast.LENGTH_SHORT);
            }
        }
        if(menuItem.getItemId()==R.id.about_nav_id){
            customAlertDialog();
        }
        return false;
    }

    private void getIntent(Class className) {
        Intent intent = new Intent(getApplicationContext(), className);
        startActivity(intent);
    }

    //===============================================| Custom AlertDialog
    private void customAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
        final View inflateForm = getLayoutInflater().inflate(R.layout.about_alert_dialog, null);
        builder.setView(inflateForm);
        builder.setCancelable(true);
        builder.create();

        builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Thank You", Toast.LENGTH_SHORT);
                dialog.dismiss();
            }
        });

        final AlertDialog dialog = builder.show();

        TextView txt = (TextView) inflateForm.findViewById(R.id.developed_by_id);
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.adroitsoft.net")));
                dialog.cancel();
            }
        });

    }

    //===============================================| Intent
    private class ActionHandler implements View.OnClickListener {
        Intent intent;
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_mahfil:
                    getIntent(MahfilVideoActivity.class);
                    break;
                case R.id.btn_management:
                    getIntent(ManagementActivity.class);
                    break;
                case R.id.btn_members:
                    getIntent(MembersActivity.class);
                    break;
                case R.id.btn_registration:
                    getIntent(RegistrationActivity.class);
                    break;
                case R.id.btn_books:
                    getIntent(BooksActivity.class);
                    break;
                case R.id.btn_advertisement:
                    getIntent(AdvertisementActivity.class);
                    break;
                case R.id.btn_news:
                    getIntent(NewsActivity.class);
                    break;
                case R.id.btn_communication:
                    getIntent(CommunicationActivity.class);
                    break;
                case R.id.btn_donation:
                    getIntent(DonationActivity.class);
                    break;
                default:
                    break;
            }
        }
        private void getIntent(Class className) {
            intent = new Intent(getApplicationContext(), className);
            startActivity(intent);
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
