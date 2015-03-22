package com.example.bryanty.projectx_smym5.domain;

/**
 * Created by BRYANTY on 22/03/2015.
 */
public class History {
    private int _hisID;
    private String _hisType;
    private String _hisDate;

    public History() {
    }

    public History(int _hisID, String _hisType, String _hisDate) {
        this._hisID = _hisID;
        this._hisType = _hisType;
        this._hisDate = _hisDate;
    }

    public History(String _hisType, String _hisDate) {
        this._hisType = _hisType;
        this._hisDate = _hisDate;
    }

    public int get_hisID() {
        return _hisID;
    }

    public void set_hisID(int _hisID) {
        this._hisID = _hisID;
    }

    public String get_hisType() {
        return _hisType;
    }

    public void set_hisType(String _hisType) {
        this._hisType = _hisType;
    }

    public String get_hisDate() {
        return _hisDate;
    }

    public void set_hisDate(String _hisDate) {
        this._hisDate = _hisDate;
    }

    @Override
    public String toString() {
        return "History{" +
                "_hisID=" + _hisID +
                ", _hisType='" + _hisType + '\'' +
                ", _hisDate='" + _hisDate + '\'' +
                '}';
    }
}
