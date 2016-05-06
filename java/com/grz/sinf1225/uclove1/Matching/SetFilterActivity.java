package com.grz.sinf1225.uclove1.Matching;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.grz.sinf1225.uclove1.Database;
import com.grz.sinf1225.uclove1.R;
import com.grz.sinf1225.uclove1.SettingsActivity;
import com.grz.sinf1225.uclove1.User;

public class SetFilterActivity extends AppCompatActivity
{

    Filter filter;
    User currentUser;

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
    public void onResume()
    {
        super.onResume();

        String currentPseudo = getIntent().getStringExtra(User.EXTRA_PSEUDO);
        currentUser = new User(currentPseudo);

        filter = (Filter) getIntent().getSerializableExtra(Filter.EXTRA_FILTER);

        if (filter != null)
        {
            EditText pseudoEditText = (EditText) findViewById(R.id.edit_text_pseudo);
            pseudoEditText.setText(filter.getPseudo());

            EditText firstNameEditText = (EditText) findViewById(R.id.edit_text_first_name);
            firstNameEditText.setText(filter.getFirstName());

            EditText familyNameEditText = (EditText) findViewById(R.id.edit_text_family_name);
            familyNameEditText.setText(filter.getFamilyName());

            EditText birthDateEditText = (EditText) findViewById(R.id.edit_text_birth_date);
            birthDateEditText.setText(filter.getBirthDate());

            EditText heightEditText = (EditText) findViewById(R.id.edit_text_height);
            if (filter.getHeight() == 0.0)
                heightEditText.setHint(getResources().getString(R.string.no_filter));
            else
                heightEditText.setText(Double.toString(filter.getHeight()));

            EditText nbChildrenEditText = (EditText) findViewById(R.id.edit_text_children_nb);
            if (filter.getChildrenNb() == -1)
                nbChildrenEditText.setHint(getResources().getString(R.string.no_filter));
            else
                nbChildrenEditText.setText(Integer.toString(filter.getChildrenNb()));

            EditText cityEditText = (EditText) findViewById(R.id.edit_text_city);
            cityEditText.setText(filter.getCity());
        }
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
                Log.d("TOPMENU", "Save filters selected");
                saveFilter();
                return true;
            case R.id.top_menu_item_settings:
                Log.d("TOPMENU", "Settings selected");
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;
        }
        return false;
    }

    public void saveFilter()
    {
        Log.d("TOPMENU", "Save filters");

        EditText pseudoEditText = (EditText) findViewById(R.id.edit_text_pseudo);
        String pseudoFilter = pseudoEditText.getText().toString();

        EditText firstNameEditText = (EditText) findViewById(R.id.edit_text_first_name);
        String firstNameFilter = firstNameEditText.getText().toString();

        EditText familyNameEditText = (EditText) findViewById(R.id.edit_text_family_name);
        String familyNameFilter = familyNameEditText.getText().toString();

        EditText birthDateEditText = (EditText) findViewById(R.id.edit_text_birth_date);
        String birthDateFilter = birthDateEditText.getText().toString();

        Spinner genderSpinner = (Spinner) findViewById(R.id.spinner_gender);
        String genderFilter = genderSpinner.getSelectedItem().toString();

        Spinner loveStatusSpinner = (Spinner) findViewById(R.id.spinner_love_status);
        String loveStatusFilter = loveStatusSpinner.getSelectedItem().toString();

        EditText heightEditText = (EditText) findViewById(R.id.edit_text_height);
        double height = 0.0;
        if (!heightEditText.getText().toString().equals("")
                && !heightEditText.getText().toString().equals(getResources().getString(R.string.no_filter)))
            height = Double.parseDouble(heightEditText.getText().toString());

        Spinner smokerSpinner = (Spinner) findViewById(R.id.spinner_smoker);
        String smokerFilter = smokerSpinner.getSelectedItem().toString();

        EditText nbChildrenEditText = (EditText) findViewById(R.id.edit_text_children_nb);
        int nbChildren = -1;
        if (!nbChildrenEditText.getText().toString().equals("")
                && !nbChildrenEditText.getText().toString().equals(getResources().getString(R.string.no_filter)))
            nbChildren = Integer.parseInt(nbChildrenEditText.getText().toString());

        Spinner countrySpinner = (Spinner) findViewById(R.id.spinner_country);
        String countryFilter = countrySpinner.getSelectedItem().toString();

        EditText cityEditText = (EditText) findViewById(R.id.edit_text_city);
        String cityFilter = cityEditText.getText().toString();

        filter = new Filter(pseudoFilter, firstNameFilter, familyNameFilter, birthDateFilter, genderFilter, loveStatusFilter, height, smokerFilter, nbChildren, countryFilter, cityFilter);

        Intent intent = new Intent(this, FindMatchsActivity.class);
        intent.putExtra(User.EXTRA_PSEUDO, currentUser.getPseudo());
        intent.putExtra(Filter.EXTRA_FILTER, filter);
        startActivity(intent);
    }
}
