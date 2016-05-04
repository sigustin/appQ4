package com.grz.sinf1225.uclove1.Profile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.grz.sinf1225.uclove1.Profile.EditProfileActivity;
import com.grz.sinf1225.uclove1.R;

public class MyProfileActivity extends AppCompatActivity
{
    final String tmpPseudo = "++Jesus++", tmpName = "Vincent Wertz", tmpBirthDate = "01/01/1901", tmpAge = "225 years old", tmpGender = "M", tmpLoveStatus = "Married", tmpInterestedIn = "F", tmpHeight = "1m75", tmpDescription = "Math teacher", tmpSmoker = "No", tmpChildrenNb = "2", tmpCountry = "Belgium", tmpCity = "Louvain-La-Neuve";
    final int profilePictureRes = R.drawable.hollande_profile_picture;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        /*
        CurrentUser currentUser = (CurrentUser) getIntent().getSerializableExtra(CurrentUser.EXTRA_CURRENT_USER);
         */

        ImageView profilePicture = (ImageView) findViewById(R.id.profile_picture);
        profilePicture.setImageResource(profilePictureRes);
        TextView pseudo = (TextView) findViewById(R.id.pseudo);
        pseudo.setText(tmpPseudo);
        TextView name = (TextView) findViewById(R.id.name);
        name.setText(tmpName);
        TextView birthDate = (TextView) findViewById(R.id.birth_date);
        birthDate.setText(tmpBirthDate);
        TextView age = (TextView) findViewById(R.id.age);
        age.setText(tmpAge);
        TextView gender = (TextView) findViewById(R.id.gender);
        gender.setText(tmpGender);
        TextView loveStatus = (TextView) findViewById(R.id.love_status);
        loveStatus.setText(tmpLoveStatus);
        TextView interestedIn = (TextView) findViewById(R.id.interested_in);
        interestedIn.setText(tmpInterestedIn);
        TextView height = (TextView) findViewById(R.id.height);
        height.setText(tmpHeight);
        TextView description = (TextView) findViewById(R.id.description);
        description.setText(tmpDescription);
        TextView smoker = (TextView) findViewById(R.id.smoker);
        smoker.setText(tmpSmoker);
        TextView childrenNb = (TextView) findViewById(R.id.children_nb);
        childrenNb.setText(tmpChildrenNb);
        TextView country = (TextView) findViewById(R.id.country);
        country.setText(tmpCountry);
        TextView city = (TextView) findViewById(R.id.city);
        city.setText(tmpCity);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.top_menu_my_profile_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.top_menu_item_change_informations:
                Log.d("TOPMENU", "Edit selected");
                goToEditProfileActivity();
                return  true;
        }
        return false;
    }

    public void onEditButtonClicked(View view)
    {
        Log.d("BUTTON", "Edit mode selected");
        goToEditProfileActivity();
    }

    public void goToEditProfileActivity()
    {
        Intent intent = new Intent(this, EditProfileActivity.class);
        /*
        intent.putExtra(CurrentUser.EXTRA_CURRENT_USER, currentUser);
         */
        startActivity(intent);
    }

}
