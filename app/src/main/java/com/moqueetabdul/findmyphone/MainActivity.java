package com.moqueetabdul.findmyphone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("com.moqueetabdul.findmyphone", MODE_PRIVATE);

        checkFirstRun();

    }

    private void checkFirstRun() {

        if(sharedPreferences.getBoolean("first_run",true)){

            String pin = String.format(Locale.US,"%04d", new Random().nextInt(10000));
            sharedPreferences.edit().putString("pin",pin).apply();
            sharedPreferences.edit().putBoolean("first_run",false).apply();
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Your secret code")
                    .setMessage("Your 4 digit secret pin is: "+pin+"\nYou can find it later under settings")
                    .setPositiveButton("Ok", null)
                    .setCancelable(false).create().show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        Intent i = new Intent(getApplicationContext(),SettingActivity.class);
        startActivity(i);

        return true;
    }
}
