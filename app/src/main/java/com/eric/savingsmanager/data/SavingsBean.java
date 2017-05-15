package com.eric.savingsmanager.data;

/**
 * Created by Hector on 09/05/2017.
 */

public class SavingsBean {
    private String bankName;
    private String startDate;
    private String endDate;
    private String amount;
    private String yield;
    private String interest;

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getYield() {
        return yield;
    }

    public void setYield(String yield) {
        this.yield = yield;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    @Override
    public String toString() {
        return "SavingsBean{" +
                "bankName='" + bankName + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", amount='" + amount + '\'' +
                ", yield='" + yield + '\'' +
                ", interest='" + interest + '\'' +
                '}';
    }
}
