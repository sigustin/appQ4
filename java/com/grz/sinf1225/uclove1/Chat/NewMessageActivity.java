package com.grz.sinf1225.uclove1.Chat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.grz.sinf1225.uclove1.Database;
import com.grz.sinf1225.uclove1.Dating.DateOverviewAdapter;
import com.grz.sinf1225.uclove1.R;
import com.grz.sinf1225.uclove1.SettingsActivity;
import com.grz.sinf1225.uclove1.User;

public class NewMessageActivity extends AppCompatActivity
{
    private User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_message);
    }

    @Override
    public void onResume()
    {
        super.onResume();

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
                Log.d("TOPMENU", "Settings selected");
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;
        }
        return false;
    }

    public void onDoneButtonClicked(View view)
    {
        Log.d("BUTTON", "Done button clicked");
        EditText friendPseudoEditText = (EditText) findViewById(R.id.friend_pseudo_edit_text);
        String friendPseudo = friendPseudoEditText.getText().toString();
        boolean notFriend = false;
        if (Database.getRelationshipType(currentUser.getPseudo(), friendPseudo) != User.RelationshipType.FRIENDS)
        {
            Toast badInput = Toast.makeText(this, R.string.no_date_with_no_friends, Toast.LENGTH_LONG);
            badInput.show();
            notFriend = true;
        }

        if (!notFriend)
        {
            Intent intent = new Intent(this, ChatActivity.class);
            intent.putExtra(User.EXTRA_PSEUDO, currentUser.getPseudo());
            intent.putExtra(User.EXTRA_USER, new User(friendPseudo));
            startActivity(intent);
        }
    }
}
