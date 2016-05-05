package com.grz.sinf1225.uclove1.Matching;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.grz.sinf1225.uclove1.Profile.ProfileActivity;
import com.grz.sinf1225.uclove1.Profile.ProfileOverviewAdapter;
import com.grz.sinf1225.uclove1.R;
import com.grz.sinf1225.uclove1.SettingsActivity;

import java.util.ArrayList;
import java.util.List;

public class FindMatchsActivity extends AppCompatActivity
{
    private RecyclerView m_recyclerView;
    private RecyclerView.Adapter m_recyclerViewAdapter;
    private RecyclerView.LayoutManager m_recyclerViewLayoutManager;
    /*
    CurrentUser currentUser;
     */

    private List<OverviewData> tmpFriendsOverview;
    private final int tmpProfilePictureRes1 = R.drawable.angelina_jolie_profile_picture, tmpRequest1 = OverviewData.NONE;
    private final String tmpPseudo1 = "angelina24", tmpAge1 = "42 years old", tmpCity1 = "New York";
    private final int tmpProfilePictureRes2 = R.drawable.adele_profile_picture, tmpRequest2 = OverviewData.NONE;
    private final String tmpPseudo2 = "A.D.E.LE", tmpAge2 = "27 years old", tmpCity2 = "London";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_matchs);

        tmpFriendsOverview = new ArrayList<>();
        tmpFriendsOverview.add(new OverviewData(tmpProfilePictureRes1, tmpPseudo1, tmpAge1, tmpCity1, tmpRequest1));
        tmpFriendsOverview.add(new OverviewData(tmpProfilePictureRes2, tmpPseudo2, tmpAge2, tmpCity2, tmpRequest2));
        /*
        currentUser = (CurrentUser) getIntent().getSerializableExtra(CurrentUser.EXTRA_CURRENT_USER);
         */

        m_recyclerView = (RecyclerView) findViewById(R.id.profile_overviews_recycler_view);
        m_recyclerViewLayoutManager = new LinearLayoutManager(this);
        m_recyclerView.setLayoutManager(m_recyclerViewLayoutManager);
        m_recyclerViewAdapter = new ProfileOverviewAdapter(tmpFriendsOverview, new ProfileOverviewAdapter.OnOverviewClickedListener() {
            public void onOverviewClicked(String pseudo)
            {
                Log.d("OVERVIEWLISTENER", "RelativeLayoutClicked " + pseudo);
                onFriendOverviewClicked(pseudo);
            }
        }, this);
        m_recyclerView.setAdapter(m_recyclerViewAdapter);
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

    public void onFriendOverviewClicked(String pseudo)
    {
        Log.d("SIG", "You clicked friend " + pseudo);
        displayFriend(pseudo);
    }

    public boolean displayFriend(String pseudo)
    {
        //check pseudo (if found -> return true else false)
        Intent intent = new Intent(this, ProfileActivity.class);
        /*
        intent.putExtra(CurrentUser.EXTRA_CURRENT_USER, currentUser);
        intent.putExtra(User.EXTRA_USER, Database.getUser(pseudo));
         */
        startActivity(intent);
        return true;
    }

    public void onSetFilterButtonClicked(View view)
    {
        Log.d("BUTTON", "Set filter button pressed");
        Intent intent = new Intent(this, SetFilterActivity.class);
        /*
        intent.putExtra(CurrentUser.EXTRA_CURRENT_USER, currentUser);
         */
        startActivity(intent);
    }
}