package shahadat_e_karbala.com.shahadatekarbala.login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.sql.Timestamp;
import java.util.ArrayList;

import shahadat_e_karbala.com.shahadatekarbala.database.ConstantKey;
import shahadat_e_karbala.com.shahadatekarbala.database.SQLiteDAO;

public class LoginService {

    private SQLiteDAO dao;
    private ArrayList<LoginModel> arrayList;

    public LoginService(Context context) {
        arrayList = new ArrayList<>();
        dao = new SQLiteDAO(context);
    }

    public long addData(LoginModel model){
        final ContentValues values = new ContentValues();
        values.put(ConstantKey.LOGIN_COLUMN1, model.getLoginName());
        values.put(ConstantKey.LOGIN_COLUMN2, model.getLoginMobile());
        values.put(ConstantKey.LOGIN_COLUMN3, "created by kamal");
        values.put(ConstantKey.LOGIN_COLUMN4, "");
        values.put(ConstantKey.LOGIN_COLUMN5, String.valueOf(new Timestamp(System.currentTimeMillis())));

        return dao.addData(ConstantKey.LOGIN_TABLE_NAME, values);
    }

    public ArrayList<LoginModel> getAllData(){
        arrayList = new ArrayList<>();
        Cursor cursor = dao.getAllData(ConstantKey.SELECT_LOGIN_TABLE);
        if(cursor.moveToFirst()){
            do{
                String id = cursor.getString(cursor.getColumnIndex(ConstantKey.COLUMN_ID));
                String name = cursor.getString(cursor.getColumnIndex(ConstantKey.LOGIN_COLUMN1));
                String mobile = cursor.getString(cursor.getColumnIndex(ConstantKey.LOGIN_COLUMN2));
                String createdById = cursor.getString(cursor.getColumnIndex(ConstantKey.LOGIN_COLUMN3));
                String updatedById = cursor.getString(cursor.getColumnIndex(ConstantKey.LOGIN_COLUMN4));
                String createdAt = cursor.getString(cursor.getColumnIndex(ConstantKey.LOGIN_COLUMN5));

                LoginModel model = new LoginModel(id, name, mobile, createdById, updatedById, createdAt);
                arrayList.add(model);
            }while(cursor.moveToNext());
        }
        return arrayList;
    }
}
