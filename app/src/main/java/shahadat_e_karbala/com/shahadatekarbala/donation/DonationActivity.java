package shahadat_e_karbala.com.shahadatekarbala.donation;

import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import shahadat_e_karbala.com.shahadatekarbala.R;
import shahadat_e_karbala.com.shahadatekarbala.language.LocaleHelper;

public class DonationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation);

        ActionBar bar = getSupportActionBar();
        bar.setTitle(getResources().getString(R.string.donation_title));
    }

    //===============================================| Language Change
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase));
    }

}
