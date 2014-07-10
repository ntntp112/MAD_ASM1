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
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * @author NTN
 */
public class TaskActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tasks);

        DatabaseHandler db = new DatabaseHandler(this);

        TextView textGroup = (TextView) findViewById(R.id.textViewGroup);

        Intent intent = getIntent();
        String groupID = intent.getStringExtra(Keys.groupID);
        textGroup.setText(db.getGroup(groupID).getTitle());

        Log.d("Reading: ", "Reading all tasks in group..");
        List<DTO_Task> lstTasks = db.getTasks(groupID);
        ListView listviewTask = (ListView) findViewById(R.id.listViewTasks);
        if (lstTasks != null) {
            ArrayAdapter adapterTask = new ArrayAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text2, lstTasks);
            listviewTask.setAdapter(adapterTask);
        }
    }
}
