package com.grz.sinf1225.uclove1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.grz.sinf1225.uclove1.Chat.ConversationsActivity;
import com.grz.sinf1225.uclove1.Dating.DatesActivity;
import com.grz.sinf1225.uclove1.Matching.FindMatchsActivity;
import com.grz.sinf1225.uclove1.Matching.FriendsActivity;
import com.grz.sinf1225.uclove1.Matching.OverviewData;
import com.grz.sinf1225.uclove1.Profile.MyProfileActivity;
import com.grz.sinf1225.uclove1.Profile.ProfileActivity;
import com.grz.sinf1225.uclove1.Profile.ProfileOverviewAdapter;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity
{
    final String tmpPseudo = "++Jesus++", tmpAge = "225 years old", tmpCity = "Louvain-La-Neuve";
    final int profilePictureRes = R.drawable.hollande_profile_picture;
    final User.RelationshipType tmpRequest = User.RelationshipType.ONESELF;

    private RecyclerView m_recyclerView;
    private RecyclerView.Adapter m_recyclerViewAdapter;
    private RecyclerView.LayoutManager m_recyclerViewLayoutManager;
    /*
    private CurrentUser currentUser;
     */
    private User m_currentUser;
    private List<OverviewData> m_overviewCurrentPseudoList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        /*
        currentUser = (CurrentUser) getIntent().getSerializableExtra(CurrentUser.EXTRA_CURRENT_USER);
         */
    }

    @Override
    public void onResume()
    {
        super.onResume();

        String currentPseudo = getIntent().getStringExtra(User.EXTRA_PSEUDO);
        m_currentUser = new User(currentPseudo);

        m_overviewCurrentPseudoList = new ArrayList<OverviewData>();
        m_overviewCurrentPseudoList.add(new OverviewData(m_currentUser.getProfilePicture(), m_currentUser.getPseudo(),
                Integer.toString(m_currentUser.getAge()) +" "+ getResources().getString(R.string.years_old),
                m_currentUser.getCity(), User.RelationshipType.ONESELF));

        m_recyclerView = (RecyclerView) findViewById(R.id.profile_overviews_recycler_view);
        m_recyclerViewLayoutManager = new LinearLayoutManager(this);
        m_recyclerView.setLayoutManager(m_recyclerViewLayoutManager);
        m_recyclerViewAdapter = new ProfileOverviewAdapter(m_overviewCurrentPseudoList, new ProfileOverviewAdapter.OnOverviewClickedListener() {
            @Override
            public void onOverviewClicked(String pseudo) {
                onProfileOverviewClicked(pseudo);
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
                Log.d("TOPMENU", "top menu item settings selected");
                onSettingsButtonClicked(null);;
                return true;
        }
        return false;
    }

    public void onMyProfileButtonClicked(View view)
    {
        Log.d("BUTTON", "my profile button clicked");
        goToActivity(ProfileActivity.class);
    }

    public void onFindFriendsButtonClicked(View view)
    {
        Log.d("BUTTON", "find friends button clicked");
        goToActivity(FindMatchsActivity.class);
    }

    public void onMyFriendsButtonClicked(View view)
    {
        Log.d("BUTTON", "my friends button clicked");
        goToActivity(FriendsActivity.class);
    }

    public void onMessagesButtonClicked(View view)
    {
        Log.d("BUTTON", "messages button clicked");
        goToActivity(ConversationsActivity.class);
    }

    public void onMyDatesButtonClicked(View view)
    {
        Log.d("BUTTON", "my dates button clicked");
        goToActivity(DatesActivity.class);
    }

    public void onSettingsButtonClicked(View view)
    {
        Log.d("BUTTON", "settings button clicked");
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    public void onProfileOverviewClicked(String pseudo)
    {
        Log.d("OVERVIEW", "Overview clicked");
        goToActivity(ProfileActivity.class);
    }

    public void goToActivity(Class<?> activityClass)
    {
        Intent intent = new Intent(this, activityClass);
        /*
        intent.putExtra(CurrentUser.EXTRA_CURRENT_USER, currentUser);
         */
        intent.putExtra(User.EXTRA_PSEUDO, m_currentUser.getPseudo());
        intent.putExtra(User.EXTRA_USER, m_currentUser);//For profile activity
        startActivity(intent);
    }
}
