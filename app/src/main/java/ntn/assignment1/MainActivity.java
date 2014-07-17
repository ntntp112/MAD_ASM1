package ntn.assignment1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.sql.Date;
import java.util.List;

public class MainActivity extends Activity {
    private DatabaseHandler db;
    private List<DTO_Group> lst_groups;
    private ArrayAdapter adapterGroup;
    private ListView listviewGroup;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        db = new DatabaseHandler(this);
        Log.d("Insert: ", "Inserting...");
        //Default value
//        db.addGroup(new DTO_Group("MobileCourse"));
//        db.addGroup(new DTO_Group("ProcessTool"));
//        db.addGroup(new DTO_Group("UserInterface"));
//        db.addGroup(new DTO_Group("Database"));
//        db.addTask(new DTO_Task("1", "Title1", new Date(195000), "Note", 1, "ntn@gmail.com", "Done"));
//        db.addTask(new DTO_Task("1", "Title234", new Date(195000), "Note", 1, "ntn@gmail.com", "Done"));

        //Reading
        Log.d("Reading: ", "Reading all group..");
        lst_groups = db.getGroups();
        listviewGroup = (ListView) findViewById(R.id.listViewGroup);
        adapterGroup = new ArrayAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text1, lst_groups);
        listviewGroup.setAdapter(adapterGroup);
        listviewGroup.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DTO_Group selectedGroup = (DTO_Group) parent.getItemAtPosition(position);
                Intent intent = new Intent(getApplicationContext(), TaskActivity.class);
                intent.putExtra(Keys.groupID, selectedGroup.getId());
                startActivity(intent);
            }
        });

        //Add button
        Button btn_add = (Button) findViewById(R.id.buttonAddGroupView);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UCGroupActivity.class);
                startActivityForResult(intent, Keys.addgroup_code);
            }
        });

        //Result

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
