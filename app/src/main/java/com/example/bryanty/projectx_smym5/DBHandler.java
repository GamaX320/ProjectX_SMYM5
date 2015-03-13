package com.example.bryanty.projectx_smym5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.bryanty.projectx_smym5.domain.Account;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BRYANTY on 12/03/2015.
 */
public class DBHandler extends SQLiteOpenHelper{
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "financial.db";
    public static final String TABLE_NAME ="accounts";

    public static final String COLUMN_ACC_ID ="accID";
    public static final String COLUMN_ACC_NAME ="accName";
    public static final String COLUMN_ACC_COLOR ="accColor";
    public static final String COLUMN_ACC_AMOUNT ="accAmount";

    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);

        //delete database
        //context.deleteDatabase(DATABASE_NAME);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query= "CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_ACC_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_ACC_NAME + " TEXT," +
                COLUMN_ACC_COLOR + " INTEGER," +
                COLUMN_ACC_AMOUNT + " DOUBLE);";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query= "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(query);

        onCreate(db);
    }

    //add a new row to database
    public void addAccount(Account acoount){
        ContentValues values = new ContentValues();
        values.put(COLUMN_ACC_NAME, acoount.get_accName());
        values.put(COLUMN_ACC_COLOR, acoount.get_accColor());
        values.put(COLUMN_ACC_AMOUNT, acoount.get_accAmount());
        SQLiteDatabase db= getWritableDatabase();
        db.insert(TABLE_NAME,null,values);
        db.close();
    }

    //delete a row from database
    public void deleteAccount(String accountName){
        SQLiteDatabase db= getWritableDatabase();
        db.execSQL("DELETE FROM  "+TABLE_NAME +" WHERE "+COLUMN_ACC_NAME + "=\"" +accountName +"\";");
    }

    //retrieve all row from database
    public List<Account> getAllAccount(){
        String query="SELECT * FROM "+TABLE_NAME +" WHERE 1";
        SQLiteDatabase db= getWritableDatabase();

        List<Account> records = new ArrayList<Account>();
        //cursor point to your location
        Cursor cursor = db.rawQuery(query, null);
        //move to first row in your result
        cursor.moveToFirst();

        while(!cursor.isAfterLast()){
            if(cursor.getString(cursor.getColumnIndex("accName")) != null){
                Account userRecord = new Account();
                userRecord.set_accID(cursor.getInt(0));
                userRecord.set_accName(cursor.getString(1));
                userRecord.set_accColor(cursor.getInt(2));
                userRecord.set_accAmount(cursor.getDouble(3));

                records.add(userRecord);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return records;
    }

}
