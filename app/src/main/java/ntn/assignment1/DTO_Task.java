package ntn.assignment1;

import java.sql.Date;

/**
 * Created by NTN on 7/9/2014.
 */
public class DTO_Task {
    private String _id;
    private String _group;
    private String _title;
    private Date _duedate;
    private String _note;
    private int _priority;
    private String _collaborators;
    private String _status;

    public DTO_Task() {
    }
    public DTO_Task(String _group, String _title, Date _duedate, String _note, int _priority, String _collaborators, String _status) {
        this._group = _group;
        this._title = _title;
        this._duedate = _duedate;
        this._note = _note;
        this._priority = _priority;
        this._collaborators = _collaborators;
        this._status = _status;
    }

    public DTO_Task(String _id, String _group, String _title, Date _duedate, String _note, int _priority, String _collaborators, String _status) {
        this._id = _id;
        this._group = _group;
        this._title = _title;
        this._duedate = _duedate;
        this._note = _note;
        this._priority = _priority;
        this._collaborators = _collaborators;
        this._status = _status;
    }

    @Override
    public String toString() {
        return _title;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String get_group() {
        return _group;
    }

    public void set_group(String _group) {
        this._group = _group;
    }

    public String get_title() {
        return _title;
    }

    public void set_title(String _title) {
        this._title = _title;
    }

    public Date get_duedate() {
        return _duedate;
    }

    public void set_duedate(Date _duedate) {
        this._duedate = _duedate;
    }

    public String get_note() {
        return _note;
    }

    public void set_note(String _note) {
        this._note = _note;
    }

    public int get_priority() {
        return _priority;
    }

    public void set_priority(int _priority) {
        this._priority = _priority;
    }

    public String get_collaborators() {
        return _collaborators;
    }

    public void set_collaborators(String _collaborators) {
        this._collaborators = _collaborators;
    }

    public String get_status() {
        return _status;
    }

    public void set_status(String _status) {
        this._status = _status;
    }
}
