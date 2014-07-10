/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ntn.assignment1;

import java.sql.Date;

/**
 * @author NTN
 */
public class DTO_Group {

    private String _id;
    private String _title;

    public DTO_Group() {
    }

    public DTO_Group(String _title) {
        this._title = _title;
    }

    public DTO_Group(String _id, String _title) {
        this._id = _id;
        this._title = _title;
    }

    @Override
    public String toString() {
        return _title;
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

}
