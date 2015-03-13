package com.example.bryanty.projectx_smym5.domain;

/**
 * Created by BRYANTY on 12/03/2015.
 */
public class Account {
    private int _accID;
    private String _accName;
    private int _accColor;
    private double _accAmount;

    public Account() {
    }

    public Account(String _accName, int _accColor, double _accAmount) {
        this._accName = _accName;
        this._accColor = _accColor;
    }

    public Account(int _accID, String _accName, int _accColor, double _accAmount) {
        this._accID = _accID;
        this._accName = _accName;
        this._accColor = _accColor;
    }

    public int get_accID() {
        return _accID;
    }

    public void set_accID(int _accID) {
        this._accID = _accID;
    }

    public String get_accName() {
        return _accName;
    }

    public void set_accName(String _accName) {
        this._accName = _accName;
    }

    public int get_accColor() {
        return _accColor;
    }

    public void set_accColor(int _accColor) {
        this._accColor = _accColor;
    }

    public double get_accAmount() {
        return _accAmount;
    }

    public void set_accAmount(double _accAmount) {
        this._accAmount = _accAmount;
    }

    @Override
    public String toString() {
        return "Account{" +
                "_accID=" + _accID +
                ", _accName='" + _accName + '\'' +
                ", _accColor=" + _accColor +
                ", _accAmount=" + _accAmount +
                '}';
    }
}
