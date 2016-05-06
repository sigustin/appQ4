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

import com.grz.sinf1225.uclove1.Database;
import com.grz.sinf1225.uclove1.Matching.OverviewData;
import com.grz.sinf1225.uclove1.Profile.ProfileActivity;
import com.grz.sinf1225.uclove1.Profile.ProfileOverviewAdapter;
import com.grz.sinf1225.uclove1.R;
import com.grz.sinf1225.uclove1.SettingsActivity;
import com.grz.sinf1225.uclove1.User;

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
    private User currentUser;

    private List<OverviewData> friendOverview;

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
    }

    @Override
    public void onResume()
    {
        super.onResume();

        String currentPseudo = getIntent().getStringExtra(User.EXTRA_PSEUDO);
        currentUser = new User(currentPseudo);

        m_friendPseudo = (String) getIntent().getStringExtra(DatesActivity.EXTRA_DATE_PSEUDO);

        if (m_friendPseudo != null)
        {
            m_newDate = false;

            setTitle(getResources().getString(R.string.set_date_activity_title_part) + " " + m_friendPseudo);

            friendOverview = new ArrayList<OverviewData>();
            User friend = new User(m_friendPseudo);
            friendOverview.add(new OverviewData(friend.getProfilePicture(), m_friendPseudo,
                    Integer.toString(friend.getAge()) +" "+ getResources().getString(R.string.years_old),
                    friend.getCity(), User.RelationshipType.FRIENDS));

            m_recyclerView = (RecyclerView) findViewById(R.id.profile_overview_recycler_view);
            m_recyclerViewLayoutManager = new LinearLayoutManager(this);
            m_recyclerView.setLayoutManager(m_recyclerViewLayoutManager);
            m_recyclerViewAdapter = new ProfileOverviewAdapter(friendOverview, new ProfileOverviewAdapter.OnOverviewClickedListener() {
                public void onOverviewClicked(String pseudo)
                {
                    Log.d("OVERVIEWLISTENER", "RelativeLayoutClicked " + pseudo);
                    onFriendOverviewClicked(pseudo);
                }
            }, this);
            m_recyclerView.setAdapter(m_recyclerViewAdapter);

            EditText msgEditText = (EditText) findViewById(R.id.edit_text_location);
            String friendLocation = Database.getLocationMeeting(currentPseudo, m_friendPseudo);
            msgEditText.setText(friendLocation);

            boolean[] disponibilities = Database.getDisponibilityDates(currentPseudo, this);
            if (disponibilities != null)
            {
                String tmp = "";
                for (int i = 0; i < 7; i++)
                    tmp += disponibilities[i] + " ";
                Log.d("DEBUG", "Disponibilities " + tmp);
                CheckBox monday = (CheckBox) findViewById(R.id.monday_checkbox);
                monday.setChecked(disponibilities[0]);
                CheckBox tuesday = (CheckBox) findViewById(R.id.tuesday_checkbox);
                tuesday.setChecked(disponibilities[1]);
                CheckBox wednesday = (CheckBox) findViewById(R.id.wednesday_checkbox);
                wednesday.setChecked(disponibilities[2]);
                CheckBox thursday = (CheckBox) findViewById(R.id.thursday_checkbox);
                thursday.setChecked(disponibilities[3]);
                CheckBox friday = (CheckBox) findViewById(R.id.friday_checkbox);
                friday.setChecked(disponibilities[4]);
                CheckBox saturday = (CheckBox) findViewById(R.id.saturday_checkbox);
                saturday.setChecked(disponibilities[5]);
                CheckBox sunday = (CheckBox) findViewById(R.id.sunday_checkbox);
                sunday.setChecked(disponibilities[6]);
            }
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
        intent.putExtra(User.EXTRA_PSEUDO, currentUser.getPseudo());
        intent.putExtra(User.EXTRA_USER, new User(pseudo));
        startActivity(intent);
    }

    public void onSaveDateButtonClicked(View view)
    {
        if (m_newDate)
        {
            String enteredPseudo = m_enterPseudo.getText().toString();

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

            Database.removeDisponibilities(currentUser.getPseudo());

            for (int i=0; i<7; i++)
            {
                if (disponibilities[i])
                {
                    switch (i)
                    {
                        case 0:
                            Database.addDisponibilityDate(currentUser.getPseudo(), getResources().getString(R.string.monday));
                            break;
                        case 1:
                            Database.addDisponibilityDate(currentUser.getPseudo(), getResources().getString(R.string.tuesday));
                            break;
                        case 2:
                            Database.addDisponibilityDate(currentUser.getPseudo(), getResources().getString(R.string.wednesday));
                            break;
                        case 3:
                            Database.addDisponibilityDate(currentUser.getPseudo(), getResources().getString(R.string.thursday));
                            break;
                        case 4:
                            Database.addDisponibilityDate(currentUser.getPseudo(), getResources().getString(R.string.friday));
                            break;
                        case 5:
                            Database.addDisponibilityDate(currentUser.getPseudo(), getResources().getString(R.string.saturday));
                            break;
                        case 6:
                            Database.addDisponibilityDate(currentUser.getPseudo(), getResources().getString(R.string.sunday));
                            break;
                    }
                }
            }

            Meeting meeting = new Meeting(currentUser.getPseudo(), enteredPseudo, location);
            meeting.save();

            String tmp = "";
            for (int i = 0; i < 7; i++)
                tmp += disponibilities[i] + " ";
            if (m_newDate)
                Log.d("BUTTON", "Save date button clicked " + enteredPseudo + " " + location + " " + tmp);
            else
                Log.d("BUTTON", "Save date button clicked " + location + " " + tmp);
            finish();
        }
        else
        {
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

            Database.removeDisponibilities(currentUser.getPseudo());

            for (int i=0; i<7; i++)
            {
                if (disponibilities[i])
                {
                    switch (i)
                    {
                        case 0:
                            Database.addDisponibilityDate(currentUser.getPseudo(), getResources().getString(R.string.monday));
                            break;
                        case 1:
                            Database.addDisponibilityDate(currentUser.getPseudo(), getResources().getString(R.string.tuesday));
                            break;
                        case 2:
                            Database.addDisponibilityDate(currentUser.getPseudo(), getResources().getString(R.string.wednesday));
                            break;
                        case 3:
                            Database.addDisponibilityDate(currentUser.getPseudo(), getResources().getString(R.string.thursday));
                            break;
                        case 4:
                            Database.addDisponibilityDate(currentUser.getPseudo(), getResources().getString(R.string.friday));
                            break;
                        case 5:
                            Database.addDisponibilityDate(currentUser.getPseudo(), getResources().getString(R.string.saturday));
                            break;
                        case 6:
                            Database.addDisponibilityDate(currentUser.getPseudo(), getResources().getString(R.string.sunday));
                            break;
                    }
                }
            }

            disponibilities = Database.getDisponibilityDates(currentUser.getPseudo(), this);
            String tmp = "";
            for (int i = 0; i < 7; i++)
                tmp += disponibilities[i] + " ";
            Log.d("DEBUG", "Disponibilities " + tmp);

            EditText msgEditText = (EditText) findViewById(R.id.edit_text_location);
            String location = msgEditText.getText().toString();

            Meeting meeting = new Meeting(currentUser.getPseudo(), m_friendPseudo, location);
            if(meeting.arrangeDay(this))
            {
                Database.updateMeetingDay(meeting, meeting.getDate());
                Log.d("DEBUG", "Meeting day : " +meeting.getDate());
                Database.removeDisponibilities(currentUser.getPseudo());
                Database.removeDisponibilities(m_friendPseudo);
            }

            finish();
        }
    }

}
