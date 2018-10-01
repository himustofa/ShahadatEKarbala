package shahadat_e_karbala.com.shahadatekarbala.registration;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.sql.Timestamp;
import java.util.ArrayList;

import shahadat_e_karbala.com.shahadatekarbala.database.ConstantKey;
import shahadat_e_karbala.com.shahadatekarbala.database.SQLiteDAO;

public class RegistrationService {

    private SQLiteDAO dao;
    private ArrayList<RegistrationModel> arrayList;

    public RegistrationService(Context context) {
        arrayList = new ArrayList<>();
        dao = new SQLiteDAO(context);
    }

    public long addData(RegistrationModel model){
        final ContentValues values = new ContentValues();
        values.put(ConstantKey.REG_COLUMN1, model.getRegName());
        values.put(ConstantKey.REG_COLUMN2, model.getRegFatherName());
        values.put(ConstantKey.REG_COLUMN3, model.getRegMotherName());
        values.put(ConstantKey.REG_COLUMN4, model.getRegRelation());
        values.put(ConstantKey.REG_COLUMN5, model.getRegBirth());
        values.put(ConstantKey.REG_COLUMN6, model.getRegBloodGroup());
        values.put(ConstantKey.REG_COLUMN7, model.getRegMotherName());
        values.put(ConstantKey.REG_COLUMN8, model.getRegNid());
        values.put(ConstantKey.REG_COLUMN9, model.getRegEmail());
        values.put(ConstantKey.REG_COLUMN10, model.getRegPresentAddress());
        values.put(ConstantKey.REG_COLUMN11, model.getRegPermanentAddress());
        values.put(ConstantKey.REG_COLUMN12, model.getRegPhotoName());
        values.put(ConstantKey.REG_COLUMN13, model.getRegPhotoPath());
        values.put(ConstantKey.REG_COLUMN14, "created_by_id");
        values.put(ConstantKey.REG_COLUMN15, "updated_by_id");
        values.put(ConstantKey.REG_COLUMN16, String.valueOf(new Timestamp(System.currentTimeMillis())));

        return dao.addData(ConstantKey.REGISTRATION_TABLE_NAME, values);
    }

    public ArrayList<RegistrationModel> getAllData(){
        arrayList = new ArrayList<>();
        Cursor cursor = dao.getAllData(ConstantKey.SELECT_REGISTRATION_TABLE);
        if(cursor.moveToFirst()){
            do{
                String id = cursor.getString(cursor.getColumnIndex(ConstantKey.COLUMN_ID));
                String name = cursor.getString(cursor.getColumnIndex(ConstantKey.REG_COLUMN1));
                String father = cursor.getString(cursor.getColumnIndex(ConstantKey.REG_COLUMN2));
                String mother = cursor.getString(cursor.getColumnIndex(ConstantKey.REG_COLUMN3));
                String relation = cursor.getString(cursor.getColumnIndex(ConstantKey.REG_COLUMN4));
                String birth = cursor.getString(cursor.getColumnIndex(ConstantKey.REG_COLUMN5));
                String blood = cursor.getString(cursor.getColumnIndex(ConstantKey.REG_COLUMN6));
                String mobile = cursor.getString(cursor.getColumnIndex(ConstantKey.REG_COLUMN7));
                String nid = cursor.getString(cursor.getColumnIndex(ConstantKey.REG_COLUMN8));
                String email = cursor.getString(cursor.getColumnIndex(ConstantKey.REG_COLUMN9));
                String present = cursor.getString(cursor.getColumnIndex(ConstantKey.REG_COLUMN10));
                String permanent = cursor.getString(cursor.getColumnIndex(ConstantKey.REG_COLUMN11));
                String photo = cursor.getString(cursor.getColumnIndex(ConstantKey.REG_COLUMN12));
                String path = cursor.getString(cursor.getColumnIndex(ConstantKey.REG_COLUMN13));
                String createdById = cursor.getString(cursor.getColumnIndex(ConstantKey.REG_COLUMN14));
                String updatedById = cursor.getString(cursor.getColumnIndex(ConstantKey.REG_COLUMN15));
                String createdAt = cursor.getString(cursor.getColumnIndex(ConstantKey.REG_COLUMN16));

                RegistrationModel model = new RegistrationModel(id, name, father, mother, relation, birth, blood, mobile, nid, email, present, permanent, photo, path, createdById, updatedById, createdAt);
                arrayList.add(model);
            }while(cursor.moveToNext());
        }
        return arrayList;
    }

}
