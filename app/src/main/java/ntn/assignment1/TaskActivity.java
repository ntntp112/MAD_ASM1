/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ntn.assignment1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * @author NTN
 */
public class TaskActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tasks);

        TextView textGroup = (TextView) findViewById(R.id.textViewGroup);

        Intent intent = getIntent();
        String groupID = intent.getStringExtra(Keys.groupID);
        textGroup.setText(groupID);
    }
}
