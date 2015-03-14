package com.example.bryanty.projectx_smym5.domain;

import java.sql.Date;

/**
 * Created by BRYANTY on 14/03/2015.
 */
public class Expense {
    private int _expID;
    private String _expType;
    private double _expAmount;
    private String _expDate;
    private int _accID;

    public Expense() {
    }

    public Expense(String _expType, double _expAmount, String _expDate,int _accID) {
        this._expType = _expType;
        this._expAmount = _expAmount;
        this._expDate = _expDate;
        this._accID = _accID;
    }

    public Expense(int _expID, String _expType, double _expAmount, String _expDate, int _accID) {
        this._expID = _expID;
        this._expType = _expType;
        this._expAmount = _expAmount;
        this._expDate = _expDate;
        this._accID = _accID;
    }

    public int get_expID() {
        return _expID;
    }

    public void set_expID(int _expID) {
        this._expID = _expID;
    }

    public String get_expType() {
        return _expType;
    }

    public void set_expType(String _expType) {
        this._expType = _expType;
    }

    public double get_expAmount() {
        return _expAmount;
    }

    public void set_expAmount(double _expAmount) {
        this._expAmount = _expAmount;
    }

    public int get_accID() {
        return _accID;
    }

    public void set_accID(int _accID) {
        this._accID = _accID;
    }

    public String get_expDate() {
        return _expDate;
    }

    public void set_expDate(String _expDate) {
        this._expDate = _expDate;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "_expID=" + _expID +
                ", _expType='" + _expType + '\'' +
                ", _expAmount=" + _expAmount +
                ", _expDate='" + _expDate + '\'' +
                ", _accID=" + _accID +
                '}';
    }
}
