package ntn.assignment1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.CharacterPickerDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by NTN on 7/16/2014.
 */
public class UCGroupActivity extends Activity {
    private DatabaseHandler db;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ucgroup);

        db = new DatabaseHandler(this);

        //TextView
        final EditText txt_title = (EditText) findViewById(R.id.editTextGroupTitle);
        //Button
        Button btn_addgroup = (Button) findViewById(R.id.buttonAddGroup);
        Button btn_cancel = (Button) findViewById(R.id.buttonCancelGroup);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_addgroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!txt_title.getText().toString().trim().equals("")) {
                    db.addGroup(new DTO_Group(txt_title.getText().toString()));
                    Intent i = new Intent();
                    i.putExtra(Keys.addgroup, "true");
                    setResult(Keys.addgroup_code, i);
                }
                finish();
            }
        });
    }
}
