package com.grz.sinf1225.uclove1.Profile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.grz.sinf1225.uclove1.R;

public class EditVisibilityActivity extends AppCompatActivity
{
    public final int tmpSpinnerPos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_visibility);

        Spinner firstNameSpinner = (Spinner) findViewById(R.id.spinner_first_name);
        ArrayAdapter<CharSequence> firstNameAdapter = ArrayAdapter.createFromResource(this, R.array.array_visibilities, android.R.layout.simple_spinner_item);
        firstNameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        firstNameSpinner.setAdapter(firstNameAdapter);
        firstNameSpinner.setSelection(tmpSpinnerPos);

        Spinner familyName = (Spinner) findViewById(R.id.spinner_family_name);
        ArrayAdapter<CharSequence> familyNameAdapter = ArrayAdapter.createFromResource(this, R.array.array_visibilities, android.R.layout.simple_spinner_item);
        familyNameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        familyName.setAdapter(familyNameAdapter);
        familyName.setSelection(tmpSpinnerPos);

        Spinner birthDate = (Spinner) findViewById(R.id.spinner_birth_date);
        ArrayAdapter<CharSequence> birthDateAdapter = ArrayAdapter.createFromResource(this, R.array.array_visibilities, android.R.layout.simple_spinner_item);
        birthDateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        birthDate.setAdapter(birthDateAdapter);
        birthDate.setSelection(tmpSpinnerPos);

        Spinner gender = (Spinner) findViewById(R.id.spinner_gender);
        ArrayAdapter<CharSequence> genderAdapter = ArrayAdapter.createFromResource(this, R.array.array_visibilities, android.R.layout.simple_spinner_item);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender.setAdapter(genderAdapter);
        gender.setSelection(tmpSpinnerPos);

        Spinner loveStatus = (Spinner) findViewById(R.id.spinner_love_status);
        ArrayAdapter<CharSequence> loveStatusAdapter = ArrayAdapter.createFromResource(this, R.array.array_visibilities, android.R.layout.simple_spinner_item);
        loveStatusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        loveStatus.setAdapter(loveStatusAdapter);
        loveStatus.setSelection(tmpSpinnerPos);

        Spinner height = (Spinner) findViewById(R.id.spinner_height);
        ArrayAdapter<CharSequence> heightAdapter = ArrayAdapter.createFromResource(this, R.array.array_visibilities, android.R.layout.simple_spinner_item);
        heightAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        height.setAdapter(heightAdapter);
        height.setSelection(tmpSpinnerPos);

        Spinner smoker = (Spinner) findViewById(R.id.spinner_smoker);
        ArrayAdapter<CharSequence> smokerAdapter = ArrayAdapter.createFromResource(this, R.array.array_visibilities, android.R.layout.simple_spinner_item);
        smokerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        smoker.setAdapter(smokerAdapter);
        smoker.setSelection(tmpSpinnerPos);

        Spinner childrenNb = (Spinner) findViewById(R.id.spinner_children_nb);
        ArrayAdapter<CharSequence> childrenNbAdapter = ArrayAdapter.createFromResource(this, R.array.array_visibilities, android.R.layout.simple_spinner_item);
        childrenNbAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        childrenNb.setAdapter(childrenNbAdapter);
        childrenNb.setSelection(tmpSpinnerPos);

        Spinner city = (Spinner) findViewById(R.id.spinner_city);
        ArrayAdapter<CharSequence> cityAdapter = ArrayAdapter.createFromResource(this, R.array.array_visibilities, android.R.layout.simple_spinner_item);
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        city.setAdapter(cityAdapter);
        city.setSelection(tmpSpinnerPos);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.top_menu_edit_profile_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.top_menu_item_save:
                Log.d("TOPMENU", "Save");
                if(saveVisibilities())
                    finish();
                return true;
        }
        return false;
    }

    public boolean saveVisibilities()
    {
        Spinner firstNameSpinner = (Spinner) findViewById(R.id.spinner_first_name);
        String firstName = firstNameSpinner.getSelectedItem().toString();

        Spinner familyNameSpinner = (Spinner) findViewById(R.id.spinner_family_name);
        String familyName = familyNameSpinner.getSelectedItem().toString();

        Spinner birthDateSpinner = (Spinner) findViewById(R.id.spinner_birth_date);
        String birthDate = birthDateSpinner.getSelectedItem().toString();

        Spinner genderSpinner = (Spinner) findViewById(R.id.spinner_gender);
        String gender = genderSpinner.getSelectedItem().toString();

        Spinner loveStatusSpinner = (Spinner) findViewById(R.id.spinner_love_status);
        String loveStatus = loveStatusSpinner.getSelectedItem().toString();

        Spinner heightSpinner = (Spinner) findViewById(R.id.spinner_height);
        String height = heightSpinner.getSelectedItem().toString();

        Spinner smokerSpinner = (Spinner) findViewById(R.id.spinner_smoker);
        String smoker = smokerSpinner.getSelectedItem().toString();

        Spinner childrenNbSpinner = (Spinner) findViewById(R.id.spinner_children_nb);
        String childrenNb = childrenNbSpinner.getSelectedItem().toString();

        Spinner citySpinner = (Spinner) findViewById(R.id.spinner_city);
        String city = citySpinner.getSelectedItem().toString();

        return true;
    }
}
