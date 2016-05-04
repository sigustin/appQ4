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
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.grz.sinf1225.uclove1.Matching.OverviewData;
import com.grz.sinf1225.uclove1.Profile.ProfileActivity;
import com.grz.sinf1225.uclove1.Profile.ProfileOverviewAdapter;
import com.grz.sinf1225.uclove1.R;
import com.grz.sinf1225.uclove1.SettingsActivity;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

public class SetDateActivity extends AppCompatActivity
{
    List<OverviewData> tmpFriendOverview;
    private final int tmpFriendProfilePictureRes = R.drawable.adele_profile_picture;
    private final String tmpFriendPseudo = "AD.E.LE", tmpFriendAge = "24 years old", tmpFriendCity = "London";

    /*
    CurrentUser currentUser;
     */
    private RecyclerView m_recyclerView;
    private RecyclerView.Adapter m_recyclerViewAdapter;
    private RecyclerView.LayoutManager m_recyclerViewLayoutManager;
    private String m_friendPseudo;
    private EditText m_enterPseudo;
    private boolean m_newDate;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_date);

        /*
        currentUser = (CurrentUser) getIntent().getSerializableExtra(CurrentUser.EXTRA_CURRENT_USER);
        m_friendPseudo = (String) getIntent().getStringExtra(User.EXTRA_PSEUDO);
         */
        m_friendPseudo = (String) getIntent().getStringExtra(DatesActivity.EXTRA_PSEUDO);

        if (m_friendPseudo != null)
        {
            m_newDate = false;

            setTitle(getResources().getString(R.string.set_date_activity_title_part) + " " + m_friendPseudo);

            tmpFriendOverview = new ArrayList<>();
            tmpFriendOverview.add(new OverviewData(tmpFriendProfilePictureRes, tmpFriendPseudo, tmpFriendAge, tmpFriendCity, OverviewData.FRIEND));

            m_recyclerView = (RecyclerView) findViewById(R.id.profile_overview_recycler_view);
            m_recyclerViewLayoutManager = new LinearLayoutManager(this);
            m_recyclerView.setLayoutManager(m_recyclerViewLayoutManager);
            m_recyclerViewAdapter = new ProfileOverviewAdapter(tmpFriendOverview, new ProfileOverviewAdapter.OnOverviewClickedListener() {
                public void onOverviewClicked(String pseudo)
                {
                    Log.d("OVERVIEWLISTENER", "RelativeLayoutClicked " + pseudo);
                    onFriendOverviewClicked(pseudo);
                }
            }, this);
            m_recyclerView.setAdapter(m_recyclerViewAdapter);
        }
        else
        {
            m_newDate = true;

            setTitle(getResources().getString(R.string.set_date_activity_title_new));

            LinearLayout baseLayout = (LinearLayout) findViewById(R.id.base_linear_layout);
            EditText enterPseudo = new EditText(this);
            enterPseudo.setHint(getResources().getString(R.string.enter_friends_pseudo));
            enterPseudo.setSingleLine(true);
            enterPseudo.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            baseLayout.addView(enterPseudo, 0);

            m_enterPseudo = enterPseudo;
        }
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
        Log.d("OVERVIEW", "Overview clicked");
        Intent intent = new Intent(this, ProfileActivity.class);
        /*
        intent.putExtra(CurrentUser.EXTRA_CURRENT_USER, currentUser);
        intent.putExtra(User.EXTRA_USER, Database.getUser(pseudo));
         */
        startActivity(intent);
    }

    public void onSaveDateButtonClicked(View view)
    {
        String enteredPseudo = "";
        if (m_newDate)
            enteredPseudo = m_enterPseudo.getText().toString();

        boolean[] disponibilities = new boolean[7];
        CheckBox currentCheckBox;
        currentCheckBox = (CheckBox) findViewById(R.id.monday_checkbox);
        disponibilities[0] = currentCheckBox.isChecked();
        currentCheckBox = (CheckBox) findViewById(R.id.tuesday_checkbox);
        disponibilities[1] = currentCheckBox.isChecked();
        currentCheckBox = (CheckBox) findViewById(R.id.wednesday_checkbox);
        disponibilities[2] = currentCheckBox.isChecked();
        currentCheckBox = (CheckBox) findViewById(R.id.thursday_checkbox);
        disponibilities[3] = currentCheckBox.isChecked();
        currentCheckBox = (CheckBox) findViewById(R.id.friday_checkbox);
        disponibilities[4] = currentCheckBox.isChecked();
        currentCheckBox = (CheckBox) findViewById(R.id.saturday_checkbox);
        disponibilities[5] = currentCheckBox.isChecked();
        currentCheckBox = (CheckBox) findViewById(R.id.sunday_checkbox);
        disponibilities[6] = currentCheckBox.isChecked();

        EditText msgEditText = (EditText) findViewById(R.id.edit_text_location);
        String location = msgEditText.getText().toString();
        String tmp = "";
        for (int i=0; i<7; i++)
            tmp += disponibilities[i] +" ";
        if (m_newDate)
            Log.d("BUTTON", "Save date button clicked " + enteredPseudo +" "+ location +" "+ tmp);
        else
            Log.d("BUTTON", "Save date button clicked " + location +" "+ tmp);
        finish();
    }

}
