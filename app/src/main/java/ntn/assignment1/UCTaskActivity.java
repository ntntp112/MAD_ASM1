package ntn.assignment1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.sql.Date;

/**
 * Created by NTN on 7/16/2014.
 */
public class UCTaskActivity extends Activity {
    private DatabaseHandler db;
    private EditText txt_title;
    private EditText txt_note;
    private EditText txt_collaborate;
    private String groupID;
    private DatePicker date;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.uctask);

        db = new DatabaseHandler(this);

        Intent intent = getIntent();
        groupID = intent.getStringExtra(Keys.groupID);

        //Controls
        txt_title = (EditText) findViewById(R.id.editTextTaskTitle);
        date = (DatePicker) findViewById(R.id.datePickerTaskDueDate);
        txt_note = (EditText) findViewById(R.id.editTextTaskNote);
        txt_collaborate = (EditText) findViewById(R.id.editTextTaskCollaborate);


        //Button
        Button btn_add = (Button) findViewById(R.id.buttonAddTask);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = txt_title.getText().toString().trim();
                Date inputdate = new Date(date.getYear(), date.getMonth()+1, date.getDayOfMonth());
                String note = "";
                if(!txt_note.getText().toString().trim().equals("")){
                    note = txt_note.getText().toString().trim();
                }
                String collaborate = "";
                if(!txt_collaborate.getText().toString().trim().equals("")){
                    collaborate = txt_collaborate.getText().toString().trim();
                }
                Log.d("Click: ", "Button add task to "+groupID);
                if(!title.equals("")) {
                    db.addTask(new DTO_Task(groupID, title, inputdate,note ,0,txt_collaborate.getText().toString(),""));
                    Intent i = new Intent();
                    i.putExtra(Keys.addtask, "true");
                    setResult(Keys.addtask_code, i);
                    Log.d("Add Task: ", "Done...");
                }
                finish();
            }
        });

//        Button btn_cancel = (Button) findViewById(R.id.buttonCancelTask);
//        btn_cancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });

    }
}
