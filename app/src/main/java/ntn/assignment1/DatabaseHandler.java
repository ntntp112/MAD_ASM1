/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ntn.assignment1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author NTN
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    //GENERAL   
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "taskmanager";

    //TABLE_GROUP
    private static final String TABLE_GROUP = "tbl_group";
    private static final String QUERY_DROP_GROUP = "DROP TABLE IF EXISTS " + TABLE_GROUP;
    private static final String TBL_GROUP_ID = "id";
    private static final String TBL_GROUP_TITLE = "title";
    private static final String TBL_GROUP_TIMESTAMP = "timestamp";
    private static final String QUERY_CREATE_GROUP = "CREATE TABLE " + TABLE_GROUP + " ("
            + "    " + TBL_GROUP_ID + " Integer Primary Key   Autoincrement,"
            + "    " + TBL_GROUP_TITLE + " Text,"
            + "    " + TBL_GROUP_TIMESTAMP + " Datetime Default Current_Timestamp"
            + "    , \"tickfl\" INTEGER)";

    //TABLE_TASK
    private static final String TABLE_TASK = "tbl_task";
    private static final String QUERY_DROP_TASK = "DROP TABLE IF EXISTS " + TABLE_TASK;
    private static final String TBL_TASK_ID = "id";
    private static final String TBL_TASK_GROUP = "group";
    private static final String TBL_TASK_TITLE = "title";
    private static final String TBL_TASK_DUEDATE = "duedate";
    private static final String TBL_TASK_NOTE = "note";
    private static final String TBL_TASK_PRIORITY = "priority";
    private static final String TBL_TASK_COLLABORATORS = "collaborators";
    private static final String TBL_TASK_STATUS = "status";
    private static final String TBL_TASK_TIMESTAMP = "timestamp";
    private static final String QUERY_CREATE_TASK = "CREATE TABLE " + TABLE_TASK + " ("
            + "    " + TBL_TASK_ID + " Integer Primary Key   Autoincrement,"
            + "    " + TBL_TASK_GROUP + " Integer References "+TABLE_GROUP+"("+TBL_GROUP_ID+"),"
            + "    " + TBL_TASK_TITLE + " Text,"
            + "    " + TBL_TASK_DUEDATE + " Datetime,"
            + "    " + TBL_TASK_NOTE + " Text,"
            + "    " + TBL_TASK_PRIORITY + " Integer,"
            + "    " + TBL_TASK_COLLABORATORS + " Text,"
            + "    " + TBL_TASK_STATUS + " Text,"
            + "    " + TBL_TASK_TIMESTAMP + " Datetime Default Current_Timestamp"
            + "    , \"tickfl\" INTEGER)";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(QUERY_CREATE_GROUP);
        db.execSQL(QUERY_CREATE_TASK);
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(QUERY_DROP_TASK);
        db.execSQL(QUERY_DROP_GROUP);
        onCreate(db);
    }

    //CRUD for GROUP
    public void addGroup(DTO_Group input) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TBL_GROUP_TITLE, input.getTitle());

        db.insert(TABLE_GROUP, null, values);
        db.close();
    }

    public DTO_Group getGroup(String id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_GROUP, new String[]{TBL_GROUP_ID,
                        TBL_GROUP_TITLE}, TBL_GROUP_ID + " == ? ",
                new String[]{String.valueOf(id)}, null, null, null, null
        );
        if (cursor != null) {
            cursor.moveToFirst();
        }

        DTO_Group group = new DTO_Group(String.valueOf(cursor.getInt(0)),
                cursor.getString(1), Boolean.valueOf(cursor.getString(2)));

        db.close();
        return group;
    }

    public List<DTO_Group> getGroups() {
        List<DTO_Group> groups = new ArrayList<DTO_Group>();

        String selectQuery = "SELECT  * FROM " + TABLE_GROUP;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                DTO_Group group = new DTO_Group();
                group.setId(String.valueOf(cursor.getInt(0)));
                group.setTitle(cursor.getString(1));
                groups.add(group);
            } while (cursor.moveToNext());
        }

        db.close();
        return groups;
    }

    public int updateContact(DTO_Group input) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TBL_GROUP_TITLE, input.getTitle());

        int result = db.update(TABLE_GROUP, values, TBL_GROUP_ID + " == ?",
                new String[]{String.valueOf(input.getId())});

        db.close();
        return result;
    }

    public int deleteContact(DTO_Group input) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_GROUP, TBL_GROUP_ID + " == ?",
                new String[]{String.valueOf(input.getId())});

        db.close();
        return result;
    }

    //CRUD for TASK
    public void addTask(DTO_Task input) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TBL_TASK_TITLE, input.get_title());
        values.put(TBL_TASK_GROUP, input.get_group());
        values.put(TBL_TASK_DUEDATE, input.get_duedate().getTime());
        values.put(TBL_TASK_NOTE, input.get_note());
        values.put(TBL_TASK_PRIORITY, input.get_priority());
        values.put(TBL_TASK_COLLABORATORS, input.get_collaborators());
        values.put(TBL_TASK_STATUS, input.get_status());

        db.insert(TABLE_TASK, null, values);
        db.close();
    }

    public DTO_Task getTask(String id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_TASK, new String[]{TBL_TASK_ID, TBL_TASK_GROUP,
                        TBL_GROUP_TITLE, TBL_TASK_DUEDATE, TBL_TASK_NOTE, TBL_TASK_PRIORITY,
                        TBL_TASK_COLLABORATORS, TBL_TASK_STATUS}, TBL_GROUP_ID + " == ? ",
                new String[]{String.valueOf(id)}, null, null, null, null
        );
        if (cursor != null) {
            cursor.moveToFirst();
        }

        DTO_Task task = new DTO_Task(String.valueOf(cursor.getInt(0)),
                cursor.getString(1), Boolean.valueOf(cursor.getString(2)));

        db.close();
        return task;
    }

    public List<DTO_Group> getGroups() {
        List<DTO_Group> groups = new ArrayList<DTO_Group>();

        String selectQuery = "SELECT  * FROM " + TABLE_GROUP;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                DTO_Group group = new DTO_Group();
                group.setId(String.valueOf(cursor.getInt(0)));
                group.setTitle(cursor.getString(1));
                groups.add(group);
            } while (cursor.moveToNext());
        }

        db.close();
        return groups;
    }

    public int updateContact(DTO_Group input) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TBL_GROUP_TITLE, input.getTitle());

        int result = db.update(TABLE_GROUP, values, TBL_GROUP_ID + " == ?",
                new String[]{String.valueOf(input.getId())});

        db.close();
        return result;
    }

    public int deleteContact(DTO_Group input) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_GROUP, TBL_GROUP_ID + " == ?",
                new String[]{String.valueOf(input.getId())});

        db.close();
        return result;
    }
}
