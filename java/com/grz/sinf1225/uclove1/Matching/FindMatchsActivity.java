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

import com.grz.sinf1225.uclove1.Database;
import com.grz.sinf1225.uclove1.Profile.ProfileActivity;
import com.grz.sinf1225.uclove1.Profile.ProfileOverviewAdapter;
import com.grz.sinf1225.uclove1.R;
import com.grz.sinf1225.uclove1.SettingsActivity;
import com.grz.sinf1225.uclove1.User;

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
    User currentUser;
    private List<OverviewData> matchesOverviewList;
    private List<User> matchesList;

    private List<OverviewData> tmpFriendsOverview;
    private final int tmpProfilePictureRes1 = R.drawable.angelina_jolie_profile_picture;
    private final User.RelationshipType tmpRequest1 = User.RelationshipType.NONE, tmpRequest2 = User.RelationshipType.NONE;
    private final String tmpPseudo1 = "angelina24", tmpAge1 = "42 years old", tmpCity1 = "New York";
    private final int tmpProfilePictureRes2 = R.drawable.adele_profile_picture;
    private final String tmpPseudo2 = "A.D.E.LE", tmpAge2 = "27 years old", tmpCity2 = "London";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_matchs);
    }

    @Override
    public void onResume()
    {
        super.onResume();

        String currentPseudo = getIntent().getStringExtra(User.EXTRA_PSEUDO);
        currentUser = new User(currentPseudo);

        matchesList = Database.getSimpleMatches(currentUser);
        Log.d("DEBUG", "Number of matches : " +Integer.toString(matchesList.size()));

        matchesOverviewList = new ArrayList<OverviewData>();
        for (int i=0; i<matchesList.size(); i++)
        {
            User currentMatch = matchesList.get(i);
            matchesOverviewList.add(new OverviewData(currentMatch.getProfilePicture(),
                    currentMatch.getPseudo(),
                    Integer.toString(currentMatch.getAge()) +" "+ getResources().getString(R.string.years_old),
                    currentMatch.getCity(),
                    Database.getRelationshipType(currentPseudo, currentMatch.getPseudo())));
        }


        m_recyclerView = (RecyclerView) findViewById(R.id.profile_overviews_recycler_view);
        m_recyclerViewLayoutManager = new LinearLayoutManager(this);
        m_recyclerView.setLayoutManager(m_recyclerViewLayoutManager);
        m_recyclerViewAdapter = new ProfileOverviewAdapter(matchesOverviewList, new ProfileOverviewAdapter.OnOverviewClickedListener() {
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
        intent.putExtra(User.EXTRA_PSEUDO, currentUser.getPseudo());
        intent.putExtra(User.EXTRA_USER, new User(pseudo));
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
