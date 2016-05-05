package com.grz.sinf1225.uclove1.Profile;

import android.content.Intent;
import android.media.Image;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.grz.sinf1225.uclove1.R;
import com.grz.sinf1225.uclove1.SettingsActivity;

public class ProfileActivity extends AppCompatActivity
{
    final String tmpPseudo = "angelina24", tmpName = "Angelina Jolie", tmpBirthDate = "01/01/1984", tmpAge = "42 years old", tmpGender = "F", tmpLoveStatus = "Married", tmpInterestedIn = "Both", tmpHeight = "1m70", tmpDescription = "Famous actress", tmpSmoker = "No", tmpChildrenNb = "10", tmpCountry = "Etats-Unis", tmpCity = "Washington D.C.";
    final int profilePictureRes = R.drawable.angelina_jolie_profile_picture;

    /*
    private CurrentUser currentUser;
    private User userDisplayed;
     */

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        /*
        currentUser = (CurrentUser) getIntent().getSerializableExtra(CurrentUser.EXTRA_CURRENT_USER);
        userDisplayed = (User) getIntent().getSerializableExtra(User.EXTRA_PSEUDO);
         */

        setViews();
    }

    private void setViews()
    {
        ImageView profilePicture = (ImageView) findViewById(R.id.profile_picture);
        if (/*Database.isVisible(userDisplayed.getPseudo(), (int) Database.PROFILE_PICTURE, currentUser.getPseudo())*/true)
            profilePicture.setImageResource(profilePictureRes);
        else
            profilePicture.setImageResource(R.drawable.ic_person_black_48dp);

        TextView pseudo = (TextView) findViewById(R.id.pseudo);
        if (/*can be viewed*/true)
            pseudo.setText(tmpPseudo);
        else
            pseudo.setText(R.string.invisible);

        //...$

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

        Button actionButton = (Button) findViewById(R.id.action_button);
        if (/*pseudoUserDisplayed.equals(currentUser.getPseudo())*/false)
        {
            actionButton.setText(getResources().getString(R.string.button_change_informations));
            actionButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_mode_edit_black_24dp, 0, 0, 0);
        }
        else if (/*Database.getRelationshipStatus(currentUser.getPseudo(), pseudoUserDisplayed)) == Database.NONE*/false)
        {
            actionButton.setText(getResources().getString(R.string.ask_as_friend));
            actionButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_add_black_24dp, 0, 0, 0);

        }
        else if (/*Database.getRelationshipStatus(currentUser.getPseudo(), pseudoUserDisplayed)) == Database.REQUEST*/true)
        {
            actionButton.setText(getResources().getString(R.string.accept_request));
            actionButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_done_black_24dp, 0, 0, 0);
        }
        else if (/*Database.getRelationshipStatus(currentUser.getPseudo(), pseudoUserDisplayed)) == Database.REQUEST_SENT*/false)
        {
            actionButton.setText(getResources().getString(R.string.request_sent));
        }
        else if (/*Database.getRelationshipStatus(currentUser.getPseudo(), pseudoUserDisplayed)) == Database.REJECTION*/false)
        {
            actionButton.setText(getResources().getString(R.string.blocked));
        }
        else if (/*Database.getRelationshipStatus(currentUser.getPseudo(), pseudoUserDisplayed)) == Database.FRIENDS*/false)
        {
            actionButton.setText(getResources().getString(R.string.unfriend));
            actionButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_cancel_black_24dp, 0, 0, 0);
        }
    }

    private void setListItemPadding(View view)
    {
        view.setPadding(
                (int) getResources().getDimension(R.dimen.list_item_padding_horizontal),
                (int) getResources().getDimension(R.dimen.list_item_padding_vertical),
                (int) getResources().getDimension(R.dimen.list_item_padding_horizontal),
                (int) getResources().getDimension(R.dimen.list_item_padding_vertical));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.top_menu_menu_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.top_menu_item_settings:
                Log.d("TOPMENU", "Settings selected");
                Intent intent = new Intent(this, SettingsActivity.class);
                /*
                intent.putExtra(CurrentUser.EXTRA_CURRENT_USER, currentUser);
                 */
                startActivity(intent);
                return true;
        }
        return false;
    }


    public void onActionButtonClick(View view)
    {
        Log.d("BUTTON", "Action button pressed");
    }
}
