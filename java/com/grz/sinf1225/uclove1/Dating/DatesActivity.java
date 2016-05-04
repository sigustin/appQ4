package com.grz.sinf1225.uclove1.Dating;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.grz.sinf1225.uclove1.Matching.OverviewData;
import com.grz.sinf1225.uclove1.Profile.ProfileActivity;
import com.grz.sinf1225.uclove1.Profile.ProfileOverviewAdapter;
import com.grz.sinf1225.uclove1.R;
import com.grz.sinf1225.uclove1.SettingsActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DatesActivity extends AppCompatActivity
{

    private RecyclerView m_recyclerView;
    private RecyclerView.Adapter m_recyclerViewAdapter;
    private RecyclerView.LayoutManager m_recyclerViewLayoutManager;
    /*
    CurrentUser currentUser;
     */

    private List<DateData> tmpDateOverviews;
    private String tmpPseudo1 = "angelina426", tmpPseudo2 = "AD.EL.E";
    private int tmpProfilePictureRes1 = R.drawable.angelina_jolie_profile_picture, tmpProfilePictureRes2 = R.drawable.adele_profile_picture;
    private String tmpDateTime1 = "01/06/2016";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dates);

        tmpDateOverviews = new ArrayList<>();
        tmpDateOverviews.add(new DateData(tmpPseudo1, tmpProfilePictureRes1, tmpDateTime1));
        tmpDateOverviews.add(new DateData(tmpPseudo2, tmpProfilePictureRes2, null));
        /*
        currentUser = (CurrentUser) getIntent().getSerializableExtra(CurrentUser.EXTRA_CURRENT_USER);
         */

        m_recyclerView = (RecyclerView) findViewById(R.id.date_overviews_recycler_view);
        m_recyclerViewLayoutManager = new LinearLayoutManager(this);
        m_recyclerView.setLayoutManager(m_recyclerViewLayoutManager);
        m_recyclerViewAdapter = new DateOverviewAdapter(tmpDateOverviews, new DateOverviewAdapter.OnDateOverviewClickedListener() {
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
            case R.id.top_menu_item_quit:
                Log.d("TOPMENU", "Quit selected");
                return true;
        }
        return false;
    }

    public boolean onAddDateButtonClicked(View view)
    {
        Log.d("FLOATINGBUTTON", "Add date");
        return true;
    }

    public void onDateOverviewClicked(DateData dateData)
    {
        Log.d("DATE", "Date overview clicked : " +dateData.toString());
    }

}
