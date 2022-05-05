package cosc490.morgan.virtualvaccinationcard;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Base64;

//This class:
//performs all sql/database operations
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

    private VaccinationRVAdapter vaccinationRVAdapter;

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
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
                + CARD_PHOTO_COL + " BLOB, "
                + APPROVAL_COL + " INTEGER)";
        sqLiteDatabase.execSQL(query);

    }

    public void addNewRecord(String userName, String password, String vac_provider, String dose1_date, String dose1_num,
                             String dose2_date, String dose2_num, String booster_date, String booster_num, String imageString){
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
        values.put(CARD_PHOTO_COL, imageString);
        values.put(APPROVAL_COL, 0);

        db.insert(TABLE_NAME, null, values);
        db.close();

    }

    //method for updating record
    public void updateApprovalColumn(String TABLE_NAME, String ID_COL, String COL_NAME, int recordId, int newApproval){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NAME, newApproval);
        db.update(TABLE_NAME, values, ID_COL+ "= ?", new String[]{String.valueOf(recordId)});
    }

//    public void updateRecord(int id, String original_name, int approval_status, String userName, String password, String vac_provider, String dose1_date, String dose1_num,
//                                                           String dose2_date, String dose2_num, String booster_date, String booster_num, String imageString){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//
//        original_name = "user name";
//
//        values.put(ID_COL, id);
//        values.put(NAME_COL, userName);
//        values.put(PASSWORD_COL, password);
//        values.put(VAC_PROVIDER_COL, vac_provider);
//        values.put(DOSE1_DATE_COL, dose1_date);
//        values.put(DOSE1_NUM_COL, dose1_num);
//        values.put(DOSE2_DATE_COL, dose2_date);
//        values.put(DOSE2_NUM_COL, dose2_num);
//        values.put(BOOSTER_DATE_COL, booster_date);
//        values.put(BOOSTER_NUM_COL, booster_num);
//        values.put(CARD_PHOTO_COL, imageString);
//        values.put(APPROVAL_COL, approval_status);
//
//        db.update(TABLE_NAME, values, "name=?",new String[]{original_name});
//        db.close();
//    }

//    public void appendApproval(){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        Cursor cursor;
//        VaccinationModal modal = null;
//        int recordId = modal.getID();
//        //select query
//        cursor =  db.rawQuery("select * from " + TABLE_NAME + " where " + ID_COL + "=" + recordId, null);
//        //append approval status to db
//        values.put(APPROVAL_COL, 1);
//    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);

    }

    public ArrayList<VaccinationModal> readVaccinations(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorVaccinations = db.rawQuery("SELECT name, vaccine_provider, dose1_date, dose1_num, dose2_date, dose2_num, booster_date, booster_num, card_photo, approval FROM " + TABLE_NAME, null);

        ArrayList<VaccinationModal> vaccinationModalArrayList = new ArrayList<>();
        if(cursorVaccinations.moveToFirst()){
            do{
                vaccinationModalArrayList.add(new VaccinationModal(cursorVaccinations.getString(0),
                        cursorVaccinations.getString(1),
                        cursorVaccinations.getString(2),
                        cursorVaccinations.getString(3),
                        cursorVaccinations.getString(4),
                        cursorVaccinations.getString(5),
                        cursorVaccinations.getString(6),
                        cursorVaccinations.getString(7),
                        cursorVaccinations.getString(8),
                        cursorVaccinations.getInt(9)));
            }while (cursorVaccinations.moveToNext());
        }
        cursorVaccinations.close();
        return vaccinationModalArrayList;
    }

    //method to get the record by id
    public VaccinationModal getRecordById(int recordId)
    {

        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor=null;
        VaccinationModal modal = null;
        cursor =  database.rawQuery("select * from " + TABLE_NAME + " where " + ID_COL + "=" + recordId, null);
        if (cursor != null)
        {
            if (cursor.moveToFirst())
            {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(ID_COL));
                modal.setId(id);
            }
            cursor.close();
        }
        return modal;
    }

    //method to convert string to bitmap
    @RequiresApi(api = Build.VERSION_CODES.O)
    public Bitmap convertBase64String(String base64String){
        byte[] decodedBase64String = Base64.getDecoder().decode(base64String);
        Bitmap decodedByteArray = BitmapFactory.decodeByteArray(decodedBase64String, 0, base64String.length());
        return decodedByteArray;
    }
    //method to convert bitmap to image again
//    public Image convertBitmap(Bitmap decodedByteArray){
//
//
//        return;
//    }


}
