package mapp.com.sg.myfirstapplication;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import mapp.com.sg.myfirstapplication.Model.Expense;
import mapp.com.sg.myfirstapplication.Model.Income;


public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "myList.db";
    public static final String TABLE_NAME = "myList_expense";
    public static final String TABLE_NAME2 = "myList_income";
    public static final String COL1 = "ID";
    public static final String COL2 = "MEMO";
    public static final String COL3 = "MONEY";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL2 + " TEXT, " +
                COL3 + " TEXT)"; //REAL = DOUBLE
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String upgradeTable = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(upgradeTable);
        onCreate(db);
    }

    public boolean addExpenseData(String memo, Double money) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, memo);
        contentValues.put(COL3, money);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean addIncomeData(String memo, Double money) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, memo);
        contentValues.put(COL3, money);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }


    public ArrayList<Expense> getAllExpenseData() {
        ArrayList<Expense> arrayList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();

//        onCreate(db); // RECREATE

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        while(cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String memo = cursor.getString(1);
            Double money = Double.valueOf(cursor.getString(2));

            Expense expense = new Expense(id, memo, money);
            arrayList.add(expense);
        }

        return arrayList;

    }

    public ArrayList<Income> getAllIncomeData() {
        ArrayList<Income> arrayList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();

//        onCreate(db); // RECREATE

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME2, null);
        while(cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String memo = cursor.getString(1);
            Double money = Double.valueOf(cursor.getString(2));

            Income income = new Income(id, memo, money);
            arrayList.add(income);
        }

        return arrayList;
    }

    public void updateName(int id, String newMemo, Double newMoney) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + COL2 + " = '" + newMemo + "', " + COL3 + " = '" + newMoney + "'" +
                " WHERE " + COL1 + " = '" + id + "'";
        db.execSQL(query);
    }

    public void deleteName(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE " + COL1 + " = '" + id + "'";
        db.execSQL(query);
    }

    public int getTotalExpense() { // ERROR
        int i = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT COUNT(*) FROM " + TABLE_NAME;
        db.execSQL(query);

        return i = Integer.parseInt(db.toString());
    }









    public void dropTable() {
        SQLiteDatabase db = this.getWritableDatabase();
        String clearDBQuery = "DROP TABLE "+TABLE_NAME;
        db.execSQL(clearDBQuery);
    }




















}
