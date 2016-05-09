package com.grz.sinf1225.uclove1;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class SettingsActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public void onAboutTextViewClicked(View view)
    {
        Log.d("TEXTVIEW", "About selected");
        new AlertDialog.Builder(this)
                .setTitle(getResources().getString(R.string.about))
                .setMessage(getResources().getString(R.string.about_the_app))
                .setNegativeButton(getResources().getString(R.string.close_popup), null)
                .show();
    }

    public void deleteDatabase(View view)
    {
        Database.resetDatabase();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
