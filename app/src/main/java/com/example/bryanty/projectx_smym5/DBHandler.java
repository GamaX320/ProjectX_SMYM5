package com.example.bryanty.projectx_smym5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.bryanty.projectx_smym5.domain.Account;
import com.example.bryanty.projectx_smym5.domain.Expense;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BRYANTY on 12/03/2015.
 */
public class DBHandler extends SQLiteOpenHelper{
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "financial.db";
    public static final String TABLE_NAME ="accounts";
    public static final String TABLE_NAME2 ="expense";

    //column for table account
    public static final String COLUMN_ACC_ID ="accID";
    public static final String COLUMN_ACC_NAME ="accName";
    public static final String COLUMN_ACC_COLOR ="accColor";
    public static final String COLUMN_ACC_AMOUNT ="accAmount";

    //column for table expense
    public static final String COLUMN_EXP_ID ="expID";
    public static final String COLUMN_EXP_TYPE ="expType";
    public static final String COLUMN_EXP_AMOUNT ="expAmount";
    public static final String COLUMN_EXP_DATE ="expDate";
    public static final String COLUMN_EXP_ACC_ID ="accID";

    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);

        //delete database
        //context.deleteDatabase(DATABASE_NAME);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //query for create table account
        String query= "CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_ACC_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_ACC_NAME + " TEXT," +
                COLUMN_ACC_COLOR + " INTEGER," +
                COLUMN_ACC_AMOUNT + " DOUBLE);";

        //query for create table expense
        String query2= "CREATE TABLE " + TABLE_NAME2 + "(" +
                COLUMN_EXP_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_EXP_TYPE + " TEXT," +
                COLUMN_EXP_AMOUNT + " DOUBLE," +
                COLUMN_EXP_DATE + " TEXT," +
                COLUMN_EXP_ACC_ID + " INTEGER, FOREIGN KEY ("+COLUMN_EXP_ACC_ID +") REFERENCES "+TABLE_NAME +" ("+COLUMN_ACC_ID +"));";

        db.execSQL(query);
        db.execSQL(query2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query= "DROP TABLE IF EXISTS " + TABLE_NAME;
        String query2= "DROP TABLE IF EXISTS " + TABLE_NAME2;

        db.execSQL(query);
        db.execSQL(query2);

        onCreate(db);
    }

    //add a new account to database
    public void addAccount(Account acoount){
        ContentValues values = new ContentValues();
        values.put(COLUMN_ACC_NAME, acoount.get_accName());
        values.put(COLUMN_ACC_COLOR, acoount.get_accColor());
        values.put(COLUMN_ACC_AMOUNT, acoount.get_accAmount());
        SQLiteDatabase db= getWritableDatabase();
        db.insert(TABLE_NAME,null,values);
        db.close();
    }

    //delete a account from database
    public void deleteAccount(String accountName){
        SQLiteDatabase db= getWritableDatabase();
        db.execSQL("DELETE FROM  "+TABLE_NAME +" WHERE "+COLUMN_ACC_NAME + "=\"" +accountName +"\";");
    }

    //retrieve all account from database
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
//============================================================================================================================================================
    //add a new expense to database
    public void addExpense(Expense expense){
        ContentValues values = new ContentValues();
        values.put(COLUMN_EXP_TYPE, expense.get_expType());
        values.put(COLUMN_EXP_AMOUNT, expense.get_expAmount());
        values.put(COLUMN_EXP_DATE, expense.get_expDate());
        values.put(COLUMN_EXP_ACC_ID, expense.get_accID());
        SQLiteDatabase db= getWritableDatabase();
        db.insert(TABLE_NAME2,null,values);
        db.close();
    }

    //delete a expense from database
    public void deleteExpense(int expenseID){
        SQLiteDatabase db= getWritableDatabase();
        db.execSQL("DELETE FROM  "+TABLE_NAME2 +" WHERE "+COLUMN_EXP_ID + "=\"" +expenseID +"\";");
    }

    //retrieve all expense from database
    public List<Expense> getAllExpense(){
        String query="SELECT * FROM "+TABLE_NAME2 +" WHERE 1";
        SQLiteDatabase db= getWritableDatabase();

        List<Expense> records = new ArrayList<Expense>();
        //cursor point to your location
        Cursor cursor = db.rawQuery(query, null);
        //move to first row in your result
        cursor.moveToFirst();

        while(!cursor.isAfterLast()){
            if(cursor.getString(cursor.getColumnIndex("expType")) != null){
                Expense userRecord = new Expense();
                userRecord.set_expID(cursor.getInt(0));
                userRecord.set_expType(cursor.getString(1));
                userRecord.set_expAmount(cursor.getDouble(2));
                userRecord.set_expDate(cursor.getString(3));
                userRecord.set_accID(cursor.getInt(4));

                records.add(userRecord);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return records;
    }

}
