package com.grz.sinf1225.uclove1.Chat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.grz.sinf1225.uclove1.R;
import com.grz.sinf1225.uclove1.SettingsActivity;
import com.grz.sinf1225.uclove1.User;

import java.util.ArrayList;
import java.util.List;

public class ConversationsActivity extends AppCompatActivity
{
    private List<ConversationOverviewData> tmpConversationsData;
    private final int tmpProfilePictureRes1 = R.drawable.angelina_jolie_profile_picture;
    private final String tmpPseudo1 = "Angelina244", tmpLastMessage1 = "Hey! this is my last message. A very very long message";
    private final boolean tmpLastMessageRead1 = false;
    private final int tmpProfilePictureRes2 = R.drawable.adele_profile_picture;
    private final String tmpPseudo2 = "ADEL.E", tmpLastMessage2 = "Hey! I luv u";
    private final boolean tmpLastMessageRead2 = true;

    private RecyclerView m_recyclerView;
    private RecyclerView.Adapter m_recyclerViewAdapter;
    private RecyclerView.LayoutManager m_recyclerViewLayoutManager;
    /*
    CurrentUser currentUser;
     */
    User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversations);
    }

    @Override
    public void onResume()
    {
        super.onResume();

        tmpConversationsData = new ArrayList<ConversationOverviewData>();
        tmpConversationsData.add(new ConversationOverviewData(tmpProfilePictureRes1, tmpPseudo1, tmpLastMessage1, tmpLastMessageRead1));
        tmpConversationsData.add(new ConversationOverviewData(tmpProfilePictureRes2, tmpPseudo2, tmpLastMessage2, tmpLastMessageRead2));

        /*
        currentUser = (CurrentUser) getIntent().getSerializableExtra(CurrentUser.EXTRA_CURRENT_USER);
         */
        String currentPseudo = getIntent().getStringExtra(User.EXTRA_PSEUDO);
        currentUser = new User(currentPseudo);

        m_recyclerView = (RecyclerView) findViewById(R.id.conversation_overviews_recycler_view);
        m_recyclerViewLayoutManager = new LinearLayoutManager(this);
        m_recyclerView.setLayoutManager(m_recyclerViewLayoutManager);
        m_recyclerViewAdapter = new ConversationOverviewAdapter(tmpConversationsData, new ConversationOverviewAdapter.onConversationOverviewClickedListener() {
            public  void onOverviewClicked(String pseudo)
            {
                onConversationOverviewClicked(pseudo);
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

    public void onNewConversationOrMessageButtonClicked(View view)
    {
        Log.d("FAB", "New message button clicked");
    }

    public void onConversationOverviewClicked(String pseudo)
    {
        Log.d("OVERVIEW", "Conversation overview clicked : " + pseudo);
        Intent intent = new Intent(this, ChatActivity.class);
        /*
        intent.putExtra(CurrentUser.EXTRA_CURRENT_USER, currentUser);
        intent.putExtra(User.EXTRA_PSEUDO, pseudo);
         */
        intent.putExtra(User.EXTRA_PSEUDO, currentUser.getPseudo());
        intent.putExtra(User.EXTRA_USER, new User(pseudo));
        startActivity(intent);
    }
}
