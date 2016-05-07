package com.grz.sinf1225.uclove1.Chat;

import android.app.AlertDialog;
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
import android.widget.EditText;

import com.grz.sinf1225.uclove1.Chat.ConversationsActivity;
import com.grz.sinf1225.uclove1.Chat.MessageData;
import com.grz.sinf1225.uclove1.Chat.MessageViewAdapter;
import com.grz.sinf1225.uclove1.Database;
import com.grz.sinf1225.uclove1.R;
import com.grz.sinf1225.uclove1.SettingsActivity;
import com.grz.sinf1225.uclove1.User;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity
{
    private List<MessageData> tmpMessages;
    private final String tmpPseudo1 = "Angelina244", tmpPseudo2 = "ADEL.E";
    private final String tmpMsg1 = "This is a message", tmpMsg2 = "Hello", tmpMsg3 = "How are you?";
    private final String tmpSent1 = "01/01/2016", tmpSent2 = "05/01/2016", tmpSent3 = "05/03/2016";
    private final String tmpRead1 = "01/01/2016", tmpRead2 = "05/01/2016";

    private RecyclerView m_recyclerView;
    private RecyclerView.Adapter m_recyclerViewAdapter;

    /*
    private CurrentUser currentUser;
     */
    private User currentUser;
    private User m_interlocutorUser;
    private List<MessageData> messageList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        /*
        currentUser = (CurrentUser) getIntent().getSerializableExtra(CurrentUser.EXTRA_CURRENT_USER);
        m_interlocutorPseudo = (String) getIntent().getStringExtra(User.EXTRA_PSEUDO);
         */
    }

    @Override
    public void onResume()
    {
        super.onResume();

        String currentPseudo = getIntent().getStringExtra(User.EXTRA_PSEUDO);
        currentUser = new User(currentPseudo);

        m_interlocutorUser = (User) getIntent().getSerializableExtra(User.EXTRA_USER);
        setTitle(m_interlocutorUser.getPseudo());

        displayMessages();
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

    public void displayMessages()
    {
        messageList = Database.getAllMessages(currentUser.getPseudo(), m_interlocutorUser.getPseudo());

        tmpMessages = new ArrayList<MessageData>();
        tmpMessages.add(new MessageData(tmpPseudo1, tmpMsg1, tmpSent1, tmpRead1));
        tmpMessages.add(new MessageData(tmpPseudo2, tmpMsg2, tmpSent2, tmpRead2));
        tmpMessages.add(new MessageData(tmpPseudo2, tmpMsg3, tmpSent3, null));

        m_recyclerView = (RecyclerView) findViewById(R.id.messages_recycler_view);
        LinearLayoutManager recyclerViewLayoutManager = new LinearLayoutManager(this);
        recyclerViewLayoutManager.setReverseLayout(true);
        m_recyclerView.setLayoutManager(recyclerViewLayoutManager);
        m_recyclerViewAdapter = new MessageViewAdapter(messageList, new MessageViewAdapter.onMessageClickedListener() {
            @Override
            public void onMessageClicked(MessageData data) {
                showDetailsMessage(data);
            }
        }, this, m_interlocutorUser.getPseudo());
        m_recyclerView.setAdapter(m_recyclerViewAdapter);
    }

    public void showDetailsMessage(MessageData data)
    {
        Log.d("MSG", "Message selected " +data.toString());
        String msgToDisplay = getResources().getString(R.string.sent_on) +" "+ data.m_sendingDate +"\n";
        if (data.m_readingDate != null)
            msgToDisplay += getResources().getString(R.string.read_on) +" "+ data.m_readingDate;
        else
            msgToDisplay += getResources().getString(R.string.not_read_yet);
        new AlertDialog.Builder(this)
                .setTitle(getResources().getString(R.string.message_details))
                .setMessage(msgToDisplay)
                .setNegativeButton(getResources().getString(R.string.close_popup), null)
                .show();
    }

    public void onSendButtonClicked(View view)
    {
        EditText msgEditText = (EditText) findViewById(R.id.message_edit_text);
        String msgToSend = msgEditText.getText().toString();
        msgEditText.getText().clear();
        Log.d("BUTTON", "Send button pressed : " + msgToSend);

        Database.sendMessage(currentUser.getPseudo(), m_interlocutorUser.getPseudo(), msgToSend);

        displayMessages();
    }
}
