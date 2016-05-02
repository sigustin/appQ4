package com.grz.sinf1225.uclove1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.annotation.Documented;

public class MenuActivity extends AppCompatActivity
{
    final String tmpPseudo = "++Jesus++", tmpAge = "225 years old";
    final int profilePictureRes = R.drawable.hollande_profile_picture;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        ImageView profilePicture = (ImageView) findViewById(R.id.profile_picture);
        profilePicture.setImageResource(profilePictureRes);
        TextView pseudoDisplay = (TextView) findViewById(R.id.pseudo);
        pseudoDisplay.setText(tmpPseudo);
        TextView ageDisplay = (TextView) findViewById(R.id.age);
        ageDisplay.setText(tmpAge);
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
                Log.d("TOPMENU", "top menu item settings selected");
                onSettingsButtonClicked(null);;
                return true;
            case R.id.top_menu_item_quit:
                Log.d("TOPMENU", "top menu item quit selected");
                return true;
        }
        return false;
    }

    public void onMyProfileButtonClicked(View view)
    {
        Log.d("BUTTON", "my profile button clicked");
        Intent intent = new Intent(this, MyProfileActivity.class);
        startActivity(intent);
    }

    public void onFindFriendsButtonClicked(View view)
    {
        Log.d("BUTTON", "find friends button clicked");
    }

    public void onMyFriendsButtonClicked(View view)
    {
        Log.d("BUTTON", "my friends button clicked");
        Intent intent = new Intent(this, FriendsActivity.class);
        startActivity(intent);
    }

    public void onMessagesButtonClicked(View view)
    {
        Log.d("BUTTON", "messages button clicked");
    }

    public void onMyDatesButtonClicked(View view)
    {
        Log.d("BUTTON", "my dates button clicked");
        Intent intent = new Intent(this, DatesActivity.class);
        startActivity(intent);
    }

    public void onSettingsButtonClicked(View view)
    {
        Log.d("BUTTON", "settings button clicked");
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    public void onOverviewClicked(View view)
    {
        Log.d("OVERVIEW", "Overview clicked");
        Intent intent = new Intent(this, MyProfileActivity.class);
        startActivity(intent);
    }
}
