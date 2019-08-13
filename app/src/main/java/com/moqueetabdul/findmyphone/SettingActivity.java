package com.moqueetabdul.findmyphone;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class SettingActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        listView = findViewById(R.id.list_view);

        String[] settingListArr = {"View/Change your pin","Help"};
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, settingListArr);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                switch (position){
                    case 0:
                        changePinCode();
                        break;
                    case 1:
                        break;
                }
            }
        });
    }

    private void changePinCode() {

        final SharedPreferences prefes = getSharedPreferences("com.moqueetabdul.findmyphone", MODE_PRIVATE);
        String pin = prefes.getString("pin","");

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.custom_dialog, null);
        dialogBuilder.setView(dialogView);

        final EditText editText = dialogView.findViewById(R.id.edit_text);
        editText.setText(pin);

        dialogBuilder.setTitle("Change your");
        dialogBuilder.setMessage("Enter 4 digit pin to change.");
        dialogBuilder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int i) {
                String editPin = editText.getText().toString();
                if(editPin.length()<4){
                    Toast.makeText(SettingActivity.this, "Wrong pin must be 4 digit long", Toast.LENGTH_LONG).show();
                    return;
                }
                prefes.edit().putString("pin", editText.getText().toString()).apply();
                Toast.makeText(SettingActivity.this, "Your pin changed Successfully", Toast.LENGTH_SHORT).show();
            }
        });
        dialogBuilder.setNegativeButton("Cancel", null);
        dialogBuilder.create().show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
