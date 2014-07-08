package ntn.assignment1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.List;

public class MainActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        DatabaseHandler db = new DatabaseHandler(this);
        Log.d("Insert: ", "Inserting...");
        //Default value
        db.addGroup(new DTO_Group("MobileCourse"));
        db.addGroup(new DTO_Group("ProcessTool"));
        db.addGroup(new DTO_Group("UserInterface"));
        db.addGroup(new DTO_Group("Database"));
        
        //Reading 
        Log.d("Reading: ", "Reading all contacts.."); 
        List<DTO_Group> lst_groups = db.getGroups();
        ListView listview_Group = (ListView) findViewById(R.id.listViewGroup);
        ArrayAdapter adapter_group = new ArrayAdapter(this, android.R.layout.simple_list_item_2,android.R.id.text2, lst_groups);
        listview_Group.setAdapter(adapter_group);
        listview_Group.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DTO_Group selectedGroup = (DTO_Group) parent.getSelectedItem();
                Bundle bundle = new Bundle();
                Intent intent = new Intent(MainActivity.this, TaskActivity.class);
                startActivity(intent);
            }
        });
    }
}
