package com.grz.sinf1225.uclove1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity
{
    private RecyclerView m_recyclerView;
    private RecyclerView.Adapter m_recyclerViewAdapter;
    private RecyclerView.LayoutManager m_recyclerViewLayoutManager;

    private List<MessageData> tmpMessages;
    private final String tmpPseudo1 = "angelina", tmpPseudo2 = "Jesus";
    private final String tmpMsg1 = "This is a message", tmpMsg2 = "Hello", tmpMsg3 = "How are you?";
    private final boolean tmpRead1 = true, tmpRead2 = true, tmpRead3 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        setTitle(tmpPseudo1);

        tmpMessages = new ArrayList<MessageData>();
        tmpMessages.add(new MessageData(tmpPseudo1, tmpMsg1, tmpRead1));
        tmpMessages.add(new MessageData(tmpPseudo2, tmpMsg2, tmpRead2));
        tmpMessages.add(new MessageData(tmpPseudo2, tmpMsg3, tmpRead3));

        m_recyclerView = (RecyclerView) findViewById(R.id.messages_recycler_view);
        m_recyclerViewLayoutManager = new LinearLayoutManager(this);
        m_recyclerView.setLayoutManager(m_recyclerViewLayoutManager);
        m_recyclerViewAdapter = new MessageViewAdapter(tmpMessages, this, tmpPseudo1);
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
}
