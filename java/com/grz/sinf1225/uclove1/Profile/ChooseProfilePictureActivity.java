package com.grz.sinf1225.uclove1.Profile;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;

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
        onChoose(R.drawable.profile_pic1);
    }

    public void onChoose2(View view)
    {
        onChoose(R.drawable.profile_pic2);
    }

    public void onChoose3(View view)
    {
        onChoose(R.drawable.profile_pic3);
    }

    public void onChoose4(View view)
    {
        onChoose(R.drawable.profile_pic4);
    }

    public void onChoose5(View view)
    {
        onChoose(R.drawable.profile_pic5);
    }

    public void onChoose6(View view)
    {
        onChoose(R.drawable.profile_pic6);
    }

    public void onChoose7(View view)
    {
        onChoose(R.drawable.profile_pic7);
    }

    public void onChoose8(View view)
    {
        onChoose(R.drawable.profile_pic8);
    }

    public void onChoose(int pictureRes)
    {
        CheckBox asProfilePicture = (CheckBox) findViewById(R.id.other_picture_checkbox);
        if (asProfilePicture.isChecked())
        {
            Log.d("DEBUG", "Is checked");
            Database.updateProfilePicture(currentUser, pictureRes);
        }
        else
        {
            if (Database.isPictureBoundToUser(currentUser.getPseudo(), pictureRes))
                Database.removePictureBond(currentUser.getPseudo(), pictureRes);
            else
                Database.addPicture(currentUser.getPseudo(), pictureRes);
        }
        finish();
    }
}
