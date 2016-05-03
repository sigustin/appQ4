package com.grz.sinf1225.uclove1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class FriendsActivity extends AppCompatActivity
{

    final int tmpNbFriends = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        /*
        CurrentUser currentUser = (CurrentUser) getIntent().getSerializableExtra(CurrentUser.EXTRA_CURRENT_USER);
         */

        RelativeLayout baseLayout = (RelativeLayout) findViewById(R.id.relative_layout_base);
        ArrayList<View> friendBoxes = new ArrayList<>();

        for (int i=0; i<tmpNbFriends; i++)
            friendBoxes.add( addFriendView(baseLayout, friendBoxes) );

        /*ImageView profilePicture = new ImageView(this);
        profilePicture.setId(View.generateViewId());
        RelativeLayout.LayoutParams profilePictureParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, (int) getResources().getDimension(R.dimen.big_profile_picture_height));
        profilePictureParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        profilePictureParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE); profilePictureParams.addRule(RelativeLayout.ALIGN_PARENT_END, RelativeLayout.TRUE);
        profilePictureParams.setMargins(
                (int) getResources().getDimension(R.dimen.profile_picture_margin),
                (int) getResources().getDimension(R.dimen.profile_picture_margin),
                (int) getResources().getDimension(R.dimen.profile_picture_margin),
                (int) getResources().getDimension(R.dimen.profile_picture_margin));
        profilePicture.setImageResource(profilePictureRes);
        baseLayout.addView(profilePicture, profilePictureParams);

        TextView pseudo = new TextView(this);
        RelativeLayout.LayoutParams pseudoParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        pseudoParams.addRule(RelativeLayout.BELOW, profilePicture.getId());
        pseudoParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE); pseudoParams.addRule(RelativeLayout.ALIGN_PARENT_END, RelativeLayout.TRUE);
        pseudo.setText(tmpPseudo);
        setListItemPadding(pseudo);
        baseLayout.addView(pseudo, pseudoParams);

        TextView name = new TextView(this);
        RelativeLayout.LayoutParams nameParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        nameParams.addRule(RelativeLayout.BELOW, R.id.separator1);
        nameParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE); nameParams.addRule(RelativeLayout.ALIGN_PARENT_END, RelativeLayout.TRUE);
        name.setText(tmpName);
        setListItemPadding(name);
        baseLayout.addView(name, nameParams);*/
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
            case R.id.top_menu_item_quit:
                Log.d("TOPMENU", "Quit selected");
                return true;
        }
        return false;
    }

    public View addFriendView(ViewGroup baseLayout, ArrayList<View> boxes)
    {
        View box = new View(this);
        box.setId(View.generateViewId());
        RelativeLayout.LayoutParams boxParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) getResources().getDimension(R.dimen.small_profile_picture_height));
        if (boxes.size() == 0)
            boxParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        else
            boxParams.addRule(RelativeLayout.BELOW, boxes.get(boxes.size()-1).getId());
        boxParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT); boxParams.addRule(RelativeLayout.ALIGN_PARENT_START);
        boxParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT); boxParams.addRule(RelativeLayout.ALIGN_PARENT_END);
        boxParams.setMargins(
                (int) getResources().getDimension(R.dimen.box_margin_horizontal),
                (int) getResources().getDimension(R.dimen.box_margin_vertical),
                (int) getResources().getDimension(R.dimen.box_margin_horizontal),
                (int) getResources().getDimension(R.dimen.box_margin_vertical)
        );
        box.setBackgroundResource(R.color.white);
        box.setElevation((float) getResources().getDimension(R.dimen.view_elevation));
        baseLayout.addView(box, boxParams);



        return box;
    }

    public boolean onAddFriendsButtonClicked(View view)
    {
        Log.d("FLOATINGBUTTON", "Add friends button clicked");
        return true;
    }

    public boolean onFriendSelected(View view)
    {
        switch (view.getId())
        {
            case R.id.first_friend_box:
                Log.d("BOX", "First friend selected");
                displayFriend(null);
                return true;
            case R.id.second_friend_box:
                Log.d("BOX", "Second friend selected");
                displayFriend(null);
                return true;
        }
        return false;
    }

    public boolean displayFriend(String pseudo)
    {
        //check pseudo (if found -> return true else false)
        Intent intent = new Intent(this, ProfileActivity.class);
        /*
        intent.putExtra(CurrentUser.EXTRA_CURRENT_USER, currentUser);
         */
        startActivity(intent);
        return true;
    }
}
