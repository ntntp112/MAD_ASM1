/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ntn.assignment1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * @author NTN
 */
public class TaskActivity extends Activity {
    private Intent current_intent;
    private List<DTO_Task> lstTasks;
    private DatabaseHandler db;
    private String groupID;
    private ArrayAdapter adapterTask;
    private ListView listViewTask;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tasks);

        db = new DatabaseHandler(this);


        TextView textGroup = (TextView) findViewById(R.id.textViewGroup);

        current_intent = getIntent();
        groupID = current_intent.getStringExtra(Keys.groupID);
        textGroup.setText(db.getGroup(groupID).getTitle());

        Log.d("Reading: ", "Reading all tasks in group..");
        lstTasks = db.getTasks(groupID);
        listViewTask = (ListView) findViewById(R.id.listViewTasks);
        if (!lstTasks.isEmpty()) {
            Log.d("Reading: ", "tasks in group are "+lstTasks.size());
            adapterTask = new ArrayAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text1, lstTasks);
            listViewTask.setAdapter(adapterTask);
        }

        //Button
        Button btn_addtask = (Button) findViewById(R.id.buttonAddTaskView);
        btn_addtask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UCTaskActivity.class);
                intent.putExtras(current_intent.getExtras());
                startActivityForResult(intent, Keys.addtask_code);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("Result: ", resultCode+"");
        if(resultCode == Keys.addtask_code) {
            String addgroup = (String) data.getExtras().get(Keys.addtask);
            if(addgroup.equals("true")){
                lstTasks = db.getTasks(groupID);
                adapterTask = new ArrayAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text1, lstTasks);
                listViewTask.setAdapter(adapterTask);
            }
        }
    }
}
