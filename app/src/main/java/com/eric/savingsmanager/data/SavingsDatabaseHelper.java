package com.eric.savingsmanager.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Hector on 24/04/2017.
 */

public class SavingsDatabaseHelper extends SQLiteOpenHelper {

    // A string that defines the SQL statement for creating a table
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + SavingsItemEntry.TABLE_NAME + " (" +
                    SavingsItemEntry._ID + " INTEGER PRIMARY KEY," +
                    SavingsItemEntry.COLUMN_NAME_BANK_NAME + " TEXT," +
                    SavingsItemEntry.COLUMN_NAME_START_DATE + " TIMESTAMP," +
                    SavingsItemEntry.COLUMN_NAME_END_DATE + " TIMESTAMP," +
                    SavingsItemEntry.COLUMN_NAME_AMOUNT + " FLOAT," +
                    SavingsItemEntry.COLUMN_NAME_YIELD + " FLOAT," +
                    SavingsItemEntry.COLUMN_NAME_INTEREST + " FLOAT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + SavingsItemEntry.TABLE_NAME;

    // If you change the database schema, you must increment the database version.
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Savings.db";

    public SavingsDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
