package com.example.bryanty.projectx_smym5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.bryanty.projectx_smym5.domain.Account;
import com.example.bryanty.projectx_smym5.domain.Expense;
import com.example.bryanty.projectx_smym5.domain.History;

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
    public static final String TABLE_NAME3 ="history";

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

    //column for table history
    public static final String COLUMN_HIS_ID ="hisID";
    public static final String COLUMN_HIS_TYPE ="hisType";
    public static final String COLUMN_HIS_DATE ="hisDate";

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

        //query for create table history
        String query3= "CREATE TABLE " + TABLE_NAME3 + "(" +
                COLUMN_HIS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_HIS_TYPE + " TEXT," +
                COLUMN_HIS_DATE + " TEXT);";

        db.execSQL(query);
        db.execSQL(query2);
        db.execSQL(query3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query= "DROP TABLE IF EXISTS " + TABLE_NAME;
        String query2= "DROP TABLE IF EXISTS " + TABLE_NAME2;
        String query3= "DROP TABLE IF EXISTS " + TABLE_NAME3;

        db.execSQL(query);
        db.execSQL(query2);
        db.execSQL(query3);

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
    public void deleteAccount(int accountID){
        SQLiteDatabase db= getWritableDatabase();
        db.execSQL("DELETE FROM  "+TABLE_NAME +" WHERE "+COLUMN_ACC_ID + "=\"" +accountID +"\";");
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

    //retrieve account from database
    public Account getAccount(int accountID){
        String query="SELECT * FROM  "+TABLE_NAME +" WHERE "+COLUMN_ACC_ID + "=\"" +accountID +"\";";
        SQLiteDatabase db= getWritableDatabase();

        Account account =new Account();
        //cursor point to your location
        Cursor cursor = db.rawQuery(query, null);
        //move to first row in your result
        cursor.moveToFirst();

        while(!cursor.isAfterLast()){
            if(cursor.getString(cursor.getColumnIndex("accName")) != null){
                account.set_accID(cursor.getInt(0));
                account.set_accName(cursor.getString(1));
                account.set_accColor(cursor.getInt(2));
                account.set_accAmount(cursor.getDouble(3));

                cursor.moveToNext();
            }
        }
        cursor.close();
        return account;
    }

    public boolean updateAccount(Account account,int method){
        boolean result=false;

        ContentValues values = new ContentValues();
        SQLiteDatabase db= getWritableDatabase();
        Double amount=0.0;

        if(method == 1){ //only update the amount
            String query="SELECT "+COLUMN_ACC_AMOUNT+" FROM "+TABLE_NAME +" WHERE "+COLUMN_ACC_ID + "=\"" +account.get_accID() +"\";";
            Cursor cursor = db.rawQuery(query, null);

            if(cursor != null && cursor.moveToFirst()){
                amount= cursor.getDouble(0);
            }

            cursor.close();

            amount = amount - account.get_accAmount();
            values.put(COLUMN_ACC_AMOUNT, amount);
            result=true;
        }
        else if(method == 2){  //update account name,avatar and amount
            values.put(COLUMN_ACC_NAME, account.get_accName());
            values.put(COLUMN_ACC_COLOR, account.get_accColor());
            values.put(COLUMN_ACC_AMOUNT, account.get_accAmount());
            result=true;
        }
        else{
            return result;
        }

        db.update(TABLE_NAME,values, COLUMN_ACC_ID+"=" + account.get_accID(),null);
        db.close();

        return result;
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

    //retrieve all expense from one account
    public List<Expense> getExpense(int accountID){
       // String query="SELECT * FROM "+TABLE_NAME2 +" WHERE "+COLUMN_EXP_ACC_ID + "=\"" +accountID +"\";";
        String query="SELECT * FROM "+TABLE_NAME2 +" WHERE "+COLUMN_EXP_ACC_ID + "=\"" +accountID +"\" ORDER BY substr(expDate, 7, 4) DESC,substr(expDate, 4, 2) DESC,substr(expDate, 1, 2) DESC;";

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

    //get one expense from particular account and expesne id
    public Expense getExpenseByID(int accountID,int expenseid){
        Expense userRecord = new Expense();
        String query="SELECT * FROM "+TABLE_NAME2 +" WHERE "+COLUMN_EXP_ACC_ID + "=\"" +accountID +"\" AND "+COLUMN_EXP_ID+ "=\"" +expenseid +"\";";

        SQLiteDatabase db= getWritableDatabase();
        //cursor point to your location
        Cursor cursor = db.rawQuery(query, null);
        //move to first row in your result
        cursor.moveToFirst();

        while(!cursor.isAfterLast()){
            if(cursor.getString(cursor.getColumnIndex("expType")) != null){
                userRecord.set_expID(cursor.getInt(0));
                userRecord.set_expType(cursor.getString(1));
                userRecord.set_expAmount(cursor.getDouble(2));
                userRecord.set_expDate(cursor.getString(3));
                userRecord.set_accID(cursor.getInt(4));

                cursor.moveToNext();
            }
        }
        cursor.close();

        return userRecord;
    }

    //update expense
    public boolean updateExpense(Expense expense){
        boolean result=false;

        ContentValues values = new ContentValues();
        SQLiteDatabase db= getWritableDatabase();

        try{
            values.put(COLUMN_EXP_TYPE, expense.get_expType());
            values.put(COLUMN_EXP_AMOUNT, expense.get_expAmount());
            values.put(COLUMN_EXP_DATE, expense.get_expDate());

            result=true;
        }catch(SQLException e){
            return result;
        }

        //db.update(TABLE_NAME2,values, COLUMN_EXP_ACC_ID+"=" + expense.get_accID(),null);
        db.update(TABLE_NAME2,values, COLUMN_EXP_ACC_ID+"=" + expense.get_accID()+" AND "+COLUMN_EXP_ID+"=" +expense.get_expID(),null);
        db.close();


        return result;
    }
//============================================================================================================================================================
    //add a new history to database
    public void addHistory(History history){
        ContentValues values = new ContentValues();
        values.put(COLUMN_HIS_TYPE, history.get_hisType());
        values.put(COLUMN_HIS_DATE, history.get_hisDate());

        SQLiteDatabase db= getWritableDatabase();
        db.insert(TABLE_NAME3,null,values);
        db.close();
    }

    //delete a history from database
    public void deleteHistory(int historyID){
        SQLiteDatabase db= getWritableDatabase();
        db.execSQL("DELETE FROM  "+TABLE_NAME3 +" WHERE "+COLUMN_HIS_ID + "=\"" +historyID +"\";");
    }

    //delete a history from database
    public void deleteAllHistory(){
        SQLiteDatabase db= getWritableDatabase();
        db.execSQL("DELETE FROM  "+TABLE_NAME3 +";");
    }

    //retrieve all history from database
    public List<History> getAllHistory(){
        String query="SELECT * FROM "+TABLE_NAME3 +" WHERE 1 ORDER BY "+COLUMN_HIS_ID+" DESC;";
        SQLiteDatabase db= getWritableDatabase();

        List<History> records = new ArrayList<History>();
        //cursor point to your location
        Cursor cursor = db.rawQuery(query, null);
        //move to first row in your result
        cursor.moveToFirst();

        while(!cursor.isAfterLast()){
            if(cursor.getString(cursor.getColumnIndex("hisType")) != null){
                History historyRecord = new History();
                historyRecord.set_hisID(cursor.getInt(0));
                historyRecord.set_hisType(cursor.getString(1));
                historyRecord.set_hisDate(cursor.getString(2));

                records.add(historyRecord);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return records;
    }

}
