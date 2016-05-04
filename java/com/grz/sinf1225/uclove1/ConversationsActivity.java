package com.grz.sinf1225.uclove1;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class ConversationsActivity extends AppCompatActivity
{
    private RecyclerView m_recyclerView;
    private RecyclerView.Adapter m_recyclerViewAdapter;
    private RecyclerView.LayoutManager m_recyclerViewLayoutManager;
    /*
    CurrentUser currentUser;
     */

    private List<ConversationOverviewData> tmpConversationsData;
    private final int tmpProfilePictureRes1 = R.drawable.angelina_jolie_profile_picture;
    private final String tmpPseudo1 = "Angelina244", tmpLastMessage1 = "Hey! this is my last message";
    private final boolean tmpLastMessageRead1 = false;
    private final int tmpProfilePictureRes2 = R.drawable.adele_profile_picture;
    private final String tmpPseudo2 = "ADEL.E", tmpLastMessage2 = "Hey! I luv u";
    private final boolean tmpLastMessageRead2 = true;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversations);

        tmpConversationsData = new ArrayList<ConversationOverviewData>();
        tmpConversationsData.add(new ConversationOverviewData(tmpProfilePictureRes1, tmpPseudo1, tmpLastMessage1, tmpLastMessageRead1));
        tmpConversationsData.add(new ConversationOverviewData(tmpProfilePictureRes2, tmpPseudo2, tmpLastMessage2, tmpLastMessageRead2));

        /*
        currentUser = (CurrentUser) getIntent().getSerializableExtra(CurrentUser.EXTRA_CURRENT_USER);
         */

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

    public void onNewConversationOrMessageButtonClicked(View view)
    {
        Log.d("FAB", "New message button clicked");
    }

    public void onConversationOverviewClicked(String pseudo)
    {
        Log.d("OVERVIEW", "Conversation overview clicked : " + pseudo);
    }
}
