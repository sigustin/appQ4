package com.grz.sinf1225.uclove1.Profile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.grz.sinf1225.uclove1.Database;
import com.grz.sinf1225.uclove1.R;
import com.grz.sinf1225.uclove1.User;

public class EditVisibilityActivity extends AppCompatActivity
{
    public final int tmpSpinnerPos = 0;

    User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_visibility);

        currentUser = (User) getIntent().getSerializableExtra(User.EXTRA_TMP);

        Spinner firstNameSpinner = (Spinner) findViewById(R.id.spinner_first_name);
        ArrayAdapter<CharSequence> firstNameAdapter = ArrayAdapter.createFromResource(this, R.array.array_visibilities, android.R.layout.simple_spinner_item);
        firstNameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        firstNameSpinner.setAdapter(firstNameAdapter);
        firstNameSpinner.setSelection(currentUser.getFirstNameVisibility());

        Spinner familyName = (Spinner) findViewById(R.id.spinner_family_name);
        ArrayAdapter<CharSequence> familyNameAdapter = ArrayAdapter.createFromResource(this, R.array.array_visibilities, android.R.layout.simple_spinner_item);
        familyNameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        familyName.setAdapter(familyNameAdapter);
        familyName.setSelection(currentUser.getFamilyNameVisibility());

        Spinner birthDate = (Spinner) findViewById(R.id.spinner_birth_date);
        ArrayAdapter<CharSequence> birthDateAdapter = ArrayAdapter.createFromResource(this, R.array.array_visibilities, android.R.layout.simple_spinner_item);
        birthDateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        birthDate.setAdapter(birthDateAdapter);
        birthDate.setSelection(currentUser.getBirthDateVisibility());

        Spinner gender = (Spinner) findViewById(R.id.spinner_gender);
        ArrayAdapter<CharSequence> genderAdapter = ArrayAdapter.createFromResource(this, R.array.array_visibilities, android.R.layout.simple_spinner_item);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender.setAdapter(genderAdapter);
        gender.setSelection(currentUser.getGenderVisibility());

        Spinner loveStatus = (Spinner) findViewById(R.id.spinner_love_status);
        ArrayAdapter<CharSequence> loveStatusAdapter = ArrayAdapter.createFromResource(this, R.array.array_visibilities, android.R.layout.simple_spinner_item);
        loveStatusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        loveStatus.setAdapter(loveStatusAdapter);
        loveStatus.setSelection(currentUser.getLoveStatusVisibility());

        Spinner height = (Spinner) findViewById(R.id.spinner_height);
        ArrayAdapter<CharSequence> heightAdapter = ArrayAdapter.createFromResource(this, R.array.array_visibilities, android.R.layout.simple_spinner_item);
        heightAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        height.setAdapter(heightAdapter);
        height.setSelection(currentUser.getHeightVisibility());

        Spinner smoker = (Spinner) findViewById(R.id.spinner_smoker);
        ArrayAdapter<CharSequence> smokerAdapter = ArrayAdapter.createFromResource(this, R.array.array_visibilities, android.R.layout.simple_spinner_item);
        smokerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        smoker.setAdapter(smokerAdapter);
        smoker.setSelection(currentUser.getSmokerVisibility());

        Spinner childrenNb = (Spinner) findViewById(R.id.spinner_children_nb);
        ArrayAdapter<CharSequence> childrenNbAdapter = ArrayAdapter.createFromResource(this, R.array.array_visibilities, android.R.layout.simple_spinner_item);
        childrenNbAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        childrenNb.setAdapter(childrenNbAdapter);
        childrenNb.setSelection(currentUser.getChildrenNbVisibility());

        Spinner city = (Spinner) findViewById(R.id.spinner_city);
        ArrayAdapter<CharSequence> cityAdapter = ArrayAdapter.createFromResource(this, R.array.array_visibilities, android.R.layout.simple_spinner_item);
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        city.setAdapter(cityAdapter);
        city.setSelection(currentUser.getCityVisibility());
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
        int firstName = firstNameSpinner.getSelectedItemPosition();
        Database.updateFirstNameVisibility(currentUser, firstName);

        Spinner familyNameSpinner = (Spinner) findViewById(R.id.spinner_family_name);
        int familyName = familyNameSpinner.getSelectedItemPosition();
        Database.updateFirstNameVisibility(currentUser, familyName);

        Spinner birthDateSpinner = (Spinner) findViewById(R.id.spinner_birth_date);
        int birthDate = birthDateSpinner.getSelectedItemPosition();
        Database.updateFirstNameVisibility(currentUser, birthDate);

        Spinner genderSpinner = (Spinner) findViewById(R.id.spinner_gender);
        int gender = genderSpinner.getSelectedItemPosition();
        Database.updateFirstNameVisibility(currentUser, gender);

        Spinner loveStatusSpinner = (Spinner) findViewById(R.id.spinner_love_status);
        int loveStatus = loveStatusSpinner.getSelectedItemPosition();
        Database.updateFirstNameVisibility(currentUser, loveStatus);

        Spinner heightSpinner = (Spinner) findViewById(R.id.spinner_height);
        int height = heightSpinner.getSelectedItemPosition();
        Database.updateFirstNameVisibility(currentUser, height);

        Spinner smokerSpinner = (Spinner) findViewById(R.id.spinner_smoker);
        int smoker = smokerSpinner.getSelectedItemPosition();
        Database.updateFirstNameVisibility(currentUser, smoker);

        Spinner childrenNbSpinner = (Spinner) findViewById(R.id.spinner_children_nb);
        int childrenNb = childrenNbSpinner.getSelectedItemPosition();
        Database.updateFirstNameVisibility(currentUser, childrenNb);

        Spinner citySpinner = (Spinner) findViewById(R.id.spinner_city);
        int city = citySpinner.getSelectedItemPosition();
        Database.updateFirstNameVisibility(currentUser, city);

        return true;
    }
}
