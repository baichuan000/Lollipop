package com.eric.savingsmanager.data;

import android.provider.BaseColumns;

/**
 * Created by Hector on 24/04/2017.
 */

public class SavingsItemEntry implements BaseColumns {
    public static final String TABLE_NAME = "saving_entries";
    public static final String COLUMN_NAME_BANK_NAME = "bank_name";
    public static final String COLUMN_NAME_START_DATE = "start_date";
    public static final String COLUMN_NAME_END_DATE = "end_date";
    public static final String COLUMN_NAME_AMOUNT = "amount";
    public static final String COLUMN_NAME_YIELD = "yield";
    public static final String COLUMN_NAME_INTEREST = "interest";
}
