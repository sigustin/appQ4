package com.grz.sinf1225.uclove1;

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

        Spinner themeSpinner = (Spinner) findViewById(R.id.theme_spinner);
        ArrayAdapter<CharSequence> themeSpinnerAdapter = ArrayAdapter.createFromResource(this, R.array.array_themes, android.R.layout.simple_spinner_item);
        themeSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        themeSpinner.setAdapter(themeSpinnerAdapter);
    }

    public void onAboutTextViewClicked(View view)
    {
        Log.d("TEXTVIEW", "About selected");
    }
}
