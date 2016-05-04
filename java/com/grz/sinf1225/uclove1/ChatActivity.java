package com.grz.sinf1225.uclove1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity
{
    private RecyclerView m_recyclerView;
    private RecyclerView.Adapter m_recyclerViewAdapter;
    private RecyclerView.LayoutManager m_recyclerViewLayoutManager;

    private List<MessageData> tmpMessages;
    private final String tmpMsg1 = "This is a message", tmpMsg2 = "Hello", tmpMsg3 = "How are you?";
    private final boolean tmpRead1 = true, tmpRead2 = false, tmpRead3 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        setTitle("test");

        tmpMessages = new ArrayList<MessageData>();
        tmpMessages.add(new MessageData(tmpMsg1, tmpRead1));
        tmpMessages.add(new MessageData(tmpMsg2, tmpRead2));
        tmpMessages.add(new MessageData(tmpMsg3, tmpRead3));

        m_recyclerView = (RecyclerView) findViewById(R.id.messages_recycler_view);
        m_recyclerViewLayoutManager = new LinearLayoutManager(this);
        m_recyclerView.setLayoutManager(m_recyclerViewLayoutManager);
        m_recyclerViewAdapter = new MessageViewAdapter(tmpMessages, this);
        m_recyclerView.setAdapter(m_recyclerViewAdapter);
    }
}
