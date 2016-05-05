package com.grz.sinf1225.uclove1.Matching;

import android.content.Intent;
import android.provider.ContactsContract;
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

public class FriendsActivity extends AppCompatActivity
{
    private RecyclerView m_recyclerView;
    private RecyclerView.Adapter m_recyclerViewAdapter;
    private RecyclerView.LayoutManager m_recyclerViewLayoutManager;
    /*
    CurrentUser currentUser;
     */
    User currentUser;

    private List<User> friendsAndRequestList;
    private List<OverviewData> friendsOverviewDataList;

    private List<OverviewData> tmpFriendsOverview;
    private final int tmpProfilePictureRes1 = R.drawable.angelina_jolie_profile_picture;
    private final User.RelationshipType tmpRequest1 = User.RelationshipType.REQUEST, tmpRequest2 = User.RelationshipType.FRIENDS;;
    private final String tmpPseudo1 = "angelina24", tmpAge1 = "42 years old", tmpCity1 = "New York";
    private final int tmpProfilePictureRes2 = R.drawable.adele_profile_picture;
    private final String tmpPseudo2 = "A.D.E.LE", tmpAge2 = "27 years old", tmpCity2 = "London";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        tmpFriendsOverview = new ArrayList<>();
        tmpFriendsOverview.add(new OverviewData(tmpProfilePictureRes1, tmpPseudo1, tmpAge1, tmpCity1, tmpRequest1));
        tmpFriendsOverview.add(new OverviewData(tmpProfilePictureRes2, tmpPseudo2, tmpAge2, tmpCity2, tmpRequest2));
        /*
        currentUser = (CurrentUser) getIntent().getSerializableExtra(CurrentUser.EXTRA_CURRENT_USER);
         */
        /*String currentPseudo = getIntent().getStringExtra(User.EXTRA_PSEUDO);
        currentUser = new User(currentPseudo);

        friendsAndRequestList = Database.getFriendsAndRequests(currentPseudo);
        Log.d("DEBUG", "Number of friends : " +Integer.toString(friendsAndRequestList.size()));

        friendsOverviewDataList = new ArrayList<OverviewData>();
        for (int i=0; i<friendsAndRequestList.size(); i++)
        {
            User currentFriend = friendsAndRequestList.get(i);
            friendsOverviewDataList.add(new OverviewData(currentFriend.getProfilePicture(),
                    currentFriend.getPseudo(),
                    Integer.toString(currentUser.getAge()) +" "+ getResources().getString(R.string.years_old),
                    currentFriend.getCity(),
                    Database.getRelationshipType(currentPseudo, currentFriend.getPseudo())));
        }


        m_recyclerView = (RecyclerView) findViewById(R.id.profile_overviews_recycler_view);
        m_recyclerViewLayoutManager = new LinearLayoutManager(this);
        m_recyclerView.setLayoutManager(m_recyclerViewLayoutManager);
        m_recyclerViewAdapter = new ProfileOverviewAdapter(friendsOverviewDataList, new ProfileOverviewAdapter.OnOverviewClickedListener() {
            public void onOverviewClicked(String pseudo)
            {
                Log.d("OVERVIEWLISTENER", "RelativeLayoutClicked " + pseudo);
                onFriendOverviewClicked(pseudo);
            }
        }, this);
        m_recyclerView.setAdapter(m_recyclerViewAdapter);*/
    }

    @Override
    public void onResume()
    {
        super.onResume();

        String currentPseudo = getIntent().getStringExtra(User.EXTRA_PSEUDO);
        currentUser = new User(currentPseudo);

        friendsAndRequestList = Database.getFriendsAndRequests(currentPseudo);
        Log.d("DEBUG", "Number of friends : " +Integer.toString(friendsAndRequestList.size()));

        friendsOverviewDataList = new ArrayList<OverviewData>();
        for (int i=0; i<friendsAndRequestList.size(); i++)
        {
            User currentFriend = friendsAndRequestList.get(i);
            friendsOverviewDataList.add(new OverviewData(currentFriend.getProfilePicture(),
                    currentFriend.getPseudo(),
                    Integer.toString(currentUser.getAge()) +" "+ getResources().getString(R.string.years_old),
                    currentFriend.getCity(),
                    Database.getRelationshipType(currentPseudo, currentFriend.getPseudo())));
        }


        m_recyclerView = (RecyclerView) findViewById(R.id.profile_overviews_recycler_view);
        m_recyclerViewLayoutManager = new LinearLayoutManager(this);
        m_recyclerView.setLayoutManager(m_recyclerViewLayoutManager);
        m_recyclerViewAdapter = new ProfileOverviewAdapter(friendsOverviewDataList, new ProfileOverviewAdapter.OnOverviewClickedListener() {
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

    public boolean onAddFriendsButtonClicked(View view)
    {
        Log.d("FLOATINGBUTTON", "Add friends button clicked");
        Intent intent = new Intent(this, FindMatchsActivity.class);
        /*
        intent.putExtra(CurrentUser.EXTRA_CURRENT_USER, currentUser);
         */
        startActivity(intent);
        return true;
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
         */
        intent.putExtra(User.EXTRA_PSEUDO, currentUser.getPseudo());
        intent.putExtra(User.EXTRA_USER, new User(pseudo));
        startActivity(intent);
        return true;
    }
}
