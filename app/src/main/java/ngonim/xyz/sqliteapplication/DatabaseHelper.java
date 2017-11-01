package ngonim.xyz.sqliteapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

/**
 * Created by ngoni on 4/12/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "contacts.db";
    private static final String TABLE_NAME = "contacts_table";
    private static final String COL_1 = "ID";
    private static final String COL_2 = "NAME";
    private static final String COL_3 = "SURNAME";
    private static final String COL_4 = "AGE";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, SURNAME TEXT, AGE INTEGER )");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    @NonNull
    private ContentValues getContentValues(String name, String surname, String age) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, surname);
        contentValues.put(COL_4, age);
        return contentValues;
    }

    @NonNull
    private ContentValues getContentValues(String name, String surname, String age, String id) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, surname);
        contentValues.put(COL_4, age);
        return contentValues;
    }

    public boolean insertData(String name, String surname, String age) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = getContentValues(name, surname, age);
        long result = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);

        if (result == -1) {
            return false;
        } else
            return true;
    }

    public boolean updateData(String id, String name, String surname, String age){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = getContentValues(id, name, surname, age);
        sqLiteDatabase.update(TABLE_NAME, contentValues ,"ID = ?", new String[]{id});
        return true;
    }

    public Integer deleteData(String id){
        SQLiteDatabase database = this.getWritableDatabase();
       return database.delete(TABLE_NAME,"ID = ?",new String[]{id});
    }


    public Cursor getAllData(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.rawQuery("select * from " + TABLE_NAME, null);

    }

}
