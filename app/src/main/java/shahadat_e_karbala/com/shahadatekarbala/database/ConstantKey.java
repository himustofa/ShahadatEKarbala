package shahadat_e_karbala.com.shahadatekarbala.database;

public class ConstantKey {

    public static final String USER_NAME_KEY = "userName";
    //public static final String USER_MOBILE_KEY = "userMobile";

    public final static String COLUMN_ID = "id";

    //=======================| LoginModel |=======================
    public final static String LOGIN_TABLE_NAME = "login_table";
    public final static String LOGIN_COLUMN1 = "login_name";
    public final static String LOGIN_COLUMN2 = "login_mobile";
    public final static String LOGIN_COLUMN3 = "created_by_id";
    public final static String LOGIN_COLUMN4 = "updated_by_id";
    public final static String LOGIN_COLUMN5 = "created_at";

    public final static String CREATE_LOGIN_TABLE = "CREATE TABLE " + LOGIN_TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + LOGIN_COLUMN1 + " TEXT, " + LOGIN_COLUMN2 + " TEXT, " + LOGIN_COLUMN3 + " TEXT, " + LOGIN_COLUMN4 + " TEXT, " + LOGIN_COLUMN5 + " TEXT ) ";
    public final static String DROP_LOGIN_TABLE = "DROP TABLE IF EXISTS " + LOGIN_TABLE_NAME + " ";
    public final static String SELECT_LOGIN_TABLE = "SELECT * FROM " + LOGIN_TABLE_NAME;

    //=======================| ContactsModel |=======================
    public final static String CONTACTS_TABLE_NAME = "contacts_table";
    public final static String CONTACTS_COLUMN1 = "login_name";
    public final static String CONTACTS_COLUMN2 = "login_mobile";
    public final static String CONTACTS_COLUMN3 = "contract_name";
    public final static String CONTACTS_COLUMN4 = "contract_number";
    public final static String CONTACTS_COLUMN5 = "created_by_id";
    public final static String CONTACTS_COLUMN6 = "updated_by_id";
    public final static String CONTACTS_COLUMN7 = "created_at";

    public final static String CREATE_CONTACTS_TABLE = "CREATE TABLE " + CONTACTS_TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + CONTACTS_COLUMN1 + " TEXT, " + CONTACTS_COLUMN2 + " TEXT, " + CONTACTS_COLUMN3 + " TEXT, " + CONTACTS_COLUMN4 + " TEXT, " + CONTACTS_COLUMN5 + " TEXT, " + CONTACTS_COLUMN6 + " TEXT, " + CONTACTS_COLUMN7 + " TEXT ) ";
    public final static String DROP_CONTACTS_TABLE = "DROP TABLE IF EXISTS " + CONTACTS_TABLE_NAME + " ";
    //public final static String SELECT_CONTACTS_TABLE = "SELECT * FROM " + CONTACTS_TABLE_NAME;

    //=======================| RegistrationModel |=======================
    public final static String REGISTRATION_TABLE_NAME = "registration_table";
    public static final String REG_COLUMN1 = "reg_name";
    public static final String REG_COLUMN2 = "reg_father_name";
    public static final String REG_COLUMN3 = "reg_mother_name";
    public static final String REG_COLUMN4 = "reg_relation";
    public static final String REG_COLUMN5 = "reg_birth";
    public static final String REG_COLUMN6 = "reg_blood_group";
    public static final String REG_COLUMN7 = "reg_mobile_number";
    public static final String REG_COLUMN8 = "reg_nid";
    public static final String REG_COLUMN9 = "reg_email";
    public static final String REG_COLUMN10 = "reg_present_address";
    public static final String REG_COLUMN11 = "reg_permanent_address";
    public final static String REG_COLUMN12 = "reg_photo_name";
    public final static String REG_COLUMN13 = "reg_photo_path";
    public static final String REG_COLUMN14 = "created_by_id";
    public static final String REG_COLUMN15 = "updated_by_id";
    public static final String REG_COLUMN16 = "created_at";

    public final static String CREATE_REGISTRATION_TABLE = "CREATE TABLE " + REGISTRATION_TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY, " + REG_COLUMN1 + " TEXT, " + REG_COLUMN2 + " TEXT, " + REG_COLUMN3 + " TEXT, " + REG_COLUMN4 + " TEXT, " + REG_COLUMN5 + " TEXT, " + REG_COLUMN6 + " TEXT, " + REG_COLUMN7 + " TEXT, " + REG_COLUMN8 + " TEXT, " + REG_COLUMN9 + " TEXT, " + REG_COLUMN10 + " TEXT, " + REG_COLUMN11 + " TEXT, " + REG_COLUMN12 + " TEXT, " + REG_COLUMN13 + " TEXT, " + REG_COLUMN14 + " TEXT, " + REG_COLUMN15 + " TEXT, " + REG_COLUMN16 + " TEXT ) ";
    public final static String DROP_REGISTRATION_TABLE = "DROP TABLE IF EXISTS " + REGISTRATION_TABLE_NAME + " ";
    public final static String SELECT_REGISTRATION_TABLE = "SELECT * FROM " + REGISTRATION_TABLE_NAME;

}
