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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tasks);

        DatabaseHandler db = new DatabaseHandler(this);


        TextView textGroup = (TextView) findViewById(R.id.textViewGroup);

        current_intent = getIntent();
        String groupID = current_intent.getStringExtra(Keys.groupID);
        textGroup.setText(db.getGroup(groupID).getTitle());

        Log.d("Reading: ", "Reading all tasks in group..");
        List<DTO_Task> lstTasks = db.getTasks(groupID);
        ListView listViewTask = (ListView) findViewById(R.id.listViewTasks);
        if (!lstTasks.isEmpty()) {
            Log.d("Reading: ", "tasks in group are "+lstTasks.size());
            ArrayAdapter adapterTask = new ArrayAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text1, lstTasks);
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
        if(resultCode == Keys.addgroup_code) {
            String addgroup = (String) data.getExtras().get(Keys.addgroup);
            if(addgroup.equals("true")){
                lst_groups = db.getGroups();
                adapterGroup = new ArrayAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text1, lst_groups);
                listviewGroup.setAdapter(adapterGroup);
            }
        }
    }
}
