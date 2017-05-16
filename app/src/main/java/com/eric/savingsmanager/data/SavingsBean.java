package com.eric.savingsmanager.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Hector on 09/05/2017.
 */

public class SavingsBean implements Parcelable {
    private long id;
    private String bankName;
    private long startDate;
    private long endDate;
    private float amount;
    private float yield;
    private float interest;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public long getStartDate() {
        return startDate;
    }

    public void setStartDate(long startDate) {
        this.startDate = startDate;
    }

    public long getEndDate() {
        return endDate;
    }

    public void setEndDate(long endDate) {
        this.endDate = endDate;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public float getYield() {
        return yield;
    }

    public void setYield(float yield) {
        this.yield = yield;
    }

    public float getInterest() {
        return interest;
    }

    public void setInterest(float interest) {
        this.interest = interest;
    }

    @Override
    public String toString() {
        return "SavingsBean {\n" +
                "id='" + id + "\',\n" +
                "bankName='" + bankName + "\',\n" +
                "startDate='" + startDate + "\',\n" +
                "endDate='" + endDate + "\',\n" +
                "amount='" + amount + "\',\n" +
                "yield='" + yield + "\',\n" +
                "interest='" + interest + "\',\n" +
                "}";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeLong(id);
        out.writeString(bankName);
        out.writeLong(startDate);
        out.writeLong(endDate);
        out.writeFloat(amount);
        out.writeFloat(yield);
        out.writeFloat(interest);
    }

    public static final Parcelable.Creator<SavingsBean> CREATOR = new Parcelable.Creator<SavingsBean>() {
        public SavingsBean createFromParcel(Parcel in) {
            return new SavingsBean(in);
        }

        public SavingsBean[] newArray(int size) {
            return new SavingsBean[size];
        }
    };

    private SavingsBean(Parcel in) {
        id = in.readLong();
        bankName = in.readString();
        startDate = in.readLong();
        endDate = in.readLong();
        amount = in.readFloat();
        yield = in.readFloat();
        interest = in.readFloat();
    }

    public SavingsBean() {

    }
}