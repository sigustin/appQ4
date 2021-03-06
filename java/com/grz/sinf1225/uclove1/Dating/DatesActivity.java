package com.grz.sinf1225.uclove1.Dating;

import android.app.AlertDialog;
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
import com.grz.sinf1225.uclove1.Matching.OverviewData;
import com.grz.sinf1225.uclove1.Profile.ProfileActivity;
import com.grz.sinf1225.uclove1.Profile.ProfileOverviewAdapter;
import com.grz.sinf1225.uclove1.R;
import com.grz.sinf1225.uclove1.SettingsActivity;
import com.grz.sinf1225.uclove1.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DatesActivity extends AppCompatActivity
{
    public static final String EXTRA_DATE_PSEUDO = "UCLove.EXTRA_DATE_PSEUDO";

    private RecyclerView m_recyclerView;
    private RecyclerView.Adapter m_recyclerViewAdapter;
    private RecyclerView.LayoutManager m_recyclerViewLayoutManager;
    /*
    CurrentUser currentUser;
     */
    User currentUser;
    private List<Meeting> meetingList;
    private List<DateData> datesOverviews;

    private List<DateData> tmpDateOverviews;
    private String tmpPseudo1 = "angelina426", tmpPseudo2 = "AD.EL.E";
    private int tmpProfilePictureRes1 = R.drawable.angelina_jolie_profile_picture, tmpProfilePictureRes2 = R.drawable.adele_profile_picture;
    private String tmpDateTime1 = "01/06/2016";
    private final String tmpLocation1 = "McDonalds Washington";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dates);
    }

    @Override
    public void onResume()
    {
        super.onResume();

        tmpDateOverviews = new ArrayList<>();
        tmpDateOverviews.add(new DateData(tmpPseudo1, tmpProfilePictureRes1, tmpDateTime1, tmpLocation1));
        tmpDateOverviews.add(new DateData(tmpPseudo2, tmpProfilePictureRes2, null, null));
        /*
        currentUser = (CurrentUser) getIntent().getSerializableExtra(CurrentUser.EXTRA_CURRENT_USER);
         */
        String currentPseudo = getIntent().getStringExtra(User.EXTRA_PSEUDO);
        currentUser = new User(currentPseudo);

        meetingList = Database.getAllMeetings(currentPseudo);
        Log.d("DEBUG", "Number of meetings : " +Integer.toString(meetingList.size()));

        datesOverviews = new ArrayList<DateData>();
        for (int i=0; i<meetingList.size(); i++)
        {
            String[] pseudos = meetingList.get(i).getUserPseudos();
            User otherUser;
            if (pseudos[0].equals(currentPseudo))
                otherUser = new User(pseudos[1]);
            else
                otherUser = new User(pseudos[0]);
            Log.d("DEBUG", "Meeting on " +meetingList.get(i).getDate());
            datesOverviews.add(new DateData(otherUser.getPseudo(), otherUser.getProfilePicture(), meetingList.get(i).getDate(), meetingList.get(i).getLocation()));
        }

        m_recyclerView = (RecyclerView) findViewById(R.id.date_overviews_recycler_view);
        m_recyclerViewLayoutManager = new LinearLayoutManager(this);
        m_recyclerView.setLayoutManager(m_recyclerViewLayoutManager);
        m_recyclerViewAdapter = new DateOverviewAdapter(datesOverviews, new DateOverviewAdapter.OnDateOverviewClickedListener() {
            @Override
            public void onOverviewClicked(DateData dateData) {
                onDateOverviewClicked(dateData);
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

    public boolean onAddDateButtonClicked(View view)
    {
        Log.d("FLOATINGBUTTON", "Add date");
        Intent intent = new Intent(this, SetDateActivity.class);
        intent.putExtra(User.EXTRA_PSEUDO, currentUser.getPseudo());
        startActivity(intent);
        return true;
    }

    public void onDateOverviewClicked(DateData dateData)
    {
        Log.d("DATE", "Date overview clicked : " +dateData.toString());
        if (dateData.m_dateTime != null)
        {
            String msgToDisplay = getResources().getString(R.string.date_with) +" "+ dateData.m_friendPseudo +"\n";
            msgToDisplay += getResources().getString(R.string.at) +" "+ dateData.m_location +"\n";
            msgToDisplay += getResources().getString(R.string.on) +" "+ dateData.m_dateTime;
            new AlertDialog.Builder(this)
                    .setTitle(getResources().getString(R.string.date_details))
                    .setMessage(msgToDisplay)
                    .setNegativeButton(getResources().getString(R.string.close_popup), null)
                    .show();
        }
        else
        {
            Log.d("ACTIVITY", "Getting on setting date activity");
            Intent intent = new Intent(this, SetDateActivity.class);
            /*
            intent.putExtra(CurrentUser.EXTRA_CURRENT_USER, currentUser);
            intent.putExtra(User.EXTRA_PSEUDO, dateData.m_friendPseudo);
             */
            intent.putExtra(User.EXTRA_PSEUDO, currentUser.getPseudo());
            intent.putExtra(EXTRA_DATE_PSEUDO, dateData.m_friendPseudo);
            startActivity(intent);
        }
    }

}
