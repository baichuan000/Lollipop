package com.eric.savingsmanager.activities;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.eric.savingsmanager.R;
import com.eric.savingsmanager.data.SavingsContentProvider;
import com.eric.savingsmanager.data.SavingsItemEntry;
import com.eric.savingsmanager.utils.Constants;
import com.eric.savingsmanager.utils.Utils;

import java.util.Calendar;
import java.util.Date;

public class AddSavingsItemActivity extends AppCompatActivity {

    // Edit text for the fields
    private EditText mEditBankName;
    private EditText mEditStartDate;
    private EditText mEditEndDate;
    private EditText mEditAmount;
    private EditText mEditYield;
    private EditText mEditInterest;

    // Data
    private Calendar mCalendar = Calendar.getInstance();
    private Date mStartDate;
    private Date mEndDate;
    private float mAmount;
    private float mYield;
    private float mInterest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_savings_item);
        setupUI();
    }

    /**
     * Setup UI elements
     */
    private void setupUI() {

        // ID from layout to java
        mEditBankName = (EditText) findViewById(R.id.edit_bank_name);
        mEditStartDate = (EditText) findViewById(R.id.edit_start_date);
        mEditEndDate = (EditText) findViewById(R.id.edit_end_date);
        mEditAmount = (EditText) findViewById(R.id.edit_amount);
        mEditYield = (EditText) findViewById(R.id.edit_yield);
        mEditInterest = (EditText) findViewById(R.id.edit_interest);

        // set text watcher to update interest
        mEditAmount.addTextChangedListener(mInterestTextWatcher);
        mEditYield.addTextChangedListener(mInterestTextWatcher);
        mEditAmount.setOnFocusChangeListener(mOnFocusChangeListener);
        mEditYield.setOnFocusChangeListener(mOnFocusChangeListener);

        // set date field listener
        mEditStartDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    showDatePicker((EditText) v, true);
                }
            }
        });
        mEditStartDate.setInputType(InputType.TYPE_NULL);
        mEditStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.hasFocus()) {
                    showDatePicker((EditText) v, true);
                }
            }
        });
        mEditEndDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    showDatePicker((EditText) v, false);
                }
            }
        });
        mEditEndDate.setInputType(InputType.TYPE_NULL);
        mEditEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.hasFocus()) {
                    showDatePicker((EditText) v, false);
                }
            }
        });

    }

    /**
     * A text watcher for fields which impacts interest
     */
    private TextWatcher mInterestTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            updateInterest();
        }
    };

    /**
     * A OnFocusChangeListener for Amount and Yield field
     */
    private View.OnFocusChangeListener mOnFocusChangeListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (!hasFocus) {
                EditText edit = (EditText) v;
                String amountStr = edit.getText().toString();
                if (!Utils.isNullOrEmpty(amountStr)) {
                    edit.setText(Utils.getFloat(amountStr));
                }
            }
        }
    };

    /**
     * Show date picker for edit field of date
     *
     * @param edit      date field
     * @param startDate whether it's start date
     */
    private void showDatePicker(final EditText edit, final boolean startDate) {

        // show date picker
        DatePickerDialog picker = new DatePickerDialog(AddSavingsItemActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        // set the date to EditText
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(year, month, dayOfMonth);
                        Date date = new Date(calendar.getTimeInMillis());
                        long timestamp = calendar.getTimeInMillis();
                        edit.setText(Utils.formatDate(timestamp));
                        if (startDate) {
                            mStartDate = new Date(timestamp);
                        } else {
                            mEndDate = new Date(timestamp);
                        }
                        // has to do here
                        updateInterest();
                    }
                },
                mCalendar.get(Calendar.YEAR), mCalendar.get(Calendar.MONTH), mCalendar.get(Calendar.DAY_OF_MONTH));
        picker.show();
    }

    /**
     * Update interest based on current values
     */
    private void updateInterest() {

        String amountStr = mEditAmount.getText().toString();
        String yieldStr = mEditYield.getText().toString();

        if (mStartDate != null && mEndDate != null
                && !Utils.isNullOrEmpty(amountStr)
                && !Utils.isNullOrEmpty(yieldStr)) {

            float days = Utils.getDiffDays(mStartDate, mEndDate);
            mAmount = Float.valueOf(amountStr);
            mYield = Float.valueOf(yieldStr);

            // calculate the value
            mInterest = mAmount * (mYield / 100) * (days / Constants.DAYS_OF_ONE_YEAR);
            mInterest = Utils.roundFloat(mInterest);
            // update the interest value in UI
            mEditInterest.setText(Utils.formatFloat(mInterest));
            Log.d(Constants.LOG_TAG, "start = " + mStartDate.toString() + "\nend = " + mEndDate.toString()
                    + "\ndays = " + days + "\namount = " + mAmount + "\nyield = " + mYield + "\ninterest = " + mInterest);
        }

    }

    /**
     * Click listener for Cancel button
     *
     * @param view the cancel button
     */
    public void onCancelClicked(View view) {
        onBackPressed();
    }

    /**
     * Click listener for Save button
     *
     * @param view the save button
     */
    public void onSaveClicked(View view) {
        //TODO save the item
        if (mInterest != 0.0f) {

            ContentValues values = new ContentValues();
            values.put(SavingsItemEntry.COLUMN_NAME_BANK_NAME, mEditBankName.getText().toString());
            values.put(SavingsItemEntry.COLUMN_NAME_AMOUNT, mAmount);
            values.put(SavingsItemEntry.COLUMN_NAME_YIELD, mYield);
            values.put(SavingsItemEntry.COLUMN_NAME_START_DATE, mStartDate.getTime());
            values.put(SavingsItemEntry.COLUMN_NAME_END_DATE, mEndDate.getTime());
            values.put(SavingsItemEntry.COLUMN_NAME_INTEREST, mInterest);

            // Save the data into database by ContentProvider
            getContentResolver().insert(
                    SavingsContentProvider.CONTENT_URI,
                    values
            );

            // Go back to dashboard
            Utils.gotoDashBoard(this);
        } else {
            Toast.makeText(this, R.string.missing_savings_information, Toast.LENGTH_LONG).show();
        }

    }
}
