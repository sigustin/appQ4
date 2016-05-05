package com.grz.sinf1225.uclove1.Profile;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.grz.sinf1225.uclove1.Database;
import com.grz.sinf1225.uclove1.R;
import com.grz.sinf1225.uclove1.SettingsActivity;
import com.grz.sinf1225.uclove1.User;

public class ChooseProfilePictureActivity extends AppCompatActivity
{
    private User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_profile_picture);

        String currentPseudo = getIntent().getStringExtra(User.EXTRA_PSEUDO);
        currentUser = new User(currentPseudo);
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
        }
        return false;
    }

    public void onSettingsButtonClicked(View view)
    {
        Log.d("BUTTON", "settings button clicked");
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    public void onChoose1(View view)
    {
        Database.updateProfilePicture(currentUser, R.drawable.profile_pic1);
        finish();
    }

    public void onChoose2(View view)
    {
        Database.updateProfilePicture(currentUser, R.drawable.profile_pic2);
        finish();
    }

    public void onChoose3(View view)
    {
        Database.updateProfilePicture(currentUser, R.drawable.profile_pic3);
        finish();
    }

    public void onChoose4(View view)
    {
        Database.updateProfilePicture(currentUser, R.drawable.profile_pic4);
        finish();
    }
}
