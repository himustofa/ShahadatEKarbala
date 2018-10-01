package shahadat_e_karbala.com.shahadatekarbala.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteDAO {

    private static final String DATABASE_NAME = "shahadat_app"; //shahadat_e_karbala
    private static final int DATABASE_VERSION = 1;

    private final SQLiteDatabase database;
    private final SQLiteOpenHelper helper;

    public SQLiteDAO(final Context context) {
        this.helper = new SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
            @Override
            public void onCreate(final SQLiteDatabase db) {
                db.execSQL(ConstantKey.CREATE_LOGIN_TABLE);
                db.execSQL(ConstantKey.CREATE_CONTACTS_TABLE);
                db.execSQL(ConstantKey.CREATE_REGISTRATION_TABLE);
            }
            @Override
            public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {
                db.execSQL(ConstantKey.DROP_LOGIN_TABLE);
                db.execSQL(ConstantKey.DROP_CONTACTS_TABLE);
                db.execSQL(ConstantKey.DROP_REGISTRATION_TABLE);
                this.onCreate(db);
            }
        };
        this.database = this.helper.getWritableDatabase();
    }

    public long addData(String tableName, ContentValues values) {
        long data = this.database.insert(tableName, null, values);
        return data;
    }

    public long deleteDataById(String tableName, final String id) {
        long data = this.database.delete(tableName, ConstantKey.COLUMN_ID + " = ?", new String[] { String.valueOf(id) });
        return data;
    }

    public Cursor getAllData(String query) {
        final Cursor cursor = this.database.rawQuery(query,null);
        return cursor;
    }
}
