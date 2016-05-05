package com.grz.sinf1225.uclove1.Profile;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.grz.sinf1225.uclove1.Database;
import com.grz.sinf1225.uclove1.MainActivity;
import com.grz.sinf1225.uclove1.R;
import com.grz.sinf1225.uclove1.User;

import java.util.Calendar;
import java.util.Locale;

public class EditProfileActivity extends AppCompatActivity
{
    final String tmpPseudo = "++Jesus++", tmpFirstName = "Vincent", tmpFamilyName = "Wertz", tmpBirthDate = "01/01/1901", tmpDescription = "Math teacher", tmpChildrenNb = "2", tmpCity = "Louvain-La-Neuve";
    final int tmpGender = 0, tmpLoveStatus = 2, tmpInterestedIn = 0, tmpCountry = 19;
    final int profilePictureRes = R.drawable.hollande_profile_picture;
    final double tmpHeight = 1.75;
    final boolean tmpSmoker = false;

    /*
    CurrentUser currentUser;
     */
    User currentUser;
    private boolean m_isRegistration;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        m_isRegistration = (boolean) getIntent().getBooleanExtra(Database.EXTRA_IS_REGISTRATION, true);

        currentUser = (User) getIntent().getSerializableExtra(User.EXTRA_TMP);

        if (!m_isRegistration)
        {
            /*
            currentUser = (CurrentUser) getIntent().getSerializableExtra(CurrentUser.EXTRA_CURRENT_USER);
             */

            ImageView profilePicture = (ImageView) findViewById(R.id.profile_picture);
            if (currentUser.getProfilePicture() == 0)
                profilePicture.setImageResource(R.drawable.ic_person_black_48dp);
            else
                profilePicture.setImageResource(currentUser.getProfilePicture());

            EditText pseudo = (EditText) findViewById(R.id.edit_text_pseudo);
            pseudo.setText(currentUser.getPseudo());
            pseudo.setKeyListener(null);

            EditText password = (EditText) findViewById(R.id.edit_text_password);
            password.setText(getResources().getString(R.string.cant_change_password));
            password.setKeyListener(null);

            EditText firstName = (EditText) findViewById(R.id.edit_text_first_name);
            firstName.setText(currentUser.getFirstName());

            EditText familyName = (EditText) findViewById(R.id.edit_text_family_name);
            familyName.setText(currentUser.getFamilyName());

            EditText birthDate = (EditText) findViewById(R.id.edit_text_birth_date);
            birthDate.setText(currentUser.getBirthDate());

            Spinner gender = (Spinner) findViewById(R.id.spinner_gender);
            ArrayAdapter<CharSequence> genderAdapter = ArrayAdapter.createFromResource(this, R.array.array_genders, android.R.layout.simple_spinner_item);
            genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            gender.setAdapter(genderAdapter);
            gender.setSelection(genderAdapter.getPosition(currentUser.getGender()));

            Spinner loveStatus = (Spinner) findViewById(R.id.spinner_love_status);
            ArrayAdapter<CharSequence> loveStatusAdapter = ArrayAdapter.createFromResource(this, R.array.array_love_statuses, android.R.layout.simple_spinner_item);
            loveStatusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            loveStatus.setAdapter(loveStatusAdapter);
            loveStatus.setSelection(loveStatusAdapter.getPosition(currentUser.getLoveStatus()));

            Spinner interestedIn = (Spinner) findViewById(R.id.spinner_interested_in);
            ArrayAdapter<CharSequence> interestedInAdapter = ArrayAdapter.createFromResource(this, R.array.array_interested_in_values, android.R.layout.simple_spinner_item);
            interestedInAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            interestedIn.setAdapter(interestedInAdapter);
            interestedIn.setSelection(interestedInAdapter.getPosition(currentUser.getInterestedIn()));

            EditText height = (EditText) findViewById(R.id.edit_text_height);
            height.setText(Double.toString(currentUser.getHeight()));

            EditText description = (EditText) findViewById(R.id.description);
            description.setText(currentUser.getDescription());

            CheckBox smoker = (CheckBox) findViewById(R.id.checkbox_smoker);
            smoker.setChecked(currentUser.getSmoker());

            EditText childrenNb = (EditText) findViewById(R.id.edit_text_children_nb);
            childrenNb.setText(Integer.toString(currentUser.getChildrenNb()));

            Spinner country = (Spinner) findViewById(R.id.spinner_country);
            ArrayAdapter<CharSequence> countriesAdapter = ArrayAdapter.createFromResource(this, R.array.array_countries, android.R.layout.simple_spinner_item);
            countriesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            country.setAdapter(countriesAdapter);
            country.setSelection(countriesAdapter.getPosition(currentUser.getCountry()));

            EditText city = (EditText) findViewById(R.id.edit_text_city);
            city.setText(currentUser.getCity());
        }
        else
        {
            ImageView profilePicture = (ImageView) findViewById(R.id.profile_picture);
            profilePicture.setImageResource(R.drawable.ic_person_black_48dp);

            EditText pseudo = (EditText) findViewById(R.id.edit_text_pseudo);

            EditText password = (EditText) findViewById(R.id.edit_text_password);

            Spinner gender = (Spinner) findViewById(R.id.spinner_gender);
            ArrayAdapter<CharSequence> genderAdapter = ArrayAdapter.createFromResource(this, R.array.array_genders, android.R.layout.simple_spinner_item);
            genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            gender.setAdapter(genderAdapter);

            Spinner loveStatus = (Spinner) findViewById(R.id.spinner_love_status);
            ArrayAdapter<CharSequence> loveStatusAdapter = ArrayAdapter.createFromResource(this, R.array.array_love_statuses, android.R.layout.simple_spinner_item);
            loveStatusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            loveStatus.setAdapter(loveStatusAdapter);

            Spinner interestedIn = (Spinner) findViewById(R.id.spinner_interested_in);
            ArrayAdapter<CharSequence> interestedInAdapter = ArrayAdapter.createFromResource(this, R.array.array_interested_in_values, android.R.layout.simple_spinner_item);
            interestedInAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            interestedIn.setAdapter(interestedInAdapter);

            Spinner country = (Spinner) findViewById(R.id.spinner_country);
            ArrayAdapter<CharSequence> countriesAdapter = ArrayAdapter.createFromResource(this, R.array.array_countries, android.R.layout.simple_spinner_item);
            countriesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            country.setAdapter(countriesAdapter);
        }
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
                if(saveInformations())
                    finish();
                return true;
        }
        return false;
    }

    public boolean saveInformations()
    {
        Log.d("BUTTON", "Save informations");

        boolean validEntries = true;

        EditText pseudoEditText = (EditText) findViewById(R.id.edit_text_pseudo);
        String pseudo = pseudoEditText.getText().toString();
        if (pseudo.equals("")) {
            Toast noInput = Toast.makeText(this, R.string.must_enter_pseudo, Toast.LENGTH_LONG);
            noInput.show();
            validEntries = false;
        }
        if (m_isRegistration  && validEntries && Database.isPseudoTaken(pseudo)) {
            Toast pseudoTaken = Toast.makeText(this, R.string.pseudo_taken, Toast.LENGTH_LONG);
            pseudoTaken.show();
            validEntries = false;
        }

        EditText passwordEditText = (EditText) findViewById(R.id.edit_text_password);
        String password = passwordEditText.getText().toString();
        if (m_isRegistration && validEntries && password.equals(""))
        {
            Toast pseudoTaken = Toast.makeText(this, R.string.no_password, Toast.LENGTH_LONG);
            pseudoTaken.show();
            validEntries = false;
        }

        EditText firstNameEditText = (EditText) findViewById(R.id.edit_text_first_name);
        String firstName = firstNameEditText.getText().toString();
        if (firstName.equals("") && validEntries) {
            Toast noInput = Toast.makeText(this, R.string.must_enter_first_name, Toast.LENGTH_LONG);
            noInput.show();
            validEntries = false;
        }

        EditText familyNameEditText = (EditText) findViewById(R.id.edit_text_family_name);
        String familyName = familyNameEditText.getText().toString();
        if (familyName.equals("")  && validEntries) {
            Toast noInput = Toast.makeText(this, R.string.must_enter_family_name, Toast.LENGTH_LONG);
            noInput.show();
            validEntries = false;
        }

        EditText birthDateEditText = (EditText) findViewById(R.id.edit_text_birth_date);
        String birthDate = birthDateEditText.getText().toString();
        if (birthDate.equals("") && validEntries) {
            Toast noInput = Toast.makeText(this, R.string.must_enter_birth_date, Toast.LENGTH_LONG);
            noInput.show();
            validEntries = false;
        }

        Spinner genderSpinner = (Spinner) findViewById(R.id.spinner_gender);
        String gender = genderSpinner.getSelectedItem().toString();

        Spinner loveStatusSpinner = (Spinner) findViewById(R.id.spinner_love_status);
        String loveStatus = loveStatusSpinner.getSelectedItem().toString();

        Spinner interestedInSpinner = (Spinner) findViewById(R.id.spinner_interested_in);
        String interestedIn = interestedInSpinner.getSelectedItem().toString();

        EditText heightEditText = (EditText) findViewById(R.id.edit_text_height);
        double height;
        if (heightEditText.getText().toString().equals(""))
            height = 0.0;
        else
            height = Double.parseDouble(heightEditText.getText().toString());

        EditText descriptionEditText = (EditText) findViewById(R.id.description);
        String description = descriptionEditText.getText().toString();

        CheckBox smokerCheckBox = (CheckBox) findViewById(R.id.checkbox_smoker);
        boolean smoker = smokerCheckBox.isChecked();

        EditText nbChildrenEditText = (EditText) findViewById(R.id.edit_text_children_nb);
        int nbChildren;
        if (nbChildrenEditText.getText().toString().equals(""))
            nbChildren = 0;
        else
            nbChildren = Integer.parseInt(nbChildrenEditText.getText().toString());

        Spinner countrySpinner = (Spinner) findViewById(R.id.spinner_country);
        String country = countrySpinner.getSelectedItem().toString();

        EditText cityEditText = (EditText) findViewById(R.id.edit_text_city);
        String city = cityEditText.getText().toString();
        if (city.equals("") && validEntries) {
            Toast noInput = Toast.makeText(this, R.string.must_enter_city, Toast.LENGTH_LONG);
            noInput.show();
            validEntries = false;
        }

        Log.d("DEBUG", String.valueOf(validEntries));
        if (validEntries)
        {
            if (m_isRegistration)
            {
                User newUser = new User(pseudo, firstName, familyName, birthDate, gender, loveStatus,
                        String.format("%1$td/%1$tm/%1$tY", Calendar.getInstance()), height, description, smoker, interestedIn, 0, null, nbChildren, country, city, null,
                        User.DEFAULT_VISIBILITY, User.DEFAULT_VISIBILITY,User.DEFAULT_VISIBILITY,User.DEFAULT_VISIBILITY,
                        User.DEFAULT_VISIBILITY,User.DEFAULT_VISIBILITY,User.DEFAULT_VISIBILITY,User.DEFAULT_VISIBILITY,User.DEFAULT_VISIBILITY);
                Database.addNewUser(newUser, password);
            }
            else
            {
                if (!Database.isPseudoTaken(pseudo))
                {
                    Toast noInput = Toast.makeText(this, R.string.cant_change_pseudo, Toast.LENGTH_LONG);
                    noInput.show();
                    return false;
                }

                Database.updateFirstName(pseudo, firstName);
                Database.updateFamilyName(pseudo, familyName);
                Database.updateBirthDate(pseudo, birthDate);
                Database.updateGender(pseudo, gender);
                Database.updateLoveStatus(pseudo, loveStatus);
                Database.updateInterestedIn(pseudo, interestedIn);
                Database.updateHeight(pseudo, height);
                Database.updateDescription(pseudo, description);
                Database.updateSmoker(pseudo, smoker);
                Database.updateChildrenNb(pseudo, nbChildren);
                Database.updateCountry(pseudo, country);
                Database.updateCity(pseudo, city);
            }
        }
        return validEntries;
    }

    public boolean changeProfilePicture(View view)
    {
        Log.d("IMAGE", "Change profile picture");
        return false;
    }

    public void onEditVisibilityButtonClicked(View view)
    {
        Log.d("BUTTON", "Edit visibility button clicked");
        Intent intent = new Intent(this, EditVisibilityActivity.class);
        /*
        intent.putExtra(CurrentUser.EXTRA_CURRENT_USER, currentUser);
         */
        startActivity(intent);
    }
}
