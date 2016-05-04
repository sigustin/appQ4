package com.grz.sinf1225.uclove1.Profile;

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

import com.grz.sinf1225.uclove1.R;

public class EditProfileActivity extends AppCompatActivity
{
    final String tmpPseudo = "++Jesus++", tmpFirstName = "Vincent", tmpFamilyName = "Wertz", tmpBirthDate = "01/01/1901", tmpDescription = "Math teacher", tmpChildrenNb = "2", tmpCity = "Louvain-La-Neuve";
    final int tmpGender = 0, tmpLoveStatus = 2, tmpInterestedIn = 0, tmpCountry = 19;
    final int profilePictureRes = R.drawable.hollande_profile_picture;
    final double tmpHeight = 1.75;
    final boolean tmpSmoker = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        /*
        CurrentUser currentUser = (CurrentUser) getIntent().getSerializableExtra(CurrentUser.EXTRA_CURRENT_USER);
         */

        ImageView profilePicture = (ImageView) findViewById(R.id.profile_picture);
        profilePicture.setImageResource(profilePictureRes);

        EditText pseudo = (EditText) findViewById(R.id.edit_text_pseudo);
        pseudo.setText(tmpPseudo);

        EditText firstName = (EditText) findViewById(R.id.edit_text_first_name);
        firstName.setText(tmpFirstName);

        EditText familyName = (EditText) findViewById(R.id.edit_text_family_name);
        familyName.setText(tmpFamilyName);

        EditText birthDate = (EditText) findViewById(R.id.edit_text_birth_date);
        birthDate.setText(tmpBirthDate);

        Spinner gender = (Spinner) findViewById(R.id.spinner_gender);
        ArrayAdapter<CharSequence> genderAdapter = ArrayAdapter.createFromResource(this, R.array.array_genders, android.R.layout.simple_spinner_item);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender.setAdapter(genderAdapter);
        gender.setSelection(tmpGender);

        Spinner loveStatus = (Spinner) findViewById(R.id.spinner_love_status);
        ArrayAdapter<CharSequence> loveStatusAdapter = ArrayAdapter.createFromResource(this, R.array.array_love_statuses, android.R.layout.simple_spinner_item);
        loveStatusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        loveStatus.setAdapter(loveStatusAdapter);
        loveStatus.setSelection(tmpLoveStatus);

        Spinner interestedIn = (Spinner) findViewById(R.id.spinner_interested_in);
        ArrayAdapter<CharSequence> interestedInAdapter = ArrayAdapter.createFromResource(this, R.array.array_interested_in_values, android.R.layout.simple_spinner_item);
        interestedInAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        interestedIn.setAdapter(interestedInAdapter);
        interestedIn.setSelection(tmpInterestedIn);

        EditText height = (EditText) findViewById(R.id.edit_text_height);
        height.setText(Double.toString(tmpHeight));

        EditText description = (EditText) findViewById(R.id.description);
        description.setText(tmpDescription);

        CheckBox smoker = (CheckBox) findViewById(R.id.checkbox_smoker);
        smoker.setChecked(tmpSmoker);

        EditText childrenNb = (EditText) findViewById(R.id.edit_text_children_nb);
        childrenNb.setText(tmpChildrenNb);

        Spinner country = (Spinner) findViewById(R.id.spinner_country);
        ArrayAdapter<CharSequence> countriesAdapter = ArrayAdapter.createFromResource(this, R.array.array_countries, android.R.layout.simple_spinner_item);
        countriesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        country.setAdapter(countriesAdapter);
        country.setSelection(tmpCountry);

        EditText city = (EditText) findViewById(R.id.edit_text_city);
        city.setText(tmpCity);
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
                saveInformations(null);
                return true;
            case  R.id.top_menu_item_quit:
                Log.d("TOPMENU", "Quit selected");
                return true;
        }
        return false;
    }

    public void saveInformations(View view)
    {
        Log.d("BUTTON", "Save informations");
        /*
        currentUser.set...
         */
        finish();
    }

    public boolean changeProfilePicture(View view)
    {
        Log.d("IMAGE", "Change profile picture");
        return false;
    }
}
