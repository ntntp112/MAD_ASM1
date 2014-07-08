/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ntn.assignment1;

import java.sql.Date;

/**
 *
 * @author NTN
 */
public class DTO_Group {

    private String _id;
    private String _title;
    private boolean _remove;
    private Date _date;

    public DTO_Group() {
    }

    public DTO_Group(String _title) {
        this._title = _title;
        this._remove = false;
    }

    public DTO_Group(String _id, String _title, boolean _remove) {
        this._id = _id;
        this._title = _title;
        this._remove = _remove;
    }

    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }

    public String getTitle() {
        return _title;
    }

    public void setTitle(String _title) {
        this._title = _title;
    }

    public Date getDate() {
        return _date;
    }

    public void setDate(Date _date) {
        this._date = _date;
    }

    public boolean isRemove() {
        return _remove;
    }

    public void setRemove(boolean _remove) {
        this._remove = _remove;
    }
    

}
