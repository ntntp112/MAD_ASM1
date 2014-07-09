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

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        DatabaseHandler db = new DatabaseHandler(this);
        Log.d("Insert: ", "Inserting...");
        //Default value
//        db.addGroup(new DTO_Group("MobileCourse"));
//        db.addGroup(new DTO_Group("ProcessTool"));
//        db.addGroup(new DTO_Group("UserInterface"));
//        db.addGroup(new DTO_Group("Database"));

        //Reading 
        Log.d("Reading: ", "Reading all group..");
        List<DTO_Group> lst_groups = db.getGroups();
        Log.d("Reading: ", "List size "+lst_groups.size());
        ListView listview_Group = (ListView) findViewById(R.id.listViewGroup);
        ArrayAdapter adapter_group = new ArrayAdapter(this, android.R.layout.simple_list_item_2, android.R.id.text2, lst_groups);
        listview_Group.setAdapter(adapter_group);
        listview_Group.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DTO_Group selectedGroup = (DTO_Group) parent.getItemAtPosition(position);
                Intent intent = new Intent(getApplicationContext(), TaskActivity.class);
                intent.putExtra(Keys.groupID, selectedGroup.getId());
                startActivity(intent);
            }
        });
    }
}
