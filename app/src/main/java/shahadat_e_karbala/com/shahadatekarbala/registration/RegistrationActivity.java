package shahadat_e_karbala.com.shahadatekarbala.registration;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import shahadat_e_karbala.com.shahadatekarbala.R;
import shahadat_e_karbala.com.shahadatekarbala.database.BackgroundWorker;
import shahadat_e_karbala.com.shahadatekarbala.language.LocaleHelper;

public class RegistrationActivity extends AppCompatActivity {

    private static final int RESULT_LOAD_IMAGE = 1;
    private OutputStream output;
    private String imagePath;
    private String userPhotoName;
    private ImageView userPhoto;

    private RegistrationModel model;
    private RegistrationService service;

    private EditText regName, regFather, regMother, regBirth, regMobile, regNid, regEmail, regPresent, regPermanent;
    private Button regSubmit;
    private Spinner regRelation, regBloodGroup;
    private DatePicker picker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        //===============================================| Change Actionbar Title
        ActionBar bar = getSupportActionBar();
        bar.setTitle(getResources().getString(R.string.registration_title));

        this.service = new RegistrationService(this); //To get database from registration service

        userPhoto = (ImageView) findViewById(R.id.reg_photo);
        regName = (EditText) findViewById(R.id.reg_name);
        regFather = (EditText) findViewById(R.id.reg_father_name);
        regMother = (EditText) findViewById(R.id.reg_mother_name);
        regRelation = (Spinner) findViewById(R.id.spinner_reg_relation);
        regBirth = (EditText) findViewById(R.id.reg_birth);
        regBloodGroup = (Spinner) findViewById(R.id.spinner_reg_blood_group);
        regMobile = (EditText) findViewById(R.id.reg_mobile);
        regNid = (EditText) findViewById(R.id.reg_nid);
        regEmail = (EditText) findViewById(R.id.reg_email);
        regPresent = (EditText) findViewById(R.id.reg_present_address);
        regPermanent = (EditText) findViewById(R.id.reg_permanent_address);
        regSubmit = (Button) findViewById(R.id.reg_submit);

        regBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                birthDate();
            }
        });

        userPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
            }
        });


        regSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                model = new RegistrationModel(
                        regName.getText().toString(),
                        regFather.getText().toString(),
                        regMother.getText().toString(),
                        regRelation.getSelectedItem().toString(),
                        regBirth.getText().toString(),
                        regBloodGroup.getSelectedItem().toString(),
                        regMobile.getText().toString(),
                        regNid.getText().toString(),
                        regEmail.getText().toString(),
                        regPresent.getText().toString(),
                        regPermanent.getText().toString(),
                        userPhotoName,
                        new File(getFilesDir() + "/RegPassportPhoto/").getAbsolutePath()
                );


                if(!regName.getText().toString().trim().isEmpty() && !regMobile.getText().toString().trim().isEmpty()) {
                    long data = RegistrationActivity.this.service.addData(model);
                    insertRegistrationIntoServer(regName.getText().toString(), regFather.getText().toString(), regMother.getText().toString(), regRelation.getSelectedItem().toString(), regBirth.getText().toString(), regBloodGroup.getSelectedItem().toString(), regMobile.getText().toString(), regNid.getText().toString(), regEmail.getText().toString(), regPresent.getText().toString(), regPermanent.getText().toString(), userPhotoName, new File(getFilesDir() + "/RegPassportPhoto/").getAbsolutePath()); //MySQL Web Server
                    clearInputFields(regName, regFather, regMother, regRelation, regBirth, regBloodGroup, regMobile, regNid, regEmail, regPresent, regPermanent, userPhoto); //Clear fields
                    if (data > 0){
                        Bitmap bitmap = ((BitmapDrawable)userPhoto.getDrawable()).getBitmap(); //image save
                        imagePath = saveToInternalStorage(bitmap);
                        if(imagePath != null){
                            Toast.makeText(getApplicationContext(), "Logo saved successfully", Toast.LENGTH_SHORT).show();
                        }
                        Toast.makeText(getApplicationContext(),"Saved successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(),"Do not saved unsuccessfully", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(),"Please insert values into the red color fields", Toast.LENGTH_LONG).show();
                }



            }
        });


    }

    //===============================================| Language Change
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase));
    }

    //===============================================| Clear fields
    private void clearInputFields(EditText regName, EditText regFather, EditText regMother, Spinner regRelation, EditText regBirth, Spinner regBloodGroup, EditText regMobile, EditText regNid, EditText regEmail, EditText regPresent, EditText regPermanent, ImageView userPhoto) {
        regName.setText("");
        regFather.setText("");
        regMother.setText("");
        regBirth.setText("");
        regMobile.setText("");
        regNid.setText("");
        regEmail.setText("");
        regPresent.setText("");
        regPermanent.setText("");
        //userPhoto.setBackgroundResource(R.drawable.add_photo_icon);
        userPhoto.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.add_photo_icon));
    }

    //===============================================| Registration for MySQL Web Server
    public void insertRegistrationIntoServer(String regName, String regFather, String regMother, String regRelation, String regBirth, String regBloodGroup, String regMobile, String regNid, String regEmail, String regPresent, String regPermanent, String userPhotoName, String absolutePath){
        String type = "registration";
        String encodeImage = imageToServer();
        BackgroundWorker worker = new BackgroundWorker(this);
        worker.execute(type, regName, regFather, regMother, regRelation, regBirth, regBloodGroup, regMobile, regNid, regEmail, regPresent, regPermanent, userPhotoName, absolutePath, encodeImage);
    }

    //Encoding from passport image
    public String imageToServer(){
        Bitmap bitmap = ((BitmapDrawable)userPhoto.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        String encode = Base64.encodeToString(stream.toByteArray(), Base64.DEFAULT);
        return encode;
    }

    //===============================================| Birthday date-picker
    private void birthDate() {
        picker = new DatePicker(this);
        int curYear = picker.getYear();
        int curMonth = picker.getMonth()+1;
        int curDayOfMonth = picker.getDayOfMonth();
        DatePickerDialog pickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                regBirth.setText(dayOfMonth+"/"+(month+1)+"/"+year);
            }
        }, curYear, curMonth, curDayOfMonth);
        pickerDialog.show();
    }


    //===============================================| For Image
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null){
            Uri selectImage = data.getData();
            userPhotoName = "img_" + new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date()) + ".png";
            userPhoto.setImageURI(selectImage);
        }
    }

    protected String saveToInternalStorage(Bitmap bitmapImage){
        File directory = new File(getFilesDir() + "/RegPassportPhoto/");
        try {
            directory.mkdir(); //Create imageDir
            File file = new File(directory,userPhotoName);
            output = new FileOutputStream(file);
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, output); // Compress into png format image from 0% - 100%
            output.flush();
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return directory.getAbsolutePath();
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
