package com.grz.sinf1225.uclove1.Profile;

import android.content.Intent;
import android.media.Image;
import android.net.rtp.RtpStream;
import android.provider.ContactsContract;
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
import android.widget.Toast;

import com.grz.sinf1225.uclove1.Database;
import com.grz.sinf1225.uclove1.R;
import com.grz.sinf1225.uclove1.SettingsActivity;
import com.grz.sinf1225.uclove1.User;

import java.util.List;

public class ProfileActivity extends AppCompatActivity
{
    /*
    private CurrentUser currentUser;
    */
    private User currentUser;
    private User userDisplayed;

    private int showingPictureIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        /*
        currentUser = (CurrentUser) getIntent().getSerializableExtra(CurrentUser.EXTRA_CURRENT_USER);
        */
        String currentPseudo = getIntent().getStringExtra(User.EXTRA_PSEUDO);
        currentUser = new User(currentPseudo);
        userDisplayed = (User) getIntent().getSerializableExtra(User.EXTRA_USER);
    }

    @Override
    public void onResume()
    {
        super.onResume();

        currentUser = new User(currentUser.getPseudo());
        userDisplayed = new User(userDisplayed.getPseudo());
        setViews();
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

    private void setViews()
    {
        showingPictureIndex = -1;

        ImageView profilePicture = (ImageView) findViewById(R.id.profile_picture);
        if (userDisplayed.getProfilePicture() != 0)
            profilePicture.setImageResource(userDisplayed.getProfilePicture());
        else
            profilePicture.setImageResource(R.drawable.ic_person_black_48dp);

        TextView pseudo = (TextView) findViewById(R.id.pseudo);
        pseudo.setText(userDisplayed.getPseudo());

        TextView name = (TextView) findViewById(R.id.name);
        if (Database.isVisible(userDisplayed.getPseudo(), Database.UserInformation.FIRST_NAME, currentUser.getPseudo())
                && Database.isVisible(userDisplayed.getPseudo(), Database.UserInformation.LAST_NAME, currentUser.getPseudo()))
            name.setText(userDisplayed.getFirstName() +" "+ userDisplayed.getFamilyName());
        else
            name.setText(R.string.invisible);

        TextView birthDate = (TextView) findViewById(R.id.birth_date);
        if (Database.isVisible(userDisplayed.getPseudo(), Database.UserInformation.BIRTH_DATE, currentUser.getPseudo()))
            birthDate.setText(userDisplayed.getBirthDate());
        else
            birthDate.setText(R.string.invisible);

        TextView age = (TextView) findViewById(R.id.age);
        if (Database.isVisible(userDisplayed.getPseudo(), Database.UserInformation.AGE, currentUser.getPseudo()))
            age.setText(Integer.toString(userDisplayed.getAge()) +" "+ getResources().getString(R.string.years_old));
        else
            age.setText(R.string.invisible);

        TextView gender = (TextView) findViewById(R.id.gender);
        if (Database.isVisible(userDisplayed.getPseudo(), Database.UserInformation.GENDER, currentUser.getPseudo()))
            gender.setText(userDisplayed.getGender());
        else
            gender.setText(R.string.invisible);

        TextView loveStatus = (TextView) findViewById(R.id.love_status);
        if (Database.isVisible(userDisplayed.getPseudo(), Database.UserInformation.LOVE_STATUS, currentUser.getPseudo()))
            loveStatus.setText(userDisplayed.getLoveStatus());
        else
            loveStatus.setText(R.string.invisible);

        TextView interestedIn = (TextView) findViewById(R.id.interested_in);
        interestedIn.setText(userDisplayed.getInterestedIn());

        TextView height = (TextView) findViewById(R.id.height);
        if (Database.isVisible(userDisplayed.getPseudo(), Database.UserInformation.HEIGHT, currentUser.getPseudo()))
            height.setText(Double.toString(userDisplayed.getHeight()));
        else
            height.setText(R.string.invisible);

        TextView description = (TextView) findViewById(R.id.description);
        description.setText(userDisplayed.getDescription());

        TextView smoker = (TextView) findViewById(R.id.smoker);
        if (Database.isVisible(userDisplayed.getPseudo(), Database.UserInformation.SMOKER, currentUser.getPseudo()))
        {
            if (userDisplayed.getSmoker())
                smoker.setText(getResources().getString(R.string.yes));
            else
                smoker.setText(getResources().getString(R.string.no));
        }
        else
            smoker.setText(R.string.invisible);

        TextView childrenNb = (TextView) findViewById(R.id.children_nb);
        if (Database.isVisible(userDisplayed.getPseudo(), Database.UserInformation.CHILDREN_NB, currentUser.getPseudo()))
            childrenNb.setText(Integer.toString(userDisplayed.getChildrenNb()));
        else
            childrenNb.setText(R.string.invisible);

        TextView country = (TextView) findViewById(R.id.country);
        country.setText(userDisplayed.getCountry());

        TextView city = (TextView) findViewById(R.id.city);
        if (Database.isVisible(userDisplayed.getPseudo(), Database.UserInformation.CITY, currentUser.getPseudo()))
            city.setText(userDisplayed.getCity());
        else
            city.setText(R.string.invisible);

        Button actionButton = (Button) findViewById(R.id.action_button);
        if (currentUser.isSameUser(userDisplayed.getPseudo()))
        {
            actionButton.setText(getResources().getString(R.string.button_change_informations));
            actionButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_mode_edit_black_24dp, 0, 0, 0);
        }
        else if (Database.getRelationshipType(currentUser.getPseudo(), userDisplayed.getPseudo()) == User.RelationshipType.NONE)
        {
            actionButton.setText(getResources().getString(R.string.ask_as_friend));
            actionButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_add_black_24dp, 0, 0, 0);
        }
        else if ((Database.getRelationshipType(currentUser.getPseudo(), userDisplayed.getPseudo()) == User.RelationshipType.REQUEST)
            && !Database.hasSentRequest(currentUser.getPseudo(), userDisplayed.getPseudo()))
        {
            actionButton.setText(getResources().getString(R.string.accept_request));
            actionButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_done_black_24dp, 0, 0, 0);
        }
        else if ( (Database.getRelationshipType(currentUser.getPseudo(), userDisplayed.getPseudo()) == User.RelationshipType.REQUEST)
            && Database.hasSentRequest(currentUser.getPseudo(), userDisplayed.getPseudo()))
        {
            actionButton.setText(getResources().getString(R.string.request_sent));
        }
        else if (Database.getRelationshipType(currentUser.getPseudo(), userDisplayed.getPseudo()) == User.RelationshipType.REJECTION)
        {
            actionButton.setText(getResources().getString(R.string.blocked));
        }
        else if (Database.getRelationshipType(currentUser.getPseudo(), userDisplayed.getPseudo()) == User.RelationshipType.FRIENDS)
        {
            actionButton.setText(getResources().getString(R.string.unfriend));
            actionButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_cancel_black_24dp, 0, 0, 0);
        }
    }

    public void onActionButtonClick(View view)
    {
        Log.d("BUTTON", "Action button pressed");
        if (currentUser.isSameUser(userDisplayed.getPseudo()))
        {
            Intent intent = new Intent(this, EditProfileActivity.class);
        /*
        intent.putExtra(CurrentUser.EXTRA_CURRENT_USER, currentUser);
         */
            intent.putExtra(User.EXTRA_PSEUDO, currentUser.getPseudo());
            intent.putExtra(Database.EXTRA_IS_REGISTRATION, false);
            startActivity(intent);
        }
        else if (Database.getRelationshipType(currentUser.getPseudo(), userDisplayed.getPseudo()) == User.RelationshipType.NONE)
        {
            //Ask friend
            Database.sendRequest(currentUser.getPseudo(), userDisplayed.getPseudo());
            Toast requestSent = Toast.makeText(this, R.string.request_sent, Toast.LENGTH_LONG);
            requestSent.show();

            Button actionButton = (Button) findViewById(R.id.action_button);
            actionButton.setText(getResources().getString(R.string.request_sent));
        }
        else if ((Database.getRelationshipType(currentUser.getPseudo(), userDisplayed.getPseudo()) == User.RelationshipType.REQUEST)
                && !Database.hasSentRequest(currentUser.getPseudo(), userDisplayed.getPseudo()))
        {
            //Accept request
            Database.acceptRequest(currentUser.getPseudo(), userDisplayed.getPseudo());
            Toast requestAccepted = Toast.makeText(this, R.string.request_accepted, Toast.LENGTH_LONG);
            requestAccepted.show();
        }
        else if ( (Database.getRelationshipType(currentUser.getPseudo(), userDisplayed.getPseudo()) == User.RelationshipType.REQUEST)
                && Database.hasSentRequest(currentUser.getPseudo(), userDisplayed.getPseudo()))
        {
            //Request sent (nothing to do)
            Toast alreadySent = Toast.makeText(this, R.string.already_sent, Toast.LENGTH_LONG);
            alreadySent.show();
        }
        else if (Database.getRelationshipType(currentUser.getPseudo(), userDisplayed.getPseudo()) == User.RelationshipType.REJECTION)
        {
            //Rejection (nothing to do)
            Toast rejected = Toast.makeText(this, R.string.cant_send_request, Toast.LENGTH_LONG);
            rejected.show();
        }
        else if (Database.getRelationshipType(currentUser.getPseudo(), userDisplayed.getPseudo()) == User.RelationshipType.FRIENDS)
        {
            //Unfriend
            Database.unfriend(currentUser.getPseudo(), userDisplayed.getPseudo());
            Toast unfriended = Toast.makeText(this, R.string.unfriend, Toast.LENGTH_LONG);
            unfriended.show();

            Button actionButton = (Button) findViewById(R.id.action_button);
            actionButton.setText(getResources().getString(R.string.ask_as_friend));
            actionButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_add_black_24dp, 0, 0, 0);
        }
    }

    public void switchPicture(View view)
    {
        List<Integer> pictureRes = Database.getPicturesRes(userDisplayed.getPseudo());
        if (pictureRes.size() > 0)
        {
            Log.d("DEBUG", "Nb pictures : " +Integer.toString(pictureRes.size()));
            showingPictureIndex++;
            if (showingPictureIndex == pictureRes.size())
                showingPictureIndex = -1;

            ImageView profilePicture = (ImageView) findViewById(R.id.profile_picture);
            if (showingPictureIndex == -1)
                profilePicture.setImageResource(userDisplayed.getProfilePicture());
            else
                profilePicture.setImageResource(pictureRes.get(showingPictureIndex));
        }
    }
}
