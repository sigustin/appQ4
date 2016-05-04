package com.grz.sinf1225.uclove1.Matching;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.grz.sinf1225.uclove1.R;
import com.grz.sinf1225.uclove1.SettingsActivity;

public class SetFilterActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_filter);

        Spinner gender = (Spinner) findViewById(R.id.spinner_gender);
        ArrayAdapter<CharSequence> genderAdapter = ArrayAdapter.createFromResource(this, R.array.array_genders_filter, android.R.layout.simple_spinner_item);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender.setAdapter(genderAdapter);

        Spinner loveStatus = (Spinner) findViewById(R.id.spinner_love_status);
        ArrayAdapter<CharSequence> loveStatusAdapter = ArrayAdapter.createFromResource(this, R.array.array_love_statuses_filter, android.R.layout.simple_spinner_item);
        loveStatusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        loveStatus.setAdapter(loveStatusAdapter);

        Spinner interestedIn = (Spinner) findViewById(R.id.spinner_interested_in);
        ArrayAdapter<CharSequence> interestedInAdapter = ArrayAdapter.createFromResource(this, R.array.array_interested_in_values_filter, android.R.layout.simple_spinner_item);
        interestedInAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        interestedIn.setAdapter(interestedInAdapter);

        Spinner smoker = (Spinner) findViewById(R.id.spinner_smoker);
        ArrayAdapter<CharSequence> smokerAdapter = ArrayAdapter.createFromResource(this, R.array.array_smoker_filter, android.R.layout.simple_spinner_item);
        smokerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        smoker.setAdapter(smokerAdapter);

        Spinner country = (Spinner) findViewById(R.id.spinner_country);
        ArrayAdapter<CharSequence> countriesAdapter = ArrayAdapter.createFromResource(this, R.array.array_countries_filter, android.R.layout.simple_spinner_item);
        countriesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        country.setAdapter(countriesAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.top_menu_set_filters, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.top_menu_save_filters:
                Log.d("TOPMENU", "Save filters");
                return true;
            case R.id.top_menu_item_settings:
                Log.d("TOPMENU", "Settings selected");
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;
        }
        return false;
    }
}
