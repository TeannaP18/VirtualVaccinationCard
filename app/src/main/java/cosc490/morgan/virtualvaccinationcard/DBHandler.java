package cosc490.morgan.virtualvaccinationcard;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    //name of db
    private static final String DB_NAME = "usersDB";

    //database version
    private static final int DB_VERSION = 1;

    //table name
    private static final String TABLE_NAME = "user_data";

    //ALL DB COLUMNS BELOW//
    //id
    private static final String ID_COL = "id";
    //name
    public static final String NAME_COL = "name";
    //password
    public static final String PASSWORD_COL = "password";
    //vaccination provider
    public static final String VAC_PROVIDER_COL = "vaccine_provider";
    //dose 1 date
    public static final String DOSE1_DATE_COL = "dose1_date";
    //dose 1 #
    public static final String DOSE1_NUM_COL = "dose1_num";
    //dose 2 date
    public static final String DOSE2_DATE_COL = "dose2_date";
    //dose 2 #
    public static final String DOSE2_NUM_COL = "dose2_num";
    //booster date
    public static final String BOOSTER_DATE_COL = "booster_date";
    //booster #
    public static final String BOOSTER_NUM_COL = "booster_num";
    //image of physical card
    public static final String CARD_PHOTO_COL = "card_photo";
    //approval status
    public static final String  APPROVAL_COL = "approval";

    //constructor for DB handler
    public DBHandler(Context context){
        super(context, DB_NAME, null, DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                //+ ID_COL + "INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL+ " TEXT, "
                + PASSWORD_COL+ " TEXT, "
                + VAC_PROVIDER_COL+ " TEXT, "
                + DOSE1_DATE_COL + " DATE, "
                + DOSE1_NUM_COL + " TEXT, "
                + DOSE2_DATE_COL + " DATE, "
                + DOSE2_NUM_COL + " TEXT, "
                + BOOSTER_DATE_COL + " DATE, "
                + BOOSTER_NUM_COL + " TEXT, "
                + CARD_PHOTO_COL + " TEXT, "
                + APPROVAL_COL + " INTEGER)";
        sqLiteDatabase.execSQL(query);

    }

    public void addNewRecord(String userName, String password, String vac_provider, String dose1_date, String dose1_num,
                             String dose2_date, String dose2_num, String booster_date, String booster_num, String card_photo){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(NAME_COL, userName);
        values.put(PASSWORD_COL, password);
        values.put(VAC_PROVIDER_COL, vac_provider);
        values.put(DOSE1_DATE_COL, dose1_date);
        values.put(DOSE1_NUM_COL, dose1_num);
        values.put(DOSE2_DATE_COL, dose2_date);
        values.put(DOSE2_NUM_COL, dose2_num);
        values.put(BOOSTER_DATE_COL, booster_date);
        values.put(BOOSTER_NUM_COL, booster_num);
        values.put(CARD_PHOTO_COL, card_photo);

        db.insert(TABLE_NAME, null, values);
        db.close();

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);

    }

    public ArrayList<VaccinationModal> readVaccinations(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorVaccinations = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        ArrayList<VaccinationModal> vaccinationModalArrayList = new ArrayList<>();
        if(cursorVaccinations.moveToFirst()){
            do{
                vaccinationModalArrayList.add(new VaccinationModal(cursorVaccinations.getString(1),
                        cursorVaccinations.getString(2),
                        cursorVaccinations.getString(3),
                        cursorVaccinations.getString(4),
                        cursorVaccinations.getString(5),
                        cursorVaccinations.getString(6),
                        cursorVaccinations.getString(7),
                        cursorVaccinations.getString(8)));
            }while (cursorVaccinations.moveToNext());
        }
        cursorVaccinations.close();
        return vaccinationModalArrayList;
    }
}
