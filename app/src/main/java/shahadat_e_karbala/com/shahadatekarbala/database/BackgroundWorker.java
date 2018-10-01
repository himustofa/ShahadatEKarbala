package shahadat_e_karbala.com.shahadatekarbala.database;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Timestamp;

public class BackgroundWorker extends AsyncTask<String,Void,String> {

    private Context context;
    private AlertDialog alertDialog;

    public BackgroundWorker(Context ctx) {
        context = ctx;
    }

    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        String login_url = "http://app.shahadat-e-karbala.com/login.php";
        String contacts_url = "http://app.shahadat-e-karbala.com/contacts.php";
        String registration_url = "http://app.shahadat-e-karbala.com/registration.php";

        if(type.equals("login")) {
            try {
                String logName = params[1];
                String logPhone = params[2];
                String created = "";
                String updated = "";
                String createdAt = String.valueOf(new Timestamp(System.currentTimeMillis()));

                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                String post_data = URLEncoder.encode("login_name","UTF-8")+"="+URLEncoder.encode(logName,"UTF-8")+"&"
                        +URLEncoder.encode("login_mobile","UTF-8")+"="+URLEncoder.encode(logPhone,"UTF-8")+"&"
                        +URLEncoder.encode("created_by_id","UTF-8")+"="+URLEncoder.encode(created,"UTF-8")+"&"
                        +URLEncoder.encode("updated_by_id","UTF-8")+"="+URLEncoder.encode(updated,"UTF-8")+"&"
                        +URLEncoder.encode("created_at","UTF-8")+"="+URLEncoder.encode(createdAt,"UTF-8");

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line = bufferedReader.readLine())!= null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if(type.equals("contacts")) {
            try {
                String logName = params[1];
                String logPhone = params[2];
                String contactName = params[3];
                String phoneNumber = params[4];
                String created = "";
                String updated = "";
                String createdAt = String.valueOf(new Timestamp(System.currentTimeMillis()));

                URL url = new URL(contacts_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                String post_data = URLEncoder.encode("login_name","UTF-8")+"="+URLEncoder.encode(logName,"UTF-8")+"&"
                        +URLEncoder.encode("login_mobile","UTF-8")+"="+URLEncoder.encode(logPhone,"UTF-8")+"&"
                        +URLEncoder.encode("contract_name","UTF-8")+"="+URLEncoder.encode(contactName,"UTF-8")+"&"
                        +URLEncoder.encode("contract_number","UTF-8")+"="+URLEncoder.encode(phoneNumber,"UTF-8")+"&"
                        +URLEncoder.encode("created_by_id","UTF-8")+"="+URLEncoder.encode(created,"UTF-8")+"&"
                        +URLEncoder.encode("updated_by_id","UTF-8")+"="+URLEncoder.encode(updated,"UTF-8")+"&"
                        +URLEncoder.encode("created_at","UTF-8")+"="+URLEncoder.encode(createdAt,"UTF-8");

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line = bufferedReader.readLine())!= null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if(type.equals("registration")) {
            try {
                String regName = params[1];
                String regFather = params[2];
                String regMother = params[3];
                String regRelation = params[4];
                String regBirth = params[5];
                String regBloodGroup = params[6];
                String regMobile = params[7];
                String regNid = params[8];
                String regEmail = params[9];
                String regPresent = params[10];
                String regPermanent = params[11];
                String userPhotoName = params[12];
                String absolutePath = params[13];
                String encodeImage = params[14];
                String created = "";
                String updated = "";
                String createdAt = String.valueOf(new Timestamp(System.currentTimeMillis()));

                Log.d("BackgroundWorker === : ", String.valueOf(regNid));

                URL url = new URL(registration_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                String post_data = URLEncoder.encode("reg_name","UTF-8")+"="+URLEncoder.encode(regName,"UTF-8")+"&"
                        +URLEncoder.encode("reg_father_name","UTF-8")+"="+URLEncoder.encode(regFather,"UTF-8")+"&"
                        +URLEncoder.encode("reg_mother_name","UTF-8")+"="+URLEncoder.encode(regMother,"UTF-8")+"&"
                        +URLEncoder.encode("reg_relation","UTF-8")+"="+URLEncoder.encode(regRelation,"UTF-8")+"&"
                        +URLEncoder.encode("reg_birth","UTF-8")+"="+URLEncoder.encode(regBirth,"UTF-8")+"&"
                        +URLEncoder.encode("reg_blood_group","UTF-8")+"="+URLEncoder.encode(regBloodGroup,"UTF-8")+"&"
                        +URLEncoder.encode("reg_mobile_number","UTF-8")+"="+URLEncoder.encode(regMobile,"UTF-8")+"&"
                        +URLEncoder.encode("regNid","UTF-8")+"="+URLEncoder.encode(regNid,"UTF-8")+"&"
                        +URLEncoder.encode("reg_email","UTF-8")+"="+URLEncoder.encode(regEmail,"UTF-8")+"&"
                        +URLEncoder.encode("reg_present_address","UTF-8")+"="+URLEncoder.encode(regPresent,"UTF-8")+"&"
                        +URLEncoder.encode("reg_permanent_address","UTF-8")+"="+URLEncoder.encode(regPermanent,"UTF-8")+"&"
                        +URLEncoder.encode("reg_photo_name","UTF-8")+"="+URLEncoder.encode(userPhotoName,"UTF-8")+"&"
                        +URLEncoder.encode("reg_photo_path","UTF-8")+"="+URLEncoder.encode(absolutePath,"UTF-8")+"&"
                        +URLEncoder.encode("encode_image","UTF-8")+"="+URLEncoder.encode(encodeImage,"UTF-8")+"&"
                        +URLEncoder.encode("created_by_id","UTF-8")+"="+URLEncoder.encode(created,"UTF-8")+"&"
                        +URLEncoder.encode("updated_by_id","UTF-8")+"="+URLEncoder.encode(updated,"UTF-8")+"&"
                        +URLEncoder.encode("created_at","UTF-8")+"="+URLEncoder.encode(createdAt,"UTF-8");

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line = bufferedReader.readLine())!= null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        //alertDialog.setTitle("Registration Status");
    }

    @Override
    protected void onPostExecute(String result) {
        alertDialog.setMessage(result);
        alertDialog.show();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}